import java.util.ArrayList;
import java.util.Scanner;

public class ViolationManager {

    static void addViolation(User reporter,
            ArrayList<Violation> violations,
            Scanner input, int nextViolationId) {

        System.out.println("\n====== ADD VIOLATION RECORD ======");
        System.out.print("Enter Student ID: ");
        String studentId = input.nextLine();

        System.out.print("Enter Student Name: ");
        String studentName = input.nextLine();

        System.out.println("\nViolation Type:\n1. Minor Violation\n2. Major Violation");
        System.out.print("Choose type: ");
        String choice = input.nextLine();

        if (!choice.equals("1") && !choice.equals("2")) {
            System.out.println("Invalid violation type!");
            return;
        }

        String type = choice.equals("1") ? "Minor Violation" : "Major Violation";

        System.out.print("Enter Description: ");
        String description = input.nextLine();

        violations.add(new Violation(nextViolationId, studentId,
                studentName, type, description, reporter.getName()));

        System.out.println("Violation record added successfully!");
    }

    static void viewAllRecords(ArrayList<Violation> violations) {

        System.out.println("\n====== ALL VIOLATION RECORDS ======");

        if (violations.isEmpty()) {
            System.out.println("No violation records found.");
            return;
        }

        for (Violation v : violations) {
            v.displayRecord();
        }
    }

    static void viewMyRecords(Student student,
            ArrayList<Violation> violations) {

        System.out.println("\n====== MY VIOLATION RECORDS ======");
        boolean found = false;

        for (Violation v : violations) {
            if (v.getStudentId().equals(student.getId())) {
                v.displayRecord();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No violation records found.");
        }
    }

    static void updateStatus(ArrayList<Violation> violations,
            Scanner input) {

        System.out.println("\n====== UPDATE CASE STATUS ======");

        if (violations.isEmpty()) {
            System.out.println("No records available.");
            return;
        }

        viewAllRecords(violations);

        try {
            System.out.print("Enter Violation ID: ");
            int id = Integer.parseInt(input.nextLine());

            for (Violation v : violations) {
                if (v.getId() == id) {
                    System.out.println("1. Pending\n2. Resolved");
                    System.out.print("Choose new status: ");
                    String choice = input.nextLine();

                    if (choice.equals("1"))
                        v.setStatus("Pending");
                    else if (choice.equals("2"))
                        v.setStatus("Resolved");
                    else {
                        System.out.println("Invalid status!");
                        return;
                    }

                    System.out.println("Case status updated!");
                    return;
                }
            }

            System.out.println("Violation ID not found!");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid ID!");
        }
    }
}
