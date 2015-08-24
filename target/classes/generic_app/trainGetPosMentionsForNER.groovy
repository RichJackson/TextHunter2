inputAS = doc.getAnnotations("Working")
originalAnnos = inputAS.get("ManualAnnotation")
outputAS = doc.getAnnotations("Working")

if(!originalAnnos.isEmpty()){
	for (annot in originalAnnos){
		annotProb = annot.getFeatures().get("observation")	
		if(annotProb.equals("positive")){
			fm = Factory.newFeatureMap()
			outputAS.add(annot.start(),annot.end(),"NER",fm)
		}
	}
}