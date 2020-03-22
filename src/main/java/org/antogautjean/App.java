package org.antogautjean;

import org.antogautjean.Controller.LineController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.model.FileImporter;
import org.antogautjean.view.HomeView;

import javax.sound.sampled.Line;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        // init depending of args
        boolean isUIVisible = true;

        for (String s : args) {
            if (s.equals("--no-ui")) {
                isUIVisible = false;
            }
        }

        System.out.println("starting : OK");

        StockController stockController = new StockController("koala");

        System.out.println("FileImporter : Reading CSV Stock file");
        FileImporter.fileToStock("./src/main/java/org/antogautjean/data/elements.csv",
                "./src/main/java/org/antogautjean/data/prix.csv", stockController);

        LineController l = null;

        if (isUIVisible) {
            new HomeView(stockController, l);
            System.out.println("Runing with UI");
        } else {
            System.out.println("Runing with NO UI");
        }

    }
}
