package com.vytrack.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    // this class will be responsible for loading properties file and
    // will provide access to values based on key names
    private  static Properties configFile;
    static {

            // provides access to file
            // try/catch block stands for handling
            // if exception occurs, code inside a catch block will be executed
            //any class is related to InputOutput produce checked exceptions
            // without handling checked exception, you can  not run a code

        try {
            FileInputStream fileInputStream= new FileInputStream("configuration.properties");
            configFile= new Properties();
            // load your properties file
            configFile.load(fileInputStream);
            fileInputStream.close();



        } catch (IOException e) {
            System.out.println("File was not loaded :(");
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
       return  configFile.getProperty(key);
    }

}

