/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.web.controllers;


import gate.Factory;
import gate.creole.ResourceInstantiationException;
import io.bluecell.model.Greeting;
import io.bluecell.model.HelloMessage;
import io.bluecell.service.TextHighlighterService;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
    
    @MessageMapping("/nextAnn")
    @SendTo("/topic/nextAnn")
    public Greeting greeting(HelloMessage message) throws Exception {
        //Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }    
    
  
}
