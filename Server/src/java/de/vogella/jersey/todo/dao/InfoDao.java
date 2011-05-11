/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vogella.jersey.todo.dao;

import java.util.HashMap;
import java.util.Map;

import de.vogella.jersey.todo.model.Info;
/**
 *
 * @author jojo
 */
public enum InfoDao {
            instance;

	private Map<String, Info> contentProvider = new HashMap<String, Info>();
        private Medicoes medir = new Medicoes();
        

	private InfoDao() {

		Info todo = new Info("1","test", 1,1,1,1,1,1,1,1,1,1,1);
                contentProvider.put(todo.getId(), todo); 
		//todo.setDescription("Read http://www.vogella.de/articles/REST/article.html");
		//contentProvider.put("1", todo);
		//todo = new Info("2", "Do something");
		//todo.setDescription("Read complete http://www.vogella.de");
		//contentProvider.put("2", todo);

	}
	public Map<String, Info> getModel(){
		return contentProvider;
	}
        public Medicoes getMedicoes(){
            return medir;
        }
    
    
}
