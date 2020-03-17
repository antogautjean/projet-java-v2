package org.antogautjean;

import org.antogautjean.model.Stock;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {


		Stock stock = new Stock("Principal");

		new FileImporter("./src/main/java/org/antogautjean/files/elements.csv", "./src/main/java/org/antogautjean/files/prix.csv", stock);

		System.out.println(stock.getProduct("E002").toString());

	}
}

