import java.io.*;
import java.util.*;

class Student {
    String rollNo, name, course;
    int marks;

    Student(String rollNo, String name, String course, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public String toString() {
        return rollNo + "," + name + "," + course + "," + marks;
    }
}

public class StudentManagement {
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> viewStudents();
                case 3 -> searchStudent(sc);
                case 4 -> deleteStudent(sc);
                case 5 -> { System.out.println("Exiting..."); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent(Scanner sc) throws IOException {
        System.out.print("Roll No: ");
        String roll = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Course: ");
        String course = sc.nextLine();
        System.out.print("Marks: ");
        int marks = sc.nextInt();
        sc.nextLine();

        Student s = new Student(roll, name, course, marks);
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
        bw.write(s.toString());
        bw.newLine();
        bw.close();
        System.out.println("Student added!");
    }

    static void viewStudents() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No records found.");
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        System.out.println("\nRollNo | Name | Course | Marks");
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            System.out.printf("%s | %s | %s | %s%n", data[0], data[1], data[2], data[3]);
        }
        br.close();
    }

    static void searchStudent(Scanner sc) throws IOException {
        System.out.print("Enter Roll No to search: ");
        String roll = sc.nextLine();
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(roll + ",")) {
                String[] data = line.split(",");
                System.out.printf("Found: %s | %s | %s | %s%n", data[0], data[1], data[2], data[3]);
                found = true;
                break;
            }
        }
        br.close();
        if (!found) System.out.println("Student not found.");
    }

    static void deleteStudent(Scanner sc) throws IOException {
        System.out.print("Enter Roll No to delete: ");
        String roll = sc.nextLine();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No records to delete.");
            return;
        }
        File temp = new File("temp.txt");
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
        String line;
        boolean deleted = false;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(roll + ",")) {
                deleted = true;
                continue;
            }
            bw.write(line);
            bw.newLine();
        }
        br.close();
        bw.close();
        file.delete();
        temp.renameTo(file);
        if (deleted) System.out.println("Student deleted!");
        else System.out.println("Roll No not found.");
    }
}