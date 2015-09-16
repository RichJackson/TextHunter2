/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.web.controllers;


import gate.Annotation;
import gate.Document;
import gate.DocumentContent;
import io.bluecell.dao.DocumentDAO;
import io.bluecell.model.UpdateMessage;
import io.bluecell.service.GateDocumentService;
import io.bluecell.service.TextHighlighterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 *
 * @author rich
 */
@Controller
public class DocumentController {
    
    @Autowired
    TextHighlighterService thService;
    
    @Autowired
    DocumentDAO dao;
    
    GateDocumentService gateDocService;                      
    
    @MessageMapping("/updateAnn")
    @SendTo("/topic/updateAnn")
    public Annotation updateAnn(UpdateMessage message) throws Exception {

        switch(message.getInstruction()){
            case "nextAnn": 
                return gateDocService.nextAnnotation();            
            default: return null;            
        }               
    }   
    
    @MessageMapping("/updateDoc")
    @SendTo("/topic/updateDoc")
    public gate.Document updateDoc(UpdateMessage message) throws Exception {
        switch(message.getInstruction()){
            case "nextDoc": 
                return gateDocService.nextDoc();
            case "PrevDoc":                 
                return gateDocService.prevDoc();          
            case "LoadDocs":                 
                return loadDocs();                  
            default: return null;            
        }               
    }      

    private Document loadDocs(){
        gateDocService = new GateDocumentService(dao.list());
        return gateDocService.getCurrentDoc();
    } 
}
