package org.antogautjean.model;

import java.io.*;
import java.util.Properties;

public class Config
{
    String configFilePath;
    OutputStream output;
    Properties prop;

    public Config(String configFilePath) throws Exception {
        File f = new File(configFilePath);
        if (f.exists()){
            this.configFilePath = configFilePath;
            this.prop = new Properties();
        }
        else {
           throw new Exception("Le fichier spécifié n'existe pas");
        }
    }

    public void setProperty(String key, String value){
        this.prop.setProperty(key, value);
    }

    public void commit() {
        try {
            this.output = new FileOutputStream(this.configFilePath);
            this.prop.store(output, null);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public String getProperty(String key){
        try (InputStream input = new FileInputStream(this.configFilePath)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}