package com.studentsApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static List<Student> studentsList;
	private static Scanner scan;

	public static void main(String[] args) {

		System.out.println("******** Student Management System ********");

		studentsList = new ArrayList<Student>();

		while (true) {
			System.out.println("******** Welcome ********\n");
			scan = new Scanner(System.in);
			System.out.println("Select an Option.....");
			System.out.println("1: Register Student");
			System.out.println("2: Find Student with Student ID");
			System.out.println("3: List all student Information");
			System.out.println("4: Sorted Student Information");
			System.out.println("5: Exit");

			int inputOption = scan.nextInt();

			switch (inputOption) {
			case 1:
				enrollStudent(scan);
				break;
			case 2:
				findStudentById(scan);
				break;
			case 3:
				printAllStudentData();
				break;
			case 4:
				sortByName();
				break;
			case 5:
				exit();
				break;

			default:
				System.err.println("Invalid Option Selected");
				System.out.println("Select an option from 1-5");
				break;
			}
		}

	}

	private static void exit() {

		System.out.println("Good Bye!!");
		System.exit(0);
	}

	private static void printAllStudentData() {

		if (studentsList.size() > 0) {
			System.out.println("---------------------------All Students Data------------------------------");
			for (Student student : studentsList) {
				student.printStudentInfo();
			}
			System.out.println("---------------------------************************------------------------------");
		} else {
			System.err.println("Students List is Empty\n");
		}

	}

	private static void findStudentById(Scanner scan2) {

		Student studentFound = null;
		System.out.println("Enter Student ID: ");
		String studentID = scan2.next();

		try {
			studentFound = studentsList.stream().filter(student -> student.getStudentId().equalsIgnoreCase(studentID))
					.findFirst().orElseThrow(() -> new RuntimeException("No Data Found!!"));
			;

		} catch (RuntimeException e) {

			System.err.println("Student with ID: " + studentID + " Not Found");
		}
		studentFound.printStudentInfo();

	}

	public static void enrollStudent(Scanner scan2) {

		System.out.println("Enter Student Name: ");
		String studentName = scan2.next();

		System.out.println("Enter Student Age: ");
		int studentAge = scan2.nextInt();

		System.out.println("Enter Student ID: ");
		String studentId = scan2.next();

		Student newStudent = new Student(studentName, studentAge, studentId);
		studentsList.add(newStudent);

		while (true) {
			System.out.println("Enter course name to be enrolled: Enter \"DONE\" to exit ");
			String courseName = scan.next();
			if (courseName.equalsIgnoreCase("DONE")) {
				break;
			}
			newStudent.enrollCourse(courseName);
		}
		newStudent.printStudentInfo();

	}

	private static void sortByName() {

		Comparator<Student> studentNameComparator = (o1, o2) -> o1.getName().compareTo(o2.getName());

		/*
		 * new Comparator<Student>() {
		 * 
		 * @Override public int compare(Student o1, Student o2) { return
		 * o1.getName().compareTo(o2.getName()); } };
		 */

		Collections.sort(studentsList, studentNameComparator);
		printAllStudentData();
	}

	public static Student findStudentById(String studentId) {

		Student result = null;

		try {
			result = studentsList.stream().filter(x -> x.getStudentId().equalsIgnoreCase(studentId)).findFirst()
					.orElseThrow(() -> new RuntimeException("No Data Found!!"));
		} catch (RuntimeException e) {
			System.err.println("Student with ID: " + studentId + " not found!");
		}
		return result;

	}

}
