

//if NER used, convert to context type Annot
inputAS = doc.getAnnotations("ML").get("NER")
outputAS = doc.getAnnotations("ML")


for(annot in inputAS){
fm = Factory.newFeatureMap()
fm.putAll(annot.getFeatures())
fm.put("priority","0")

outputAS.add(annot.start(),annot.end(),"Context",fm)
}



inputAS = doc.getAnnotations("ML")
originalAnnos = inputAS.get("Context")
if(!originalAnnos.isEmpty()){
	Annotation bestAnnot			
	//forbatchlearning	 
	for (annot in originalAnnos){
		annotProb = annot.getFeatures().get("LF_class")	
		if(annotProb!=null){
			annot.getFeatures().put("prob",annot.getFeatures().get("LF_confidence"))	
			annot.getFeatures().put("observation",annot.getFeatures().get("LF_class"))					
		}

	}
}