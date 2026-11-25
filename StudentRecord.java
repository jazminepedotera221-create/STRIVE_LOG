
import javax.swing.*;
import java.awt.*;

public class StudentRecord extends JPanel {

    private final JTextArea recordArea;

    public StudentRecord() {
        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel(
                "STUDENT RECORDS",
                SwingConstants.CENTER);

        heading.setFont(new Font("Arial", Font.BOLD, 18));

        recordArea = new JTextArea();
        recordArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(recordArea);

        add(heading, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addRecord(String record) {
        recordArea.append(record + "\n");
    }

    public void clearRecords() {
        recordArea.setText("");
    }
}
