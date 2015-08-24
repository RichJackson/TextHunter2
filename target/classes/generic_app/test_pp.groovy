

//if NER used, convert to context type Annot


outputAS = doc.getAnnotations("ML")
if(scriptParams.NERMode.equals("yes")){
nerAS = doc.getAnnotations("NER")
originalAnnos = doc.getAnnotations("Key").get("ManualAnnotation")
outputAS = doc.getAnnotations("ML")

	if(!originalAnnos.isEmpty()){			
		for (annot in originalAnnos){
			nerInsideTargetKeywordAS = gate.Utils.getOverlappingAnnotations(nerAS, annot,"NER")
			fm = Factory.newFeatureMap()
			fm.put("priority","0")				
			if(!nerInsideTargetKeywordAS.isEmpty()){
				for(nerAnnot in nerInsideTargetKeywordAS){
					fm = Factory.newFeatureMap()
					fm.putAll(nerAnnot.getFeatures())
					fm.put("observation","positive")	
					fm.put("prob",nerAnnot.getFeatures().get("LF_confidence"))
					outputAS.add(annot.start(),annot.end(),"Context",fm)
				}				
			}else{
				fm.put("observation","unknown")		
				fm.put("prob","1.0")				
				outputAS.add(annot.start(),annot.end(),"Context",fm)	
			}		

		} 
	}
}else{
//just convert classification info to be compatible with old BL PR
	for (annot in outputAS){
		annotProb = annot.getFeatures().get("LF_confidence")	
		if(annotProb!=null){
			annot.getFeatures().put("prob",annot.getFeatures().get("LF_confidence"))			
		}
		annotClass = annot.getFeatures().get("LF_class")
		if(annotClass!=null){		
			annot.getFeatures().put("observation",annot.getFeatures().get("LF_class"))									
		}
	}
}


//retain best prob
inputAS = doc.getAnnotations("ML")
originalAnnos = inputAS.get("Context")

bestProb = 0.0		
	
Annotation bestAnnot

if(!originalAnnos.isEmpty()){				
	//forbatchlearning	 
	for (annot in originalAnnos){
		annotProb = Double.parseDouble(annot.getFeatures().get("prob").toString()	)
		if(annotProb!=null & annotProb >= bestProb){
			bestAnnot = annot
			bestProb = annotProb
		} 
	}
	for (annot in originalAnnos){
		if(annot!=bestAnnot){
		inputAS.remove(annot)
		}
	}
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


//move features for PR testing
if(scriptParams.ModelBuildMode.equals("yes")){
inputAS = doc.getAnnotations("ML").get("Context")
keyAS = doc.getAnnotations("Key").get("ManualAnnotation")
outputAS = doc.getAnnotations("TestKey")
newAnn = gate.Utils.getOnlyAnn(inputAS)
fm = Factory.newFeatureMap()
outputAS.add(newAnn.start(),newAnn.end(),"Context",fm)

gate.Utils.getOnlyAnn(outputAS).getFeatures().putAll(gate.Utils.getOnlyAnn(keyAS).getFeatures())
}


