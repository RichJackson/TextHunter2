/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.dao;

import gate.creole.ResourceInstantiationException;
import io.bluecell.service.TextHighlighterService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rich
 */
public class DocumentDAOTestImpl implements DocumentDAO {

    private static final Logger log = LoggerFactory.getLogger(DocumentDAOTestImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    TextHighlighterService thService;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(gate.Document p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
//        Session session = this.sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        session.persist(p);
//        tx.commit();
//        session.close();
    }

    @Override
    public List<gate.Document> list() {
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

                gate.Document doc;
            try {
                doc = gate.Factory.newDocument(resourceUrl);
                thService.execute(doc);
                returnList.add(doc);                
            } catch (ResourceInstantiationException ex) {
                log.error(ex.getMessage());
            }


        }

        return returnList;
    }

}
