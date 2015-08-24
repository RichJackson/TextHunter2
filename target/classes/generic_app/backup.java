

//if NER used, convert to context type Annot

if(scriptParams.NERMode.equals("true")){


nerAS = doc.getAnnotations("NER")
originalAnnos = doc.getAnnotations("").get("ManualAnnotation")
outputAS = doc.getAnnotations("ML")

	if(!originalAnnos.isEmpty()){			
		for (annot in originalAnnos){
			nerInsideTargetKeywordAS = gate.Utils.getContainedAnnotations(nerAS, annot, "NER")
			fm = Factory.newFeatureMap()
			fm.put("priority","0")				
			if(!nerInsideTargetKeywordAS.isEmpty()){
				fm = Factory.newFeatureMap()
				fm.putAll(nerInsideTargetKeywordAS.get(0))
				fm.put("observation","positive")							
				outputAS.add(annot.start(),annot.end(),"Context",fm)	
			}else{
				fm.put("observation","unknown")		
				outputAS.add(annot.start(),annot.end(),"Context",fm)	
			}		

		} 
	}
}


//forbatchlearning	 
for (annot in outputAS){
	annotProb = annot.getFeatures().get("LF_confidence")	
	if(annotProb!=null){
		annot.getFeatures().put("prob",annot.getFeatures().get("LF_confidence"))	
		annot.getFeatures().put("observation",annot.getFeatures().get("LF_class"))					
		annot.getFeatures().put("observation",annot.getFeatures().get("LF_class"))				

	}
}



//retain best prob
inputAS = doc.getAnnotations("ML")
originalAnnos = inputAS.get("Context")

bestProb = 0.0		
	

if(!originalAnnos.isEmpty()){	
Annotation bestAnnot			
//forbatchlearning	 
for (annot in originalAnnos){
	annotProb = annot.getFeatures().get("prob")	
	if(annotProb!=null & annotProb >= bestProb){
		bestAnnot = annot
		bestProb = annotProb
		inputAS.remove(annot)		
	}else{
		inputAS.remove(annot)
	}	
} 
inputAS.add(bestAnnot)
}


//copy ConTEXT features
inputAS = doc.getAnnotations("Working")
inputAS2 = doc.getAnnotations("ML")


List<Annotation> dList = new ArrayList<Annotation>(inputAS.get("Context"));
Collections.sort(dList, new OffsetComparator());

List<Annotation> MLList = new ArrayList<Annotation>(inputAS2.get("Context"));
Collections.sort(MLList, new OffsetComparator());

for (Annotation annI in dList) {
  
  for (Annotation annJ in MLList) {
    
    if (annJ.getStartNode().getOffset() == (annI.getStartNode().getOffset())
        && annJ.getEndNode().getOffset() == (annI.getEndNode().getOffset()) ) {
		
		FeatureMap fm = annI.getFeatures().toFeatureMap().clone() 
		FeatureMap fm2 = annJ.getFeatures()
		
		for (Map.Entry<String, String> entry : fm.entrySet())
		{
			fm2.put(entry.getKey(),entry.getValue());
		}

        annJ.setFeatures(fm2)
      break;
    }
  }
}

//cleanup working annots to save memory
doc.getAnnotations().clear();
doc.getAnnotations("Working").clear();


//move features
inputAS = doc.getAnnotations("ML").get("Context")
keyAS = doc.getAnnotations("Key").get("ManualAnnotation")
outputAS = doc.getAnnotations("TestKey")
newAnn = gate.Utils.getOnlyAnn(inputAS)
fm = Factory.newFeatureMap()
outputAS.add(newAnn.start(),newAnn.end(),"Context",fm)

gate.Utils.getOnlyAnn(outputAS).getFeatures().putAll(gate.Utils.getOnlyAnn(keyAS).getFeatures())


