package org.antogautjean.model;

import java.util.Arrays;

public class Employee {
    private String code;
    private String qualification;
    private String[] planning;

	public Employee(String code, String qualification, String[] planning) {
		this.code = code;
		this.qualification = qualification;
		this.planning = planning;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * @return the planning
	 */
	public String[] getPlanning() {
		return planning;
	}

	public String getPlanningOn(int i){
		return planning[i];
	}

	/**
	 * @param planning the planning to set
	 */
	public void setPlanning(String[] planning) {
		this.planning = planning;
	}

	
	public String toString() {
		return "Employee [code=" + code + ", qualification=" + qualification + ", planning=" + Arrays.toString(planning)
				+ "]";
	}
    
    public Employee clone() throws CloneNotSupportedException {
		return (Employee) super.clone();
    }
	
    


}
