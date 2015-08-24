inputAS = doc.getAnnotations("")
originalAnnos = inputAS.get("TargetKeyword")

bestProb = 0.0		
	

if(!originalAnnos.isEmpty()){	
	doc.getFeatures().put("containsHits","true");
}