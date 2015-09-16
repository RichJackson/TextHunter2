/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.service;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Document;
import gate.Gate;
import gate.creole.ResourceInstantiationException;
import gate.util.GateException;
import io.bluecell.TestConfig;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
public class GateDocumentServiceTest {
    
    @Autowired
    TextHighlighterService thService;
    
    List<Document> exampleDocs;
    
    public GateDocumentServiceTest() {
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
        exampleDocs = getTestSet();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of nextAnnotation method, of class GateDocumentService.
     */
    @Test
    public void testNextAnnotation() {
        System.out.println("nextAnnotation");
        thService.execute(exampleDocs.get(0));
        GateDocumentService instance = new GateDocumentService(exampleDocs);
        List<Annotation> annots = gate.Utils.inDocumentOrder(exampleDocs.get(0).getAnnotations("AutoCoder"));                
        Annotation expResult = annots.get(0);
        Annotation result = instance.nextAnnotation();
        assertEquals(expResult, result);
    }

    /**
     * Test of prevDoc method, of class GateDocumentService.
     */
    @Test
    public void testPrevDoc() {
        System.out.println("prevDoc");
        Document expResult = exampleDocs.get(exampleDocs.size()-1);
        GateDocumentService instance = new GateDocumentService(exampleDocs);
        instance.prevDoc();
        Document result = instance.prevDoc();
        assertEquals(expResult, result);
    }

    /**
     * Test of nextDoc method, of class GateDocumentService.
     */
    @Test
    public void testNextDoc() {
        System.out.println("nextDoc");
        Document expResult = exampleDocs.get(1);
        GateDocumentService instance = new GateDocumentService(exampleDocs);
        Document result = instance.nextDoc();
        assertEquals(expResult, result);
    }
    
    public List<Document> getTestSet() {
        String[] urlList = {
            "/exampledocs/11758.docx",
            "/exampledocs/131776.docx",
            "/exampledocs/152912.docx",
            "/exampledocs/155317.docx",
            "/exampledocs/342920.docx"
        };

        List<gate.Document> returnList = new ArrayList<>();
        for (int i = 0; i <= urlList.length-1; i++) {
            URL resourceUrl = getClass().getResource(urlList[i]);
            try {
                gate.Document doc = gate.Factory.newDocument(resourceUrl);
                thService.execute(doc);
                returnList.add(doc);
            } catch (ResourceInstantiationException ex) {
                java.util.logging.Logger.getLogger(GateDocumentServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return returnList;
    }    

    /**
     * Test of prevAnnotation method, of class GateDocumentService.
     */
    @Test
    public void testPrevAnnotation() {
        System.out.println("prevAnnotation");
        thService.execute(exampleDocs.get(0));        
        GateDocumentService instance = new GateDocumentService(exampleDocs);
        List<Annotation> annots = gate.Utils.inDocumentOrder(exampleDocs.get(0).getAnnotations("AutoCoder"));                
        Annotation expResult = annots.get(annots.size()-1);
        Annotation result = instance.prevAnnotation();
        assertEquals(expResult, result);
    }
    
}
