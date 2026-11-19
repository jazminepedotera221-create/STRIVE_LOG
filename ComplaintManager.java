import java.util.ArrayList;
import java.util.Scanner;

public class ComplaintManager {

    static void fileComplaint(Student student,
            ArrayList<String> complaints, Scanner input) {

        System.out.println("\n====== FILE A COMPLAINT ======");
        System.out.print("Enter your complaint: ");
        String complaint = input.nextLine();

        if (complaint.trim().isEmpty()) {
            System.out.println("Complaint cannot be empty.");
            return;
        }

        complaints.add("Student ID: " + student.getId()
                + "\nStudent Name: " + student.getName()
                + "\nComplaint: " + complaint);

        System.out.println("Complaint submitted successfully!");
    }

    static void viewComplaints(ArrayList<String> complaints) {

        System.out.println("\n====== STUDENT COMPLAINTS ======");

        if (complaints.isEmpty()) {
            System.out.println("No complaints found.");
            return;
        }

        for (int i = 0; i < complaints.size(); i++) {
            System.out.println("\nComplaint #" + (i + 1));
            System.out.println(complaints.get(i));
            System.out.println("--------------------------------");
        }
    }
}