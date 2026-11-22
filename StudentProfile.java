
import javax.swing.*;
import java.awt.*;

public class StudentProfile extends JPanel {

    public StudentProfile(Student student) {

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(13, 29, 40));

        JLabel title = new JLabel("STUDENT PROFILE");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel details = new JPanel(new GridLayout(0, 1, 10, 10));
        details.setBackground(new Color(13, 29, 40));
        details.setBorder(
                BorderFactory.createEmptyBorder(30, 40, 30, 40));

        addDetail(details, "Student ID:", student.getId());
        addDetail(details, "Student Name:", student.getName());
        addDetail(details, "Role:", student.getRole());

        add(title, BorderLayout.NORTH);
        add(details, BorderLayout.CENTER);
    }

    private void addDetail(JPanel panel, String label, String value) {

        JLabel detail = new JLabel(label + " " + value);
        detail.setFont(new Font("Arial", Font.PLAIN, 17));
        detail.setForeground(Color.WHITE);

        panel.add(detail);
    }
}
