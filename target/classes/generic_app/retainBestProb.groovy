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