package org.antogautjean.model;

import org.antogautjean.view.App;
import org.antogautjean.Controller.StockController;

import java.io.*;

public class FileImporter {

    public FileImporter(String productFilePath, String priceFilePath, StockController stock) throws IOException {

        App.LOGGER.log(0, "App", "FileImporter", "Reading CSV Stock file");

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
            }
            catch (Exception e){
                e.getMessage();
            }
        }

        csvReader = new BufferedReader(new java.io.FileReader(priceFilePath));
        csvReader.readLine();

        while ((row = csvReader.readLine()) != null) {

            String[] line = row.split(";");

            if (stock.getProduct(line[0]) != null){

                if (!line[1].equals("NA"))
                    stock.getProduct(line[0]).setBuyPrice((double) Integer.parseInt(line[1]));
                if (!line[2].equals("NA"))
                    stock.getProduct(line[0]).setSellPrice((double) Integer.parseInt(line[2]));

                stock.getProduct(line[0]).setDemand((double) Integer.parseInt(line[3]));
            }
        }

        csvReader.close();

    }

    public FileImporter(String lineFilePath, FactoryModel factory) throws IOException {

        App.LOGGER.log(0, "App", "FileImporter", "Reading CSV file");

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
