package io.bluecell;

import gate.Gate;
import java.io.File;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);  
        Gate.setGateHome(new File("/home/rich/GATE_Developer_8.1"));
        Gate.init();
    }

}