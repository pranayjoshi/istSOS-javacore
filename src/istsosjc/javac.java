/**
 * Author: Pranay, Google Code-in 2019
 * Copyright: (C) 2020 by Pranay and the istSOS Development Team
 * Licence: This program is free software under the GNU General Public
 * Version: 0.1
 */

package istsosjc;

import org.istsos.client.DatabaseConnection;
import org.istsos.client.EventObject;
import org.istsos.client.IstSOS;
import org.istsos.client.IstSOSListener;
import org.istsos.client.Procedure;
import org.istsos.client.Server;
import org.istsos.client.Service;

public class javac {
	public static void main(String[] args) {
		IstSOS istsos = IstSOS.getInstance();
		String serverName = "localhost";
		istsos.initServer(serverName, "http://istsos.org/istsos/");
		
		final Server server = istsos.getServer(serverName);
		
	    server.loadServices(new IstSOSListener() {
	    	
	        @Override
	        public void onSuccess(EventObject event) {
	            
	        	System.out.println("Success!! you have loaded the service");
	    		Service service = server.getService("demo");
	    		// VALIDATE DATABASE
	    	    service.loadDatabase(new IstSOSListener() {
					
	    			@Override
	    			public void onSuccess(EventObject event) {
	    			
	    				//validate database
	    				service.validateDatabase((DatabaseConnection) event.getObject());
	    									
	    			}
	    								
	    			@Override
	    			public void onError(EventObject event) {
	    						
	    									
	    			}
	    		});
	    	    // DESCRIBE SENSOR
	    	    service.describeSensor("BELLINZONA", new IstSOSListener() {
	    	        @Override
	    	        public void onSuccess(EventObject event) {
	    	        	System.out.println("Success!! YOu have successfully created an observation");
	    	            Procedure procedure = (Procedure) event.getObject();
	    	            
	    	            
	    	            service.registerSensor(procedure, new IstSOSListener() {
	    								
	    					@Override
	    					public void onSuccess(EventObject event) {
	    						System.out.println("Success!! YOu have successfully registered a sensor");	
	    									
	    					}
	    								
	    					@Override
	    					public void onError(EventObject event) {
	    						System.out.println("oops, something went wrong");			
	    									
	    					}
	    				});
	    	            
	    	        }

	    	        @Override
	    	        public void onError(EventObject event) {
	    	        	System.out.println("oops, something went wrong");
	    	        }
	    	    });
	    	    service.getProcedures();
	        }    
	        @Override
	        public void onError(EventObject event) {
	        	System.out.println("Oops an error occured!!");
	    }

	    });
	}

}

