package org.antogautjean.model;

import java.util.Arrays;

public class Employee {
    private String code;
    private String qualification;
    private int[] planning;

	public Employee(String code, String qualification, int[] planning) {
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
	public int[] getPlanning() {
		return planning;
	}

	/**
	 * @param planning the planning to set
	 */
	public void setPlanning(int[] planning) {
		this.planning = planning;
	}

	
	public String toString() {
		return "Employee [code=" + code + ", qualification=" + qualification + ", planning=" + Arrays.toString(planning)
				+ "]";
	}
    
    public Employee clone() {
        Employee e = new Employee(this.code, this.qualification, this.planning);
        return e;
    }
	
    


}
