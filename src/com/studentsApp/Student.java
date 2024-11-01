package com.studentsApp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
	// Instance Variables, Marked with private keyword, non-static
	private String name;
	private int age;
	private String studentId;
	private List<String> courses;

	// Create a constructor, cuz'instance variables needs to be initialize within
	// the constructor
	public Student(String name, int age, String studentId) {
		super(); // calling parent class constructor
		if (validateAge(age) && validateName(name) && validateStudentId(studentId)) {
			this.name = name;
			this.age = age;
			this.studentId = studentId;

			courses = new ArrayList<String>(); // Initialization of courses

		}

	}

	private boolean validateStudentId(String studentId) {
		String studentIdRegex = "S-\\d+$"; // S-123234
		Pattern studentIdPattern = Pattern.compile(studentIdRegex);
		Matcher studentIdMatcher = studentIdPattern.matcher(studentId);

		if (studentIdMatcher.matches()) {
			return true;
		} else {
			System.err.println("Student ID format should be \"S-1245\" ");
			return false;
		}

	}

	public void enrollCourse(String course) {

		// use !(not) because arrays can have duplicate values

		if (validateCourseName(course)) {
			if (!courses.contains(course)) { // checking if certain object is part of the arraylist
				courses.add(course);
				System.out.println( name + " Enrolled to " + course + " Successfully\n");

			} else {

				System.err.println("Student already enrolled to this course");

			}
		}

	}

	public void printStudentInfo() {
		System.out.println("******** Student Data ********\n");
		System.out.println("Student Name: " + name);
		System.out.println("Student Age: " + age);
		System.out.println("Student ID: " + studentId);
		System.out.println("Student Enrolled Courses: " + courses + "\n");
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", studentId=" + studentId + ", courses=" + courses + "]";
	}

	// Validation Methods, error handling with invalid values
	public boolean validateAge(int age) {

		if (age >= 19 && age <= 35) {
			return true;
		} else {
			System.err.println("Invalid age!! Student age should be between 19-35");
			return false;
		}

	}

	public boolean validateName(String name) {
		// ^ starting from
		// white spaces \\s
		// end with +$
		String namePatternRegx = "^[a-zA-Z\\s]+$";
		Pattern namePattern = Pattern.compile(namePatternRegx);
		Matcher nameMatcher = namePattern.matcher(name);

		if (nameMatcher.matches()) {
			return true;
		} else {

			System.err.println("Name cannot include Numbers or Special characters");
			return false;
		}

	}

	public boolean validateCourseName(String course) {

		if (course.equalsIgnoreCase("Java") || course.equalsIgnoreCase("Python")
				|| course.equalsIgnoreCase("JavaScript")) {
			return true;
		} else {

			System.err.println("Invalid Course name. Available courses are [Java, Python , JavaScript]");
			return false;
		}
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getStudentId() {
		return studentId;
	}

	public List<String> getCourses() {
		return courses;
	}
	

}
