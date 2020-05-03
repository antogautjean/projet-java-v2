package org.antogautjean.Controller;

import java.util.HashMap;

import org.antogautjean.model.Employee;

public class StaffController {
  private HashMap<String, Employee> staff = new HashMap<>();

    public boolean addEmployee(Employee employee){
        //tester si le produit existe deja dans la base de donnée
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
        return "Il y a " + this.staff.size() + " employ�s parmit le staff (valeur <sum> €)." ;
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

}
