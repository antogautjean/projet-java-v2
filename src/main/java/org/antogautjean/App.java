package org.antogautjean;

import org.antogautjean.controller.Logger;
import org.antogautjean.model.Stock;
import org.antogautjean.view.HomeView;

import java.io.IOException;

public class App {

	public static Logger LOGGER;

	private static void termMain(String[] args) throws IOException {

		Stock stock = new Stock("Principal");

		new FileImporter(
				"./src/main/java/org/antogautjean/data/elements.csv",
				"./src/main/java/org/antogautjean/data/prix.csv",
				stock);

		// pour vérifier des trucs
		System.out.println(stock.getProduct("E002").toString());
	}

	private static void guiMain(boolean DISPLAY){
		if (DISPLAY){
			App.LOGGER.log(0, "App", "GUI", "Runing with GUI");
			new HomeView();
		}
		else{
			App.LOGGER.log(0, "App", "GUI", "Runing without GUI");
		}
	}

	private static Logger logger(boolean USE){

		if (USE){
			return new Logger(".", USE);
		}
		else{
			System.out.println("Running without LOG");
			return null;
		}
	}
	
	public static void main(String[] args) throws IOException {

		App.LOGGER = logger(true);

		App.LOGGER.log(0, "App", "starting", "OK");

		guiMain(args[0].equals("--gui"));

		termMain(args);
	}
}
