
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class DashboardPanel extends JPanel {

    private STRIVELOG app;
    private JPanel dashboardContent;

    private final Color red = new Color(170, 0, 35);
    private final Color lightRed = new Color(220, 20, 60);
    private final Color white = Color.WHITE;
    private final Color gray = Color.GRAY;

    public DashboardPanel(STRIVELOG app) {
        this.app = app;
        setLayout(new BorderLayout());
        setOpaque(false);
    }

    public void showStudentDashboard() {
        JPanel dashboard = createDashboard();

        addDashboardHeading(
                "Check Your Violations",
                "Enter your details to view your record.");

        addDashboardButton("MY VIOLATION RECORDS",
                () -> app.showMyRecords());

        addDashboardButton("FILE A COMPLAINT",
                () -> app.fileComplaint());

        addDashboardButton("LOG OUT",
                () -> app.logout());

        displayDashboard(dashboard);
    }

    public void showStaffDashboard() {
        JPanel dashboard = createDashboard();

        addDashboardHeading(
                "Manage Student Violations",
                "Select a function to continue.");

        addDashboardButton("ADD VIOLATION",
                () -> app.addViolation());

        addDashboardButton("VIEW ALL RECORDS",
                () -> app.showAllRecords());

        addDashboardButton("LOG OUT",
                () -> app.logout());

        displayDashboard(dashboard);
    }

    public void showGuidanceDashboard() {
        JPanel dashboard = createDashboard();

        addDashboardHeading(
                "Guidance Dashboard",
                "Manage student cases and complaints.");

        addDashboardButton("ADD VIOLATION",
                () -> app.addViolation());

        addDashboardButton("VIEW ALL RECORDS",
                () -> app.showAllRecords());

        addDashboardButton("UPDATE CASE STATUS",
                () -> app.updateStatus());

        addDashboardButton("VIEW STUDENT COMPLAINTS",
                () -> app.showComplaints());

        addDashboardButton("LOG OUT",
                () -> app.logout());

        displayDashboard(dashboard);
    }

    public void showInstructorDashboard() {
        JPanel dashboard = createDashboard();

        addDashboardHeading(
                "Faculty Dashboard",
                "Manage and view student violation records.");

        addDashboardButton("ADD VIOLATION",
                () -> app.addViolation());

        addDashboardButton("VIEW ALL RECORDS",
                () -> app.showAllRecords());

        addDashboardButton("LOG OUT",
                () -> app.logout());

        displayDashboard(dashboard);
    }

    private JPanel createDashboard() {

        removeAll();

        JPanel dashboard = new JPanel(new BorderLayout());
        dashboard.setOpaque(false);

        dashboardContent = new JPanel();
        dashboardContent.setOpaque(false);
        dashboardContent.setBorder(
                new EmptyBorder(25, 20, 25, 20));

        dashboardContent.setLayout(
                new BoxLayout(dashboardContent, BoxLayout.Y_AXIS));

        JPanel centerContent = new JPanel(new GridBagLayout());
        centerContent.setOpaque(false);
        centerContent.add(dashboardContent);

        JScrollPane scrollPane = new JScrollPane(centerContent);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel navigation = new JPanel(new GridLayout(1, 3));
        navigation.setBackground(new Color(5, 10, 17, 245));
        navigation.setBorder(
                new EmptyBorder(10, 10, 10, 10));

        JButton home = createNavButton("Home");
        JButton records = createNavButton("Records");
        JButton settings = createNavButton("Settings");

        home.addActionListener(e -> app.showDashboard());

        records.addActionListener(e -> {
            if (app.isStudent()) {
                app.showMyRecords();
            } else {
                app.showAllRecords();
            }
        });

        settings.addActionListener(e -> app.showSettings());

        navigation.add(home);
        navigation.add(records);
        navigation.add(settings);

        dashboard.add(scrollPane, BorderLayout.CENTER);
        dashboard.add(navigation, BorderLayout.SOUTH);

        add(dashboard, BorderLayout.CENTER);

        revalidate();
        repaint();

        return dashboard;
    }

    private void addDashboardHeading(
            String title, String subtitle) {

        JLabel heading = new JLabel(title);
        heading.setFont(new Font("Arial", Font.BOLD, 27));
        heading.setForeground(white);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel description = new JLabel(subtitle);
        description.setFont(new Font("Arial", Font.PLAIN, 14));
        description.setForeground(gray);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        dashboardContent.add(heading);
        dashboardContent.add(Box.createVerticalStrut(10));
        dashboardContent.add(description);
        dashboardContent.add(Box.createVerticalStrut(30));
    }

    private void addDashboardButton(
            String text, Runnable action) {

        JButton button = createButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(450, 52));
        button.setPreferredSize(new Dimension(450, 52));

        button.addActionListener(e -> action.run());

        dashboardContent.add(button);
        dashboardContent.add(Box.createVerticalStrut(15));
    }

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(white);
        button.setBackground(red);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(13, 25, 13, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(lightRed);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(red);
            }
        });

        return button;
    }

    private JButton createNavButton(String text) {

        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.PLAIN, 13));
        button.setForeground(white);
        button.setBackground(new Color(4, 12, 19));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private void displayDashboard(JPanel dashboard) {
        revalidate();
        repaint();
    }
}
