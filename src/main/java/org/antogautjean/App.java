package org.antogautjean;

import org.antogautjean.controller.Logger;
import org.antogautjean.model.Stock;
import org.antogautjean.view.HomeView;

import java.io.IOException;

public class App {

	public static Logger LOGGER;

	public static boolean gui = false;
	public static boolean log = false;

	private static void termMain() throws IOException {

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

	private static void logger(boolean USE){

		App.LOGGER = new Logger(".", USE);
	}
	
	public static void main(String[] args) throws IOException {

		for(String s : args){
			switch(s){
				case "--gui":
					App.gui = true;
					break;
				case "--log":
					App.log = true;
			}
		}


		logger(App.log);

		App.LOGGER.log(0, "App", "starting", "OK");
		guiMain(App.gui);
		termMain();
	}
}
