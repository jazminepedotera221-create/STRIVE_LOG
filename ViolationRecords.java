
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ViolationRecords {

    private STRIVELOG app;

    public ViolationRecords(STRIVELOG app) {
        this.app = app;
    }

    public void showRecords(ArrayList<Violation> records) {

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setBorder(new EmptyBorder(15, 15, 15, 15));

        if (records.isEmpty()) {
            area.setText("No violation records found.");
        } else {
            for (Violation v : records) {
                area.append("VIOLATION ID: " + v.getId() + "\n");
                area.append("STUDENT ID: " + v.getStudentId() + "\n");
                area.append("STUDENT NAME: " + v.getStudentName() + "\n");
                area.append("TYPE: " + v.getType() + "\n");
                area.append("DESCRIPTION: " + v.getDescription() + "\n");
                area.append("REPORTED BY: " + v.getReportedBy() + "\n");
                area.append("STATUS: " + v.getStatus() + "\n");
                area.append("----------------------------------\n\n");
            }
        }

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(650, 400));

        JOptionPane.showMessageDialog(
                app, scroll, "Violation Records",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
