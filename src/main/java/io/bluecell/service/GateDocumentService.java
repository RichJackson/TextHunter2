/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.service;

import gate.Annotation;
import gate.Document;
import gate.DocumentContent;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author rich
 */
public class GateDocumentService {
    
    private final ListIterator<gate.Document> docs;  
    private gate.Document currentDoc;    
    private ListIterator<Annotation> annots;      

    public GateDocumentService(List<Document> list) {
        docs = list.listIterator();
        currentDoc = docs.next();
    }

    public Annotation nextAnnotation() {
        if(annots==null){
            setFields();
        }
        Annotation annot;
        if(annots.hasNext()){
            annot = annots.next();
        }else{
            do{
                annot = annots.previous();
            }while(annots.hasPrevious());
        };
        return annot;
    }
    
    public Annotation prevAnnotation() {
        if(annots==null){
            setFields();
        }        
        Annotation annot;
        if(annots.hasPrevious()){
            annot = annots.previous();
        }else{
            do{
                annot = annots.next();
            }while(annots.hasNext());
        };
        return annot;
    }
    
    public Document prevDoc() {
        if(docs.hasPrevious()){
            currentDoc = docs.previous();
        }else{
            do{
                currentDoc = docs.next();
            }while(docs.hasNext());
        };
        setFields();
        return currentDoc;
    }

    public Document nextDoc() {
        if(docs.hasNext()){
            currentDoc = docs.next();
        }else{
            do{
                currentDoc = docs.previous();
            }while(docs.hasPrevious());
        };
        setFields();
        return currentDoc;
    }    
    
    private void setFields(){
        annots = gate.Utils.inDocumentOrder(currentDoc.getAnnotations("AutoCoder")).listIterator();        
    }
    
    public Document getCurrentDoc() {
        return currentDoc;
    }    
}
