package org.antogautjean.view;

import org.antogautjean.model.FileImporter;
import org.antogautjean.Controller.LogController;
import org.antogautjean.Controller.StockController;

import java.io.IOException;

public class App {

	public static LogController LOGGER;

	public static boolean gui = false;
	public static boolean log = false;


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


		LogView.logger(App.log);

		App.LOGGER.log(0, "App", "starting", "OK");

		HomeView.guiMain(App.gui);

	}
}
