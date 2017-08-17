package test.java.com.MusicGroup.util;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader
{	
	String path =  getFilePath();  	
    public String readApplicationFile(String key){ 
    	String value = "";
        try{         	  
	          Properties prop = new Properties();
	          File f = new File(path + "//src//test//java//com//MusicGroup//config//application.properties");
	          if(f.exists()){
		          prop.load(new FileInputStream(f));
		          value = prop.getProperty(key); 		                 
          	}
	   }
        catch(Exception e){  
           System.out.println("Failed to read from application.properties file.");  
        }
        return value;
     } 
    
	public String getFilePath()
	{
		String filepath ="";		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		filepath = absolutePathOfFirstFile.replaceAll("\\\\+", "/");		
		return filepath;
	}
 
}