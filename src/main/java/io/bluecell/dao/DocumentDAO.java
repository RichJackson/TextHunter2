/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.bluecell.dao;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author rich
 */
public interface DocumentDAO {
    public void save(gate.Document p);
    
    public List<gate.Document> list();
    
    public List<gate.Document> getTestSet();

    
}
