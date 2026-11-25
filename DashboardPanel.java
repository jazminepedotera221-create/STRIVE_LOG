
import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

        private final User loggedInUser;

        public DashboardPanel(User user) {
                loggedInUser = user;

                setLayout(new BorderLayout(10, 10));
                setBackground(Color.WHITE);

                JLabel heading = new JLabel(
                                "Welcome, " + loggedInUser.getName(),
                                SwingConstants.CENTER);

                heading.setFont(new Font("Arial", Font.BOLD, 22));
                add(heading, BorderLayout.NORTH);

                JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));

                JButton profileButton = new JButton("Student Profile");
                JButton recordsButton = new JButton("Student Records");
                JButton logoutButton = new JButton("Logout");

                buttonPanel.add(profileButton);
                buttonPanel.add(recordsButton);
                buttonPanel.add(logoutButton);

                add(buttonPanel, BorderLayout.CENTER);

                profileButton.addActionListener(e -> JOptionPane.showMessageDialog(
                                this,
                                "Name: " + loggedInUser.getName()
                                                + "\nID: " + loggedInUser.getId()
                                                + "\nRole: " + loggedInUser.getRole()));

                recordsButton.addActionListener(e -> JOptionPane.showMessageDialog(
                                this,
                                "Student Records"));

                logoutButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Logged out."));
        }
}
