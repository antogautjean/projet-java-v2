package org.antogautjean.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.Controller.StaffController;

public class FileImporter {
    public static StockController fileToStock(String productFilePath, String priceFilePath)
            throws IOException {
        StockController stock = new StockController();
        // Products
        BufferedReader csvReader = new BufferedReader(new java.io.FileReader(productFilePath));
        String row;
        csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            try {
                String[] line = row.split(";");

                String code = line[0];
                String name = line[1];

                int quantity = Integer.parseInt(line[2]);
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

                currentProduct.setDemand(Integer.parseInt(line[3]));
            }
        }
        csvReader.close();
        return stock;
    }

    private static HashMap<String, Integer> stringToHashMap(String input) {
        HashMap<String, Integer> output = new HashMap<>();
        String[] inputs = input.substring(1, input.length() - 1).split("\\),\\(");
        String[] tmp;
        try {
            for (String str : inputs) {
                tmp = str.split(" ?, ?");
                output.put(tmp[0], Integer.parseInt(tmp[1]));
            }
        } catch (IndexOutOfBoundsException e) {
            // TODO: (un jour) Prévenir l'utilisateur que le parsing ne s'est pas bien passé
            System.err.println("Désolé mais le fichier CSV n'a pas pu être parsé correctement");
        }
        return output;
    }

    public static FactoryController fileToFactory(String lineFilePath, StockController stockController)
            throws IOException {
        BufferedReader csvReader = new BufferedReader(new java.io.FileReader(lineFilePath));

        String row;
        csvReader.readLine();

        HashMap<String, ProductionLine> factoryLines = new HashMap<>();
        for (int i = 1; (row = csvReader.readLine()) != null; i++) {
            String[] line = row.split(";");

            factoryLines.put(line[0],
                    new ProductionLine(line[0], line[1], FileImporter.stringToHashMap(line[2]),
                            FileImporter.stringToHashMap(line[3]), Integer.parseInt(line[4]), Integer.parseInt(line[5]),
                            Integer.parseInt(line[6]), i));
        }
        FactoryController factory = new FactoryController(factoryLines, stockController);

        csvReader.close();
        
        return factory;
    }

    public static StaffController fileToStaff(String staffFilePath) throws IOException {
    	  StaffController staff = new StaffController();
		  BufferedReader csvReader = new BufferedReader(new java.io.FileReader(staffFilePath));

          String row;
          csvReader.readLine();
          try {
	          while ((row = csvReader.readLine()) != null) {


	              String[] line = row.split(";");
	              String[] tempPlanning = new String[35];

	              for (int j = 2; j < line.length; j++) {
	            	  tempPlanning[j-2] = line[j];
	              }

	              staff.addEmployee(new Employee(line[0], line[1], tempPlanning));
	          }            
       	  } catch (Exception e) {
                e.getMessage();
       	  }

          csvReader.close();

          return staff;
    }
}
