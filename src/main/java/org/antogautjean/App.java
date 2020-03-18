package org.antogautjean;

import org.antogautjean.Controller.LogController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.model.FileImporter;
import org.antogautjean.view.HomeView;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {
		// init depending of args
		boolean isUIVisible = false;
		for (String s : args) {
			if (s.equals("--no-ui")) {
				isUIVisible = false;
			}
		}
		LogController logger = new LogController(".", true); // il n'y a pas de raison de ne pas afficher la console

		logger.log(0, "App", "starting", "OK");

		if (isUIVisible) {
			new HomeView();
			logger.log(0, "App", "UI", "Runing with UI");
		} else {
			logger.log(0, "App", "UI", "Runing with NO UI");
		}

		StockController koalaController = new StockController("koala");

		FileImporter fileImporter = new FileImporter();
		
		logger.log(0, "App", "FileImporter", "Reading CSV Stock file");
		fileImporter.fileToStock("./src/main/java/org/antogautjean/data/elements.csv",
				"./src/main/java/org/antogautjean/data/prix.csv", koalaController);
	}
}
