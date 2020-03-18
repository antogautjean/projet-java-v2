package org.antogautjean.view;

import org.antogautjean.Controller.LogController;

import java.io.IOException;

public class AppRunner {

	public static LogController LOGGER;

	public static boolean gui = false;
	public static boolean log = false;


	public static void main(String[] args) throws IOException {

		for(String s : args){
			switch(s){
				case "--gui":
					AppRunner.gui = true;
					break;
				case "--log":
					AppRunner.log = true;
			}
		}


		LogView.logger(AppRunner.log);

		AppRunner.LOGGER.log(0, "App", "starting", "OK");

		HomeView.guiMain(AppRunner.gui);

	}
}
