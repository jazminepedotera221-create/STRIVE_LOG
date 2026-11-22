public class Violation {

    private int id;
    private String studentId;
    private String studentName;
    private String type;
    private String description;
    private String reportedBy;
    private String status;

    public Violation(int id, String studentId, String studentName,
            String type, String description, String reportedBy) {

        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.type = type;
        this.description = description;
        this.reportedBy = reportedBy;
        this.status = "Pending";
    }

    public int getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}