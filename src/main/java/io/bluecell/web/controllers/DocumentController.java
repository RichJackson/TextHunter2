/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.web.controllers;


import gate.Annotation;
import gate.AnnotationSet;
import gate.Factory;
import gate.Gate;
import gate.creole.ResourceInstantiationException;
import io.bluecell.model.Greeting;
import io.bluecell.model.UpdateMessage;
import io.bluecell.service.TextHighlighterService;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rich
 */
@Controller
public class DocumentController {
    @Autowired
    TextHighlighterService thService;
    
    gate.Document doc;
    Iterator<Annotation> annots;    
    
    

    
    
    @RequestMapping(value="/document", method=RequestMethod.GET)
    public String greetingForm(Model model) {
            URL resourceUrl = getClass().getResource("/exampledocs/11758.docx");	            
        try {
            doc = Factory.newDocument(resourceUrl);
            thService.execute(doc);
            model.addAttribute("document", doc);
        } catch (ResourceInstantiationException ex) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "document";
//        model.addAttribute("greeting", new Greeting());
//        return "document";
    }

    @RequestMapping(value="/document", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "document";
    }
    
    public String loadDoc() throws Exception {
            URL resourceUrl = getClass().getResource("/exampledocs/11758.docx");	            
        try {
            doc = Factory.newDocument(resourceUrl);
            thService.execute(doc);
            annots = gate.Utils.inDocumentOrder(doc.getAnnotations("AutoCoder")).iterator();
        } catch (ResourceInstantiationException ex) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "document loaded";        
    }
          
    
    @MessageMapping("/update")
    @SendTo("/topic/update")
    public Greeting update(UpdateMessage message) throws Exception {

//        if(doc == null){
//            loadDoc();
//        }
        switch(message.getInstruction()){
            case "nextAnn": 
                return new Greeting("hello" + message.getInstruction());
            default: return null;            
        }               
    }    
  
}
