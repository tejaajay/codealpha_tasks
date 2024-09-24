package com.code_alpha.task1;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class StudentGrades {
    public static void main(String[] args) {
        
        ArrayList<Integer> grades = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("Enter student grades (type '-1' to stop):");
        while (true) {
            try {
                int grade = Integer.parseInt(scanner.nextLine());
                if (grade == -1) {
                    break; 
                } else if (grade < 0 || grade > 100) {
                    System.out.println("Please enter a valid grade between 0 and 100.");
                } else {
                    grades.add(grade); 
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric grade.");
            }
        }
        
        
        if (grades.isEmpty()) {
            System.out.println("No grades were entered.");
        } else {
            
            double sum = 0;
            for (int grade : grades) {
                sum += grade;
            }
            double average = sum / grades.size();
           
            int highest = Collections.max(grades);
            int lowest = Collections.min(grades);
                
            System.out.println("\n--- Results ---");
            System.out.println("Average Grade: " + average);
            System.out.println("Highest Grade: " + highest);
            System.out.println("Lowest Grade: " + lowest);
        }
        
        scanner.close();
    }
}
