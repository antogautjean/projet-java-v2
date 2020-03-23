package org.antogautjean.model;

import java.io.BufferedReader;
import java.io.IOException;

import org.antogautjean.Controller.ProductionLineController;
import org.antogautjean.Controller.StockController;

public class FileImporter {
    public static void fileToStock(String productFilePath, String priceFilePath, StockController stock)
            throws IOException {
        // Products
        BufferedReader csvReader = new BufferedReader(new java.io.FileReader(productFilePath));
        String row;
        csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            try {
                String[] line = row.split(";");

                String code = line[0];
                String name = line[1];

                Integer quantity = Integer.parseInt(line[2]);
                Unit unit = Unit.strToUnit(line[3]);

                Product current = new Product(code, name, quantity, unit);
                stock.addProduct(current);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        csvReader.close();

        // Prices
        csvReader = new BufferedReader(new java.io.FileReader(priceFilePath));
        csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] line = row.split(";");

            if (stock.getProduct(line[0]) != null) {
                Product currentProduct = stock.getProduct(line[0]);

                if (!line[1].equals("NA"))
                    currentProduct.setBuyPrice((double) Integer.parseInt(line[1]));

                if (!line[2].equals("NA"))
                    currentProduct.setSellPrice((double) Integer.parseInt(line[2]));

                currentProduct.setOrderedQuantity(Integer.parseInt(line[3]));
            }
        }
        csvReader.close();
    }

    public static void fileToProductionLine(String lineFilePath, ProductionLineController factory) throws IOException {
        BufferedReader csvReader = new BufferedReader(new java.io.FileReader(lineFilePath));

        String row;
        csvReader.readLine();

        while ((row = csvReader.readLine()) != null) {
            String[] line = row.split(";");

            String code = line[0];
            String name = line[1];
            String input = line[2];
            String output = line[3];
            String duration = line[4];
            String qualified = line[5];
            String unqualified = line[6];

            System.out.println(input);

            String[] dataIn = input.split(",");

            System.out.println(dataIn.length);

            break;
        }
        csvReader.close();
    }

}
