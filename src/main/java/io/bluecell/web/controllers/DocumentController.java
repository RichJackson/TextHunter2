/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.web.controllers;


import gate.Factory;
import gate.creole.ResourceInstantiationException;
import io.bluecell.model.Greeting;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rich
 */
public class DocumentController {
    @RequestMapping(value="/document", method=RequestMethod.GET)
    public String greetingForm(Model model) {
            URL resourceUrl = getClass().getResource("exampledocs/11758.docx");	
        try {
            model.addAttribute("document", Factory.newDocument(resourceUrl));
        } catch (ResourceInstantiationException ex) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "document";
    }

//    @RequestMapping(value="/gatedoc", method=RequestMethod.POST)
//    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
//        model.addAttribute("greeting", greeting);
//        return "result";
//    }    
}
