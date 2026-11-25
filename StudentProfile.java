
import javax.swing.*;
import java.awt.*;

public class StudentProfile extends JPanel {

    public StudentProfile(Student student) {
        setLayout(new GridLayout(4, 1, 5, 5));

        add(new JLabel("STUDENT PROFILE"));

        add(new JLabel("Student ID: " + student.getId()));

        add(new JLabel("Student Name: " + student.getName()));

        add(new JLabel("Role: " + student.getRole()));
    }
}
