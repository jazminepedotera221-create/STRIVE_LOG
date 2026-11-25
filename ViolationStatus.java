public class ViolationStatus {

    public void updateStatus(Violation violation, String newStatus) {

        if (violation == null || newStatus == null
                || newStatus.trim().isEmpty()) {
            return;
        }

        violation.setStatus(newStatus);
    }
}
