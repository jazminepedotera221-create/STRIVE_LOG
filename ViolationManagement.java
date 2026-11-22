
import javax.swing.*;
import java.awt.*;

public class ViolationManagement {

    private STRIVELOG app;

    public ViolationManagement(STRIVELOG app) {
        this.app = app;
    }

    public void addViolation() {

        JTextField studentId = new JTextField();
        JTextField studentName = new JTextField();

        String[] categories = {
                "Minor Violation",
                "Major Violation"
        };

        String[] minorTypes = {
                "Tardiness",
                "Improper Attire",
                "Failure to Wear Student ID",
                "Littering",
                "Disruptive Behavior",
                "Unauthorized Use of Mobile Devices",
                "Unauthorized Use of Facilities",
                "Improper Use of Campus Property",
                "Unexcused Absence"
        };

        String[] majorViolations = {
                "Bullying",
                "Physical Assault",
                "Verbal Abuse",
                "Harassment",
                "Vandalism",
                "Theft",
                "Academic Dishonesty",
                "Falsification of Documents",
                "Serious Threats and Intimidation",
                "Unauthorized Access to University Systems"
        };

        JComboBox<String> categoryBox = new JComboBox<>(categories);
        JComboBox<String> typeBox = new JComboBox<>(minorTypes);

        categoryBox.addActionListener(e -> {
            typeBox.removeAllItems();

            String selected = (String) categoryBox.getSelectedItem();

            String[] selectedTypes = "Minor Violation".equals(selected)
                    ? minorTypes
                    : majorViolations;

            for (String type : selectedTypes) {
                typeBox.addItem(type);
            }
        });

        JTextArea description = new JTextArea(4, 20);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        JPanel form = new JPanel(new GridLayout(0, 1, 5, 5));

        form.add(new JLabel("Student ID:"));
        form.add(studentId);
        form.add(new JLabel("Student Name:"));
        form.add(studentName);
        form.add(new JLabel("Violation Category:"));
        form.add(categoryBox);
        form.add(new JLabel("Violation Type:"));
        form.add(typeBox);
        form.add(new JLabel("Description:"));
        form.add(new JScrollPane(description));

        int result = JOptionPane.showConfirmDialog(
                app, form, "Add Violation",
                JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION)
            return;

        if (studentId.getText().trim().isEmpty()
                || studentName.getText().trim().isEmpty()
                || description.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(app,
                    "Please complete all fields.");
            return;
        }

        STRIVELOG.Violation violation = new STRIVELOG.Violation(
                app.getNextViolationId(),
                studentId.getText().trim(),
                studentName.getText().trim(),
                (String) typeBox.getSelectedItem(),
                description.getText().trim(),
                app.getLoggedInUser().getName());

        app.addViolationRecord(violation);

        JOptionPane.showMessageDialog(app,
                "Violation added successfully!");
    }
}
