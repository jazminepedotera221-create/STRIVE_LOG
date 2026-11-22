
import javax.swing.*;
import java.util.ArrayList;

public class StudentManagement {

    private ArrayList<Student> students = new ArrayList<>();

    // ADD STUDENT
    public void addStudent() {

        JTextField studentId = new JTextField();
        JTextField studentName = new JTextField();
        JPasswordField password = new JPasswordField();

        JPanel form = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));

        form.add(new JLabel("Student ID:"));
        form.add(studentId);

        form.add(new JLabel("Student Name:"));
        form.add(studentName);

        form.add(new JLabel("Password:"));
        form.add(password);

        int result = JOptionPane.showConfirmDialog(
                null, form, "Add Student",
                JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String id = studentId.getText().trim();
        String name = studentName.getText().trim();
        String pass = new String(password.getPassword());

        if (id.isEmpty() || name.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please complete all fields.");
            return;
        }

        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                JOptionPane.showMessageDialog(null,
                        "Student ID already exists.");
                return;
            }
        }

        students.add(new Student(id, pass, name));

        JOptionPane.showMessageDialog(null,
                "Student added successfully!");
    }

    // VIEW STUDENTS
    public void viewStudents() {

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No student records found.");
            return;
        }

        StringBuilder records = new StringBuilder();

        for (Student student : students) {
            records.append("Student ID: ")
                    .append(student.getId())
                    .append("\nStudent Name: ")
                    .append(student.getName())
                    .append("\n-------------------------\n");
        }

        JTextArea area = new JTextArea(records.toString());
        area.setEditable(false);

        JOptionPane.showMessageDialog(null,
                new JScrollPane(area),
                "Student Records",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // DELETE STUDENT
    public void deleteStudent() {

        String id = JOptionPane.showInputDialog(
                null, "Enter Student ID:");

        if (id == null || id.trim().isEmpty()) {
            return;
        }

        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id.trim())) {
                students.remove(student);

                JOptionPane.showMessageDialog(null,
                        "Student deleted successfully!");
                return;
            }
        }

        JOptionPane.showMessageDialog(null,
                "Student not found.");
    }
}
