package org.antogautjean.controller;

import java.util.HashMap;
import java.util.Map;

import org.antogautjean.model.Employee;
import org.antogautjean.model.FileImporter;
import org.antogautjean.view.components.table.TableRowFormatInterface;

/**
 * Classe permettant de controler un groupe d'employer
 */
public class StaffController implements TableRowFormatInterface, ControllerFromFileInterface {
    protected HashMap<String, Employee> staff = new HashMap<>();
    protected boolean fileImportFailed = false;


    @Override
    public void setIfFileImportFailed(boolean b) {
        this.fileImportFailed = b;
    }

    @Override
    public boolean getIfFileImportFailed() {
        return !this.fileImportFailed;
    }

    /**
     * Permet d'ajouter un employé à la base de donnée
     * @param employee
     */
    public void addEmployee(Employee employee) {
        // tester si l'employé existe deja dans la base de donnÃ©e
        try {
            this.staff.put(employee.getCode(), employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de supprimé un employé de la base de donnée en fonction de son code
     * @param code Le code de l'employé à supprimer
     * @return TRUE si supprimer
     */
    public boolean deleteEmployee(String code) {
        try {
            this.staff.remove(code);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteEmployee(Employee employee) {

        return this.deleteEmployee(employee.getCode());

    }

    public Employee getEmployee(String code) {
        try {
            return this.staff.get(code);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HashMap<String, Employee> getStaff() {
        return this.staff;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("Il y a " + this.staff.size() + " employes parmit le staff (valeur <sum>)\n");
        for (Employee e : this.staff.values()) {
            output.append(e.toString());
        }
        return output.toString();
    }

    /**
     * Permet de cloner un Staffcontroler
     * @return Une autre instance du StaffControler
     * @throws CloneNotSupportedException
     */
    public StaffController clone() throws CloneNotSupportedException {
        StaffController s = new StaffController();
        for (Employee employee : this.staff.values()) {
            s.addEmployee(employee.clone());
        }
        return s;
    }

    public int getNbEmployee() {
        return this.staff.size();
    }


    public void debug() {
        System.out.println(this.staff);

        for (Map.Entry<String, Employee> entry : this.staff.entrySet()) {
            String key = entry.getKey();
            Employee e = entry.getValue();

            System.out.print(key + " : ");
            for (int i = 0; i < e.getPlanning().length; i++) {
                System.out.print(e.getPlanning()[i] + " ");
            }
        }
    }

    /**
     * Permet de mettre sous la forme d'un tableau les données des employés.
     * @return
     */
    @Override
    public Object[][] getTableLineFormat() {
        Object[][] output = new Object[staff.size()][37]; // 37 = amount of columns
        int employeeIndex = 0;
        for (String key : staff.keySet()) {
            Employee employee = staff.get(key);

            output[employeeIndex] = new Object[] { employee.getCode(), employee.getQualification(),
                    employee.getPlanningOn(0), employee.getPlanningOn(1), employee.getPlanningOn(2),
                    employee.getPlanningOn(3), employee.getPlanningOn(4), employee.getPlanningOn(5),
                    employee.getPlanningOn(6), employee.getPlanningOn(7), employee.getPlanningOn(8),
                    employee.getPlanningOn(9), employee.getPlanningOn(10), employee.getPlanningOn(11),
                    employee.getPlanningOn(12), employee.getPlanningOn(13), employee.getPlanningOn(14),
                    employee.getPlanningOn(15), employee.getPlanningOn(16), employee.getPlanningOn(17),
                    employee.getPlanningOn(18), employee.getPlanningOn(19), employee.getPlanningOn(20),
                    employee.getPlanningOn(21), employee.getPlanningOn(22), employee.getPlanningOn(23),
                    employee.getPlanningOn(24), employee.getPlanningOn(25), employee.getPlanningOn(26),
                    employee.getPlanningOn(27), employee.getPlanningOn(28), employee.getPlanningOn(29),
                    employee.getPlanningOn(30), employee.getPlanningOn(31), employee.getPlanningOn(32),
                    employee.getPlanningOn(33), employee.getPlanningOn(34)};
            employeeIndex++;
        }

        return output;
    }

    /**
     * Permet de reimporter le fichier
     */
    @Override
    public void refreshFromFile() {
        this.staff = new HashMap<>();
        FileImporter.fileToStaff(this);
    }
}
