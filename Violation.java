public class Violation {
    private int id;
    private String studentId, studentName, type, description, reportedBy;
    private String status = "Pending";

    public Violation(int id, String studentId, String studentName,
            String type, String description, String reportedBy) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.type = type;
        this.description = description;
        this.reportedBy = reportedBy;
    }

    public int getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayRecord() {
        System.out.println("\nViolation ID: " + id);
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        System.out.println("Violation Type: " + type);
        System.out.println("Description: " + description);
        System.out.println("Reported By: " + reportedBy);
        System.out.println("Status: " + status);
        System.out.println("--------------------------------");
    }
}
