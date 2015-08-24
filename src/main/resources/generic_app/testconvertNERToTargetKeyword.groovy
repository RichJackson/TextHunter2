nerAS = doc.getAnnotations("Learn")
originalAnnos = doc.getAnnotations("").get("TargetKeyword")
outputAS = doc.getAnnotations("NERTest")

if(!originalAnnos.isEmpty()){			
	for (annot in originalAnnos){
		nerInsideTargetKeywordAS = gate.Utils.getContainedAnnotations(nerAS, annot, "NER")
		if(!nerInsideTargetKeywordAS.isEmpty()){
			annot.getFeatures().put("NERdetected","yes")
			fm = Factory.newFeatureMap()
			fm.put("observation","positive")
			outputAS.add(annot.start(),annot.end(), "ManualAnnotation",fm)			
		}else{
			annot.getFeatures().put("NERdetected","no")	
		}		

	} 
}

originalAnnos = doc.getAnnotations("Key").get("ManualAnnotation")
outputAS = doc.getAnnotations("NERKey")

if(!originalAnnos.isEmpty()){			
	for (annot in originalAnnos){
		annotProb = annot.getFeatures().get("observation")	
		if(annotProb.equals("positive")){
			fm = Factory.newFeatureMap()
			fm.put("observation","positive")
			outputAS.add(annot.start(),annot.end(), "ManualAnnotation",fm)			
		
		}
	} 
}

//inputAS = doc.getAnnotations("Learn")
//for(annot in inputAS){
//println(annot.getFeatures().get("LF_confidence"))

//}