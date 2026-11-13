
import java.util.ArrayList;
import java.util.Scanner;

public class STRIVELOG {
    static Scanner input = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Violation> violations = new ArrayList<>();
    static ArrayList<String> complaints = new ArrayList<>();
    static int nextViolationId = 1;

    public static void main(String[] args) {
        users.add(new GuidanceCounselor("GC-1001", "1234", "Guidance Counselor"));
        users.add(new Instructor("INS-1001", "1234", "Instructor"));
        users.add(new SchoolStaff("SS-1001", "1234", "School Staff"));

        System.out.println("================================================");
        System.out.println("                  STRIVE LOG");
        System.out.println(" Student Tracking and Recording of Incidents");
        System.out.println("================================================");

        login();
    }

    static void login() {
        while (true) {
            System.out.println("\n========== LOGIN ==========");
            System.out.print("Enter ID: ");
            String id = input.nextLine();
            System.out.print("Enter Password: ");
            String password = input.nextLine();

            User loggedIn = null;

            for (User user : users) {
                if (user.getId().equals(id) &&
                        user.getPassword().equals(password)) {
                    loggedIn = user;
                    break;
                }
            }

            if (loggedIn == null && id.startsWith("SR-")) {
                loggedIn = new Student(id, password, "Student");
            }

            if (loggedIn == null) {
                System.out.println("Invalid ID or Password!");
            } else {
                System.out.println("\nLogin Successful!");
                System.out.println("Welcome, " + loggedIn.getName());
                System.out.println("Role: " + loggedIn.getRole());
                showMenu(loggedIn);
            }
        }
    }

    static void showMenu(User user) {
        boolean counselor = user instanceof GuidanceCounselor;
        boolean student = user instanceof Student;
        boolean running = true;

        while (running) {
            System.out.println("\n========== MAIN MENU ==========");

            if (counselor) {
                System.out.println(
                        "1. Add Violation Record\n2. View All Violation Records\n3. Update Case Status\n4. View Student Complaints\n5. Logout");
            } else if (student) {
                System.out.println("1. View My Violation Records\n2. File a Complaint\n3. Logout");
            } else {
                System.out.println("1. Add Violation Record\n2. View All Violation Records\n3. Logout");
            }

            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }

            if (counselor) {
                switch (choice) {
                    case 1:
                        ViolationManager.addViolation(user, violations, input, nextViolationId);

                        if (!violations.isEmpty()
                                && violations.get(violations.size() - 1).getId() == nextViolationId) {
                            nextViolationId++;
                        }
                        break;
                    case 2:
                        ViolationManager.viewAllRecords(violations);
                        break;
                    case 3:
                        ViolationManager.updateStatus(violations, input);
                        break;
                    case 4:
                        ComplaintManager.viewComplaints(complaints);
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } else if (student) {
                switch (choice) {
                    case 1:
                        ViolationManager.viewMyRecords((Student) user, violations);
                        break;
                    case 2:
                        ComplaintManager.fileComplaint((Student) user, complaints, input);
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } else {
                switch (choice) {
                    case 1:
                        ViolationManager.addViolation(user, violations, input, nextViolationId);

                        if (!violations.isEmpty()
                                && violations.get(violations.size() - 1).getId() == nextViolationId) {
                            nextViolationId++;
                        }
                        break;
                    case 2:
                        ViolationManager.viewAllRecords(violations);
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }

            if (!running) {
                System.out.println("Logged out!");
            }
        }
    }
}
