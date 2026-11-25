import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViolationRecord extends JPanel {

    private final JTextArea recordArea;

    public ViolationRecord() {
        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel(
                "VIOLATION RECORDS",
                SwingConstants.CENTER);

        heading.setFont(new Font("Arial", Font.BOLD, 18));

        recordArea = new JTextArea();
        recordArea.setEditable(false);

        add(heading, BorderLayout.NORTH);
        add(new JScrollPane(recordArea), BorderLayout.CENTER);
    }

    public void displayRecords(ArrayList<Violation> violations) {
        recordArea.setText("");

        for (Violation violation : violations) {
            recordArea.append(
                    "Violation ID: " + violation.getViolationId()
                            + "\nStudent ID: " + violation.getStudentId()
                            + "\nStudent Name: " + violation.getStudentName()
                            + "\nType: " + violation.getViolationType()
                            + "\nDescription: " + violation.getDescription()
                            + "\nReported By: " + violation.getReportedBy()
                            + "\nStatus: " + violation.getStatus()
                            + "\n----------------------------\n");
        }
    }
}