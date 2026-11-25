public class Violation {

    private final int violationId;
    private final String studentId;
    private final String studentName;
    private final String violationType;
    private final String description;
    private final String reportedBy;
    private String status;

    public Violation(
            int violationId,
            String studentId,
            String studentName,
            String violationType,
            String description,
            String reportedBy) {

        this.violationId = violationId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.violationType = violationType;
        this.description = description;
        this.reportedBy = reportedBy;
        this.status = "Pending";
    }

    public int getViolationId() {
        return violationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getViolationType() {
        return violationType;
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
