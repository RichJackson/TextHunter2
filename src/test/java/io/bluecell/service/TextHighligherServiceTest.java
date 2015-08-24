/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.service;

import gate.Document;
import gate.Factory;
import gate.Gate;
import gate.creole.ResourceInstantiationException;
import gate.util.GateException;
import io.bluecell.web.controllers.DocumentController;
import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rich
 */
public class TextHighligherServiceTest {
    TextHighlighterService instance;
    public TextHighligherServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            Gate.setGateHome(new File("/home/rich/GATE_Developer_8.1"));            
            Gate.init();
        } catch (GateException ex) {
            Logger.getLogger(TextHighligherServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    
    @Before
    public void setUp() {
        instance = new TextHighlighterService();
        try {
            instance.init();
        } catch (Exception ex) {
            Logger.getLogger(TextHighligherServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of init method, of class TextHighligherService.
     */
//    @Test
//    public void testInit() throws Exception {
//        System.out.println("init");
//        TextHighlighterService instance = new TextHighlighterService();
//        instance.init();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of execute method, of class TextHighlighterService.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        URL resourceUrl = getClass().getResource("/exampledocs/11758.docx");	            
        gate.Document doc = null;
        try {
            doc = Factory.newDocument(resourceUrl);
        } catch (ResourceInstantiationException ex) {
            Logger.getLogger(TextHighligherServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(doc);
        
        Document result = instance.execute(doc);
        
        System.out.println(result.toXml());
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
 
    
}
