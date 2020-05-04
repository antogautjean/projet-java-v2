package org.antogautjean.model;

import java.io.*;
import java.util.Properties;

public class Config
{
    String configFilePath;
    OutputStream output;
    Properties prop;

    public Config(String configFilePath) throws Exception {
        try {
            File f = new File(configFilePath);
            if (!f.exists()) {
                if(f.createNewFile()){
                    System.out.println("File created: " + f.getName());
                    FileWriter myWriter = new FileWriter(configFilePath);
                    myWriter.write("stockFile=\n");
                    myWriter.write("linesFile=");
                    myWriter.close();
                }else {
                    System.out.println("File already exists.");
                }
            }
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        this.configFilePath = configFilePath;
        this.prop = new Properties();
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