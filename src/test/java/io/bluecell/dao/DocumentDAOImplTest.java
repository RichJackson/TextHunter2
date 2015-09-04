/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.dao;

import gate.Document;
import gate.Gate;
import gate.util.GateException;
import io.bluecell.service.TextHighligherServiceTest;
import io.bluecell.service.TextHighlighterService;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
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
public class DocumentDAOImplTest {
    
    public DocumentDAOImplTest() {
    }
    
//    @BeforeClass
//    public static void setUpClass() {
//        try {
//            Gate.setGateHome(new File("/home/rich/GATE_Developer_8.1"));            
//            Gate.init();
//        } catch (GateException ex) {
//            Logger.getLogger(TextHighligherServiceTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    

    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

  
     
    @Test
    public void testGetTestSet() {
        System.out.println("getTestSet");
        DocumentDAOImpl instance = new DocumentDAOImpl();
        List<Document> result = instance.getTestSet();
        assertEquals(5, result.size());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
