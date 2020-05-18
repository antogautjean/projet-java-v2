package org.antogautjean.model;

import java.util.Arrays;

/**
 * Classe représentant un employé
 */
public class Employee {
	private String code;
	private String qualification;
	private String[] planning;

	/**
	 *
	 * @param code le code de l'employé
	 * @param qualification la qualification de l'employé (NQ / Q)
	 * @param planning le planning de l'employé (Tableau de 35 colonnes)
	 */
	public Employee(String code, String qualification, String[] planning) {
		this.code = code;
		this.qualification = qualification;
		this.planning = planning;
	}

	/**
	 * Permet d'obtenir le code de l'employé
	 * @return le code de l'employer
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Permet de modifier le code de l'employé
	 * @param code le nouveau code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Permet d'obtenir la qualification de l'employé
	 * @return la qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * Permet de modifier la qualification de l'employé
	 * @param qualification La nouvelle qualification
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * Permet d'obtenir le planning de l'employé
	 * @return le planning
	 */
	public String[] getPlanning() {
		return planning;
	}

	/**
	 * Permet d'obtenir le créneau de l'employé à l'heure i
	 * @param i L'heure précise du planning souhaité
	 * @return l'heure i du planning
	 */
	public String getPlanningOn(int i) {
		return this.planning[i];
	}

	/**
	 * @param planning Le nouveau planning
	 */
	public void setPlanning(String[] planning) {
		this.planning = planning;
	}

	public String toString() {
		return "Employee [code=" + code + ", qualification=" + qualification + ", planning=" + Arrays.toString(planning)
				+ "]";
	}

	/**
	 * Permet de cloner l'objet employé
	 * @return un objet de type employé
	 */
	public Employee clone() {
		Employee e = new Employee(this.code, this.qualification, this.planning);
		return e;
	}
}
