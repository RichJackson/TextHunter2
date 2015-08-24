inputAS = doc.getAnnotations("ML").get("Context")
keyAS = doc.getAnnotations("Key").get("ManualAnnotation")
outputAS = doc.getAnnotations("TestKey")
newAnn = gate.Utils.getOnlyAnn(inputAS)
fm = Factory.newFeatureMap()
outputAS.add(newAnn.start(),newAnn.end(),"Context",fm)

gate.Utils.getOnlyAnn(outputAS).getFeatures().putAll(gate.Utils.getOnlyAnn(keyAS).getFeatures())
