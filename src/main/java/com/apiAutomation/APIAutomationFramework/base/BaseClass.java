package com.apiAutomation.APIAutomationFramework.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import org.testng.annotations.BeforeSuite;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class BaseClass 
{
  
	public Properties prop;
	Response resp;
	
	
	
    @BeforeSuite
    public void initiate() {
    	prop = new Properties();
    	try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\apiAutomation\\APIAutomationFramework\\resources\\environment.properties");
            prop.load(fis);		
  } catch (Exception e) {
			System.out.println(e.getMessage());
			}
    	
    }
    
  
    public String generateStringFromXML(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
    }
    
    public Object convertDataFormat(Response r, String format) {
    	String respBody=r.asString();
    	System.out.println(respBody);
    	if(format.equalsIgnoreCase("Json")) {
    		JsonPath js= new JsonPath(respBody);
    		return js;
    	}else if(format.equalsIgnoreCase("XML")) {
    		XmlPath xp = new XmlPath(respBody);
    		return xp;
    	}
		return null;
    	
		
	}
}
