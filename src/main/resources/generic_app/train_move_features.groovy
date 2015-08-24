// to be used for ML test mode this copies the features from the manual annotation onto the Context annotation in the default set
AnnotationSet keySet = doc.getAnnotations("");

inputAS.get(scriptParams.contextAnnot).each{ context ->
    hasFeature = false;

  Utils.getContainedAnnotations(keySet, context, "ManualAnnotation").inDocumentOrder().each { abs ->
        hasFeature = true
 
		//FeatureMap fm = abs.getFeatures().toFeatureMap().clone() 
		FeatureMap fm = abs.getFeatures()
		FeatureMap fm2 = context.getFeatures()
		fm2.clear();
		fm2.putAll(fm)
		
		//for (Map.Entry<String, String> entry : fm.entrySet())
		//{
		//	fm2.put(entry.getKey(),entry.getValue());
		//}

        //context.setFeatures(fm2)
		

  }
  


}  