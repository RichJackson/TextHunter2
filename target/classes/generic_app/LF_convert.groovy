inputAS = doc.getAnnotations("ML").get("Context")
for(annot in inputAS){
annot.getFeatures().put("observation",annot.getFeatures().get("LF_class"))
}
