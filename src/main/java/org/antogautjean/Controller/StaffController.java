package org.antogautjean.Controller;

import java.util.HashMap;
import java.util.Map;

import org.antogautjean.model.Employee;
import org.antogautjean.model.FileImporter;
import org.antogautjean.view.components.TableRowFormatInterface;

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

    public void addEmployee(Employee employee) {
        // tester si le produit existe deja dans la base de donnÃ©e
        try {
            this.staff.put(employee.getCode(), employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
                    employee.getPlanningOn(21), employee.getPlanningOn(22), employee.getPlanningOn(23) };
            employeeIndex++;
        }

        return output;
    }

    @Override
    public void refreshFromFile() {
        this.staff = new HashMap<>();
        FileImporter.fileToStaff(this);
    }
}
