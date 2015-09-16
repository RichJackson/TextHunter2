/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.dao;

import io.bluecell.service.TextHighlighterService;
import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rich
 */
public class DocumentDAOImpl implements DocumentDAO {

    public final static Logger log = LoggerFactory.getLogger(DocumentDAOImpl.class);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
//        Session session = this.sessionFactory.openSession();
//        List<gate.Document> attachmentList = session.createQuery("from gateDocument").list();
//        session.close();
//        return attachmentList;
    }

//    @Override
//    public List<Document> getTestSet() {
//        String[] urlList = {
//            //"/exampledocs/11758.docx",
//            "/exampledocs/131776.docx",
//            "/exampledocs/152912.docx",
//            "/exampledocs/155317.docx",
//            "/exampledocs/342920.docx"
//        };
//
//        List<gate.Document> returnList = new ArrayList<>();
//        for (int i = 0; i <= urlList.length; i++) {
//            URL resourceUrl = getClass().getResource(urlList[i]);
//            try {
//                gate.Document doc = gate.Factory.newDocument(resourceUrl);
//                thService.execute(doc);
//                returnList.add(doc);
//            } catch (ResourceInstantiationException ex) {
//                java.util.logging.Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        return returnList;
//    }

}
