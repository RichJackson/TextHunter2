/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell;

import io.bluecell.dao.DocumentDAO;
import io.bluecell.dao.DocumentDAOImpl;
import io.bluecell.dao.DocumentDAOTestImpl;
import io.bluecell.service.TextHighlighterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 *
 * @author rich
 */
@Configuration
public class TestConfig {
        // this bean will be injected into the OrderServiceTest class
        @Bean
        public TextHighlighterService textHighlighterService(){
            return new TextHighlighterService();
        }
        @Bean
        public DocumentDAO documentDAO(){
            return new DocumentDAOTestImpl();
        }    
}
