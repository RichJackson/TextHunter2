/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.web.controllers;

import gate.Annotation;
import gate.Document;
import gate.DocumentContent;
import io.bluecell.TestConfig;
import io.bluecell.model.UpdateMessage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author rich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfig.class ,loader=AnnotationConfigContextLoader.class)
public class DocumentControllerTest {
    
    public DocumentControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of updateAnn method, of class DocumentController.
     */
    @Ignore
    @Test
    public void testUpdateAnn() throws Exception {
        System.out.println("updateAnn");
        UpdateMessage message = null;
        DocumentController instance = new DocumentController();
        Annotation expResult = null;
        Annotation result = instance.updateAnn(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDoc method, of class DocumentController.
     */
    @Ignore
    @Test
    public void testUpdateDoc() throws Exception {
        System.out.println("updateDoc");
        UpdateMessage message = null;
        DocumentController instance = new DocumentController();
        DocumentContent expResult = null;
        Document result = instance.updateDoc(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
