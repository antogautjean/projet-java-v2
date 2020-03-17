package org.antogautjean;

import org.antogautjean.model.Stock;
import org.antogautjean.view.MainView;

import java.io.IOException;

public class App {
	
	public static void main(String[] args) throws IOException {
		Stock stock = new Stock("Principal");
		
		new FileImporter("./src/main/java/org/antogautjean/data/elements.csv",
		"./src/main/java/org/antogautjean/data/prix.csv", stock);
		// pour vérifier des trucs
		System.out.println(stock.getProduct("E002").toString());

		boolean AFFICHER = true;
		boolean AUCUN_AFFICHAGE = false;
		new MainView(AFFICHER);
	}
}
