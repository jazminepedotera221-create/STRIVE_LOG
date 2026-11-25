import java.util.ArrayList;

public class ViolationManagement {

        private final ArrayList<Violation> violations;
        private int nextViolationId;

        public ViolationManagement() {
                violations = new ArrayList<>();
                nextViolationId = 1;
        }

        public void addViolation(
                        String studentId,
                        String studentName,
                        String violationType,
                        String description,
                        String reportedBy) {

                Violation violation = new Violation(
                                nextViolationId,
                                studentId,
                                studentName,
                                violationType,
                                description,
                                reportedBy);

                violations.add(violation);
                nextViolationId++;
        }

        public ArrayList<Violation> getViolations() {
                return new ArrayList<>(violations);
        }
}
