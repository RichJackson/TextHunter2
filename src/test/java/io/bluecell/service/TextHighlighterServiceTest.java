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
import io.bluecell.TestConfig;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author rich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfig.class ,loader=AnnotationConfigContextLoader.class)
public class TextHighlighterServiceTest {  
    
    @Autowired
    TextHighlighterService thService;
    
    public TextHighlighterServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        if(!Gate.isInitialised()){
            Gate.setGateHome(new File("/home/rich/GATE_Developer_8.1"));
            try {
                Gate.init();
            } catch (GateException ex) {
                Logger.getLogger(GateDocumentServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of init method, of class TextHighlighterService.
     */
    @Ignore
    @Test
    public void testInit() throws Exception {
        System.out.println("init");
        TextHighlighterService instance = new TextHighlighterService();
        instance.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of execute method, of class TextHighlighterService.
     */
    @Ignore
    @Test
    public void testExecute() {
        System.out.println("execute");
        URL resourceUrl = getClass().getResource("/exampledocs/11758.docx");	            
        gate.Document doc = null;
        try {
            doc = Factory.newDocument(resourceUrl);
        } catch (ResourceInstantiationException ex) {
            Logger.getLogger(TextHighlighterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(doc);
        
        Document result = thService.execute(doc);
        
        System.out.println(result.toXml());
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
