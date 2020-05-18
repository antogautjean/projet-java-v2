package org.antogautjean.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import org.antogautjean.Controller.ConfigController;
import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StaffController;
import org.antogautjean.Controller.StockController;

public class FileImporter {
    protected static final String elements_csv_header = "Code;Nom;Quantite;unite";
    protected static final String prix_csv_header = "Code;achat;vente;Demande";
    protected static final String chaines_csv_header = "Code;Nom;Entree (code,qte);Sortie (code,qte);Temps;"
            + "Personnels non qualifies;Personnels qualifies";
    protected static final String employes_csv_header = "Code;Qualification;0H;1H;2h;3H;4H;5H;6H;7H;8H;"
            + "9H;10H;11H;12H;13H;14H;15H;16H;17H;18H;19H;20H;21H;22H;23H";
    protected static final Exception fileWrongCSVFormat = new Exception("Le fichier n'est pas au format attendu");

    public static void fileToStock(StockController stock) throws IOException {
        // initialiser cette valeur (peut être modifié dans le cas d'une erreur)
        stock.setIfFileImportFailed(false);
        // Products
        BufferedReader csvReader = new BufferedReader(
                new java.io.FileReader(Objects.requireNonNull(ConfigController.getProperty("stockFile"))));
        String row;
        try {
            // Vérifier si la première ligne du fichier CSV est au bon format
            row = csvReader.readLine();
            if (!row.contains(elements_csv_header)) {
                throw fileWrongCSVFormat;
            } else {
                while ((row = csvReader.readLine()) != null) {
                    String[] line = row.split(";");

                    String code = line[0];
                    String name = line[1];

                    int quantity = Integer.parseInt(line[2]);
                    Unit unit = Unit.strToUnit(line[3]);

                    Product current = new Product(code, name, quantity, unit);
                    stock.addProduct(current);
                }
            }
        } catch (Exception e) {
            System.err.println("fileToStock failed");
            stock.setIfFileImportFailed(true);
        } finally {
            csvReader.close();
        }

        // If import didn't failed, then try with the second file
        if (stock.getIfFileImportFailed()) {
            // Prices
            csvReader = new BufferedReader(new java.io.FileReader(Objects.requireNonNull(ConfigController.getProperty("pricesFile"))));
            try {
                // Vérifier si la première ligne du fichier CSV est au bon format
                row = csvReader.readLine();
                if (!row.contains(prix_csv_header)) {
                    throw fileWrongCSVFormat;
                } else {
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
                }
            } catch (Exception e) {
                System.err.println("fileToStock failed");
                stock.setIfFileImportFailed(true);
            } finally {
                csvReader.close();
            }

        }
    }

    private static HashMap<String, Integer> stringToHashMap(String input) throws IndexOutOfBoundsException {
        HashMap<String, Integer> output = new HashMap<>();
        String[] inputs = input.substring(1, input.length() - 1).split("\\),\\(");
        String[] tmp;
        for (String str : inputs) {
            tmp = str.split(" ?, ?");
            output.put(tmp[0], Integer.parseInt(tmp[1]));
        }
        return output;
    }

    public static void fileToFactory(FactoryController factory, StockController stockController) {
        // initialiser cette valeur (peut être modifié dans le cas d'une erreur)
        factory.setIfFileImportFailed(false);

        try (BufferedReader csvReader = new BufferedReader(
                new java.io.FileReader(Objects.requireNonNull(ConfigController.getProperty("linesFile"))))) {
            String row;
            HashMap<String, ProductionLine> factoryLines = new HashMap<>();
            // Vérifier si la première ligne du fichier CSV est au bon format
            row = csvReader.readLine();
            if (!row.contains(chaines_csv_header)) {
                throw fileWrongCSVFormat;
            } else {
                for (int i = 1; (row = csvReader.readLine()) != null; i++) {
                    String[] line = row.split(";");

                    factoryLines.put(line[0],
                            new ProductionLine(line[0], line[1], stringToHashMap(line[2]), stringToHashMap(line[3]),
                                    Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]),
                                    i));
                }
                factory.setStockController(stockController);
                factory.setProductionLines(factoryLines);
            }
        } catch (Exception e) {
            System.err.println("fileToFactory failed");
            factory.setIfFileImportFailed(true);
        }

    }

    public static void fileToStaff(StaffController staff) {
        // initialiser cette valeur (peut être modifié dans le cas d'une erreur)
        staff.setIfFileImportFailed(false);

        try (BufferedReader csvReader = new BufferedReader(
                new java.io.FileReader(Objects.requireNonNull(ConfigController.getProperty("staffFile"))))) {
            String row;
            // Vérifier si la première ligne du fichier CSV est au bon format
            row = csvReader.readLine();
            if (!row.contains(employes_csv_header)) {
                throw fileWrongCSVFormat;
            } else {
                while ((row = csvReader.readLine()) != null) {

                    String[] line = row.split(";");
                    String[] tempPlanning = new String[35];

                    if (line.length - 2 >= 0)
                        System.arraycopy(line, 2, tempPlanning, 0, line.length - 2);

                    staff.addEmployee(new Employee(line[0], line[1], tempPlanning));
                }
            }
        } catch (Exception e) {
            System.err.println("fileToStaff failed");
            staff.setIfFileImportFailed(true);
        }
    }
}
