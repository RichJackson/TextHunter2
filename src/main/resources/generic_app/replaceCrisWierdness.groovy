import gate.corpora.DocumentContentImpl;

docContent = doc.getContent().toString().replace(";;",System.lineSeparator());

doc.setContent(new DocumentContentImpl (docContent));


