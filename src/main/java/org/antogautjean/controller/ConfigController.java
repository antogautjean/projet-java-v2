package org.antogautjean.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Classe permetant de modifier les différents parametre
 */
public class ConfigController
{
    protected static String configFilePath = "";
    protected static OutputStream output;
    protected static Properties prop;

    /**
     * Permet de configurer le fichier settings.properties, afin d'avoir tout les chemins des différents fichiers
     * @param configFilePath Le chemin ou se trouves settings.properties
     */
    public static void setConfigFilePath(String configFilePath) {
        try {
            File f = new File(configFilePath);
            if (!f.exists()) {
                if(f.createNewFile()){
                    System.out.println("File created: " + f.getName());
                    FileWriter myWriter = new FileWriter(configFilePath);
                    myWriter.write("stockFile=\n");
                    myWriter.write("pricesFile=\n");
                    myWriter.write("linesFile=\n");
                    myWriter.write("staffFile=");
                    myWriter.close();
                }else {
                    System.out.println("File already exists.");
                }
            }
            ConfigController.configFilePath = configFilePath;
            ConfigController.prop = new Properties();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String getConfigString() {
        return ConfigController.configFilePath;
    }

    public static boolean isConfigStringEmpty() {
        return ConfigController.configFilePath == "";
    }

    public static void setProperty(String key, String value){
        ConfigController.prop.setProperty(key, value);
    }

    public static void commit() {
        try {
            ConfigController.output = new FileOutputStream(ConfigController.configFilePath);
            ConfigController.prop.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key){
        try (InputStream input = new FileInputStream(ConfigController.configFilePath)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}