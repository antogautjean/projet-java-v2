package org.antogautjean;

import org.antogautjean.model.Stock;

import java.io.IOException;

public class TerminalApp {

	public static void main(String[] args) throws IOException {


		Stock stock = new Stock("Principal");

		new FileImporter("./src/main/java/org/antogautjean/data/elements.csv","./src/main/java/org/antogautjean/data/prix.csv", stock);

		System.out.println(stock.getProduct("E002").toString());

	}
}

