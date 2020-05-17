package org.antogautjean;

import java.io.IOException;

import javax.swing.UIManager;

import org.antogautjean.Controller.ConfigController;
import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StaffController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.model.FileImporter;
import org.antogautjean.view.HomeView;

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

        // IMPORTANT
        ConfigController.setConfigFilePath("src/main/java/org/antogautjean/settings.properties");

        System.out.println("FileImporter : Reading CSV Stock file");
        // Stock controller
        StockController stock = new StockController();
        FactoryController factory = new FactoryController();
        StaffController staff = new StaffController();

        try {
            FileImporter.fileToStock(stock);
            FileImporter.fileToFactory(factory, stock);
            FileImporter.fileToStaff(staff);

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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
