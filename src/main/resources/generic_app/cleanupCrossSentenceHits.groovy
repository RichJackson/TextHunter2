//get default set
inputAS = doc.getAnnotations()
inputAS1 = doc.getAnnotations("Working")["Sentence"]
inputAS2 = doc.getAnnotations("Working")["TargetKeyword"]


for (sentence in inputAS1){
inputAS3 = gate.Utils.getContainedAnnotations(inputAS2,sentence,"TargetKeyword")
	for(targetKeyword in inputAS3){
		outputAS.add(targetKeyword.start(), targetKeyword.end(), "TargetKeyword", targetKeyword.getFeatures().clone())	
	}
}

inputAS.removeAll(inputAS2)