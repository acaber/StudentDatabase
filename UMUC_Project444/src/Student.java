/**
 * FileName: Student.java
 * Author: Rebecca Johnson
 * Date: 8/2/2017
 * Purpose: Performs gpa calculations and holds students data for database
 */
public class Student {

	//fields needed to create student database
	private String studentName;
	private String studentMajor;
	private int credits;
	private double qualityPoints;

	//constructor
	public Student(String studentName, String major) {
		this.studentName = studentName;
		this.studentMajor = major;
		credits = 0;
		qualityPoints = 0;
	}
	
	//courseCompleted method
	public void courseCompleted(double grade, int creditHours) {
		
		//accumulates points for each student
		this.qualityPoints += (creditHours * grade);
		
		//accumulates credits for each student
		this.credits += creditHours;
	}
	
	//overridden toString method
	public String toString() {
		
		//displays students name, major, and gpa
		return String.format("Name: %s %nMajor: %s %nGPA: %.2f", 
				studentName, studentMajor, gpa(this.qualityPoints, this.credits));
	}
	
	//calculates gpa
	private double gpa(double points, double creditHours) {
		
		if(this.credits == 0) 
			return 4;
		else {
			double gpa = Math.round((((this.qualityPoints / this.credits) * 100.0))) / 100.0;
			return gpa;
		}
	}
	
	//returns students name
	public String getName() {
		return studentName;
	}
	
	//returns students major
	public String getMajor() {
		return studentMajor;
	}
}
