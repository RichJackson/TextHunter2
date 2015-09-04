/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell;

import io.bluecell.service.TextHighlighterService;
import org.junit.runner.RunWith;
import org.springframework.config.java.annotation.ImportXml;
import org.springframework.config.java.plugin.context.AnnotationDrivenConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author rich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public abstract class TestBase {

    
  

    @Configuration
    @AnnotationDrivenConfig
    @ImportXml("config/Gate.xml")    
    static class ContextConfiguration {

        // this bean will be injected into the OrderServiceTest class
        @Bean
        public TextHighlighterService textHighlighterService() {
            return new TextHighlighterService();
        }      
        
        
    }


    
}
