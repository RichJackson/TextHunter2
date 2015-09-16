package io.bluecell;

import gate.Gate;
import io.bluecell.dao.DocumentDAO;
import io.bluecell.dao.DocumentDAOImpl;
import io.bluecell.dao.DocumentDAOTestImpl;
import io.bluecell.service.TextHighlighterService;
import java.io.File;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@ImportResource("classpath:config/Gate.xml")   
public class Main {
    public static void main(String[] args) throws Exception {
        Gate.setGateHome(new File("/home/rich/GATE_Developer_8.1"));
        Gate.init();        
        SpringApplication.run(Main.class, args);  
    }
    @Bean
    public TextHighlighterService textHighlighterService(){
        return new TextHighlighterService();
    }
    @Bean
    public DocumentDAO documentDAO(){
        return new DocumentDAOTestImpl();
    }
}