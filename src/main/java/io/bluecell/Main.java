package io.bluecell;

import gate.Gate;
import io.bluecell.service.TextHighlighterService;
import java.io.File;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("config/Gate.xml")
public class Main {

    public static void main(String[] args) throws Exception {
//        Gate.setGateHome(new File("/home/rich/GATE_Developer_8.1"));
//        Gate.init();        
        SpringApplication.run(Main.class, args);  
    }
    
    @Bean
    public TextHighlighterService textHighlighterService(){
        return new TextHighlighterService();
    }

}