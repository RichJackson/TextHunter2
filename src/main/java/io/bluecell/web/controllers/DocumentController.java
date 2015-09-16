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
         
    @MessageMapping("/update")
    @SendTo("/topic/update")
    public gate.DocumentContent updateDoc(UpdateMessage message) throws Exception {
        switch(message.getInstruction()){
            case "nextDoc": 
                return thService.execute(gateDocService.nextDoc()).getContent();
            case "prevDoc":                 
                return thService.execute(gateDocService.prevDoc()).getContent();          
            case "loadDocs":                 
                return thService.execute(loadDocs()).getContent();                  
            default: return null;            
        }               
    }      

    private Document loadDocs(){
        gateDocService = new GateDocumentService(dao.list());                        
        return gateDocService.getCurrentDoc();
    } 
}
