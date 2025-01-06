package assurance;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private static user currentUser;
    private static Map<String, Object> contextData = new HashMap<>(); // Map for storing key-value pairs

    // Method to get the current user
    public static user getUser() {
        return currentUser;
    }

    // Method to set the current user
    public static void setUser(user user) {
    	
        TestContext.currentUser = user;
    }

    // Method to set key-value pairs in the context
    public static void set(String key, Object value) {
    	System.out.println("TestContext set: " + key + " = " + value);
        contextData.put(key, value);
        
    }

    // Method to get values from the context
    public static Object get(String key) {
    	  System.out.println("TestContext get: " + key + " = " + contextData.get(key));
        return contextData.get(key);
    }

    // Method to clear the context (for cleanup between scenarios)
    public static void reset() {
        currentUser = null;
        contextData.clear();
        System.out.println("TestContext reset.");
    }

    // This is for messages in enroll tests and review tests
    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    public static Map<String, Object> getAllData() {
        return new HashMap<>(contextData); // Return a copy of the context data
    } 
  
}