package org.antogautjean.Controller;

import java.util.HashMap;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.antogautjean.model.Employee;
import org.antogautjean.view.elements.TableLinesFormatInterface;

public class StaffController implements TableLinesFormatInterface {
  private HashMap<String, Employee> staff = new HashMap<>();

    public boolean addEmployee(Employee employee){
        //tester si le produit existe deja dans la base de donnÃ©e
        try {
            this.staff.put(employee.getCode(), employee);
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteEmployee(String code){
        try {
            this.staff.remove(code);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteEmployee(Employee employee){

        return this.deleteEmployee(employee.getCode());

    }

    public Employee getProduct(String code){
        try {
            return this.staff.get(code);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public HashMap<String, Employee> getStock(){
        return this.staff;
    }

    public String toString(){
        return "Il y a " + this.staff.size() + " employes parmit le staff (valeur <sum>)." ;
    }

    public StaffController clone() {
        StaffController s = new StaffController();
        for (Employee employee : this.staff.values()) {
            s.addEmployee(employee.clone());
        }
        return s;
    }

    public int getNbEmployee() {
        int output = 0;
        for (Employee Employee : this.staff.values()) {
        	output++;
        }
        return output;
    }

    @Override
    public Object[][] getTableStaffFormat() {
        Object[][] output = new Object[staff.size()][26]; // 26 = amount of columns
        int employeeIndex = 0;
        for(String key : staff.keySet()) {
            Employee employee = staff.get(key);

            output[employeeIndex] = new Object[] {
                employee.getCode(),
                employee.getQualification(),
                employee.getPlanning(0),
                employee.getPlanning(1),
                employee.getPlanning(2),
                employee.getPlanning(3),
                employee.getPlanning(4),
                employee.getPlanning(5),
                employee.getPlanning(6),
                employee.getPlanning(7),
                employee.getPlanning(8),
                employee.getPlanning(9),
                employee.getPlanning(10),
                employee.getPlanning(11),
                employee.getPlanning(12),
                employee.getPlanning(13),
                employee.getPlanning(14),
                employee.getPlanning(15),
                employee.getPlanning(16),
                employee.getPlanning(17),
                employee.getPlanning(18),
                employee.getPlanning(19),
                employee.getPlanning(20),
                employee.getPlanning(21),
                employee.getPlanning(22),
                employee.getPlanning(23),
                };
            employeeIndex++;
        }

        return output;
    }

    @Override
    public Object[][] getTableLineFormat() {
        return new Object[0][];
    }
}
