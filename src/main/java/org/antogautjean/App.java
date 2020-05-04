package org.antogautjean;

import org.antogautjean.Controller.ConfigController;
import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StaffController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.Controller.StaffController;
import org.antogautjean.model.FileImporter;
import org.antogautjean.view.HomeView;

import java.io.IOException;

import javax.swing.UIManager;

public class App {

    public static void main(String[] args) throws Exception {
        // init depending of args
        boolean isUIVisible = true;
        for (String s : args) {
            if (s.equals("--no-ui")) {
                isUIVisible = false;
            }
        }
        System.out.println("starting : OK");

        ConfigController cfg = new ConfigController("src/main/java/org/antogautjean/settings.properties");

        System.out.println("FileImporter : Reading CSV Stock file");
        String dataPath = "./src/main/java/org/antogautjean/data/";
        // Stock controller
        StockController stock;
        FactoryController factory;
        StaffController staff;

        try{
            stock = FileImporter.fileToStock(cfg.getProperty("stockFile"), cfg.getProperty("pricesFile"));
            factory = FileImporter.fileToFactory(cfg.getProperty("linesFiles"), stock);
            staff = FileImporter.fileToStaff(cfg.getProperty("staffFiles"));
        }
        catch (Exception ex){
            ex.printStackTrace();
            stock = null;
            factory = null;
            staff = null;
        }

        if (isUIVisible) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Attention: setLookAndFeel ne fonctionne pas");
            }
            new HomeView(stock, factory, staff);
            System.out.println("Runing with UI");
        } else {
            System.out.println("Runing with NO UI");
        }
    }
}
