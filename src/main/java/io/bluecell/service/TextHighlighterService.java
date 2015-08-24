/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.service;

import gate.Corpus;
import gate.Factory;
import gate.FeatureMap;
import gate.Resource;
import gate.creole.ExecutionException;
import gate.creole.RealtimeCorpusController;
import gate.util.persistence.PersistenceManager;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author rich
 */
public class TextHighlighterService {
    private RealtimeCorpusController controller;

    @PostConstruct
    public void init() throws Exception {
        controller = (RealtimeCorpusController) PersistenceManager
                .loadObjectFromFile(new File(getClass()
                        .getClassLoader()
                        .getResource("generic_app/rthunter.gapp")
                        .getFile())
                );                 
        Corpus corpus = Factory.newCorpus("corpus");
        controller.setCorpus(corpus);
    }
    public gate.Document execute(gate.Document doc){
        controller.getCorpus().add(doc);
        
        try {
            controller.execute();
        } catch (ExecutionException ex) {
            Logger.getLogger(TextHighlighterService.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            controller.getCorpus().remove(doc);
        }
        return doc;
    }
    
}
