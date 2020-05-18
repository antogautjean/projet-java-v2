package org.antogautjean;

import java.io.IOException;

import javax.swing.UIManager;

import org.antogautjean.controller.ConfigController;
import org.antogautjean.controller.FactoryController;
import org.antogautjean.controller.StaffController;
import org.antogautjean.controller.StockController;
import org.antogautjean.model.FileImporter;
import org.antogautjean.view.HomeView;

/**
 * Classe principale permettant le lancement de l'application
 */
public class App {

    /**
     * Fonction principale permettant le lancement de l'application
     */
    public static void main(String[] args) throws Exception {
        // init depending of args
        boolean isUIVisible = true;
        for (String s : args) {
            if (s.equals("--no-ui")) {
                isUIVisible = false;
                break;
            }
        }
        System.out.println("starting : OK");

        // IMPORTANT
        try {
            ConfigController.setConfigFilePath("settings.properties");
        } catch (Exception e) {
            System.out.println("settings.properties is needed");
        }

        System.out.println("FileImporter : Reading CSV Stock file");
        // Stock controller
        StockController stock = new StockController();
        FactoryController factory = new FactoryController();
        StaffController staff = new StaffController();

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
