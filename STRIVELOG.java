
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class STRIVELOG extends JFrame {

    // COLORS
    private final Color darkRed = new Color(85, 0, 15);
    private final Color red = new Color(170, 0, 35);
    private final Color lightRed = new Color(210, 25, 55);
    private final Color navy = new Color(5, 15, 23);
    private final Color cardColor = new Color(13, 29, 40);
    private final Color fieldColor = new Color(19, 37, 49);
    private final Color white = Color.WHITE;
    private final Color gray = new Color(190, 195, 205);

    // IMAGES
    private final String backgroundPath = "BSU.jpg";
    private final String logoPath = "strivelogo.png";

    private Image backgroundImage;
    private Image logoImage;

    // SCREEN SETTINGS
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel dashboardContent;

    private String selectedRole = "";
    private User loggedInUser;

    private JTextField loginId;
    private JPasswordField loginPassword;
    private JLabel loginRoleLabel;

    // RECORDS
    private final ArrayList<Violation> violations = new ArrayList<>();
    private final ArrayList<String> complaints = new ArrayList<>();

    private int nextViolationId = 1;

    // CONSTRUCTOR
    public STRIVELOG() {

        setTitle("STRIVE LOG - Student Violation and Incident Tracking System");
        setSize(1050, 680);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        loadImages();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createWelcomeScreen(), "WELCOME");
        mainPanel.add(createRoleScreen(), "ROLE");
        mainPanel.add(createLoginScreen(), "LOGIN");
        mainPanel.add(createExitScreen(), "EXIT");
        mainPanel.add(createThankYouScreen(), "THANKYOU");

        add(mainPanel);

        cardLayout.show(mainPanel, "WELCOME");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                cardLayout.show(mainPanel, "EXIT");
            }
        });
    }

    // LOAD IMAGES
    private void loadImages() {

        File backgroundFile = new File(backgroundPath);
        File logoFile = new File(logoPath);

        if (backgroundFile.exists()) {
            backgroundImage = new ImageIcon(backgroundPath).getImage();
        } else {
            System.out.println("Cannot find: " + backgroundFile.getAbsolutePath());
        }

        if (logoFile.exists()) {
            logoImage = new ImageIcon(logoPath).getImage();
        } else {
            System.out.println("Cannot find logo: " + logoFile.getAbsolutePath());
        }
    }

    // BACKGROUND PANEL
    class ImagePanel extends JPanel {

        private Image image;
        private float darkness;

        public ImagePanel(Image image, float darkness) {
            this.image = image;
            this.darkness = darkness;
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            if (image != null) {
                g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            } else {
                g2.setColor(navy);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            // LIGHT BLACK OVERLAY
            g2.setColor(new Color(0, 0, 0, (int) (darkness * 255)));
            g2.fillRect(0, 0, getWidth(), getHeight());

            // DARK RED OVERLAY
            g2.setColor(new Color(105, 0, 20, 115));
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.dispose();
        }
    }

    // LOGO
    private JLabel createLogo(int width, int height) {

        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        logo.setPreferredSize(new Dimension(width, height));
        logo.setMinimumSize(new Dimension(width, height));
        logo.setMaximumSize(new Dimension(width, height));

        if (logoImage != null) {

            int originalWidth = logoImage.getWidth(null);
            int originalHeight = logoImage.getHeight(null);

            if (originalWidth > 0 && originalHeight > 0) {

                double scale = Math.min(
                        (double) width / originalWidth,
                        (double) height / originalHeight);

                int scaledWidth = (int) (originalWidth * scale);
                int scaledHeight = (int) (originalHeight * scale);

                Image scaled = logoImage.getScaledInstance(
                        scaledWidth,
                        scaledHeight,
                        Image.SCALE_SMOOTH);

                logo.setIcon(new ImageIcon(scaled));
            }
        }

        return logo;
    }

    // TITLE
    private JLabel createTitle(String text, int size) {

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, size));
        label.setForeground(white);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        return label;
    }

    // WELCOME SCREEN
    private JPanel createWelcomeScreen() {

        ImagePanel panel = new ImagePanel(backgroundImage, 0.30f);
        panel.setLayout(new GridBagLayout());

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // LARGE CENTERED LOGO
        JLabel logo = createLogo(550, 300);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(logo);
        content.add(Box.createVerticalStrut(5));

        JLabel subtitle = new JLabel(
                "STUDENT TRACKING AND RECORDING INCIDENTS");

        subtitle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        subtitle.setForeground(white);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tagline = new JLabel(
                "Sa mas ligtas at disiplinadong paaralan.");

        tagline.setFont(new Font("Arial", Font.ITALIC, 15));
        tagline.setForeground(white);
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = createButton("GET STARTED");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setMaximumSize(new Dimension(260, 50));
        startButton.setPreferredSize(new Dimension(260, 50));

        startButton.addActionListener(e -> cardLayout.show(mainPanel, "ROLE"));

        content.add(subtitle);
        content.add(Box.createVerticalStrut(10));
        content.add(tagline);
        content.add(Box.createVerticalStrut(15));
        content.add(startButton);

        panel.add(content);

        return panel;
    }

    // SELECT USER SCREEN
    private JPanel createRoleScreen() {

        ImagePanel panel = new ImagePanel(backgroundImage, 0.32f);
        panel.setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(createLogo(210, 145));
        content.add(Box.createVerticalStrut(5));

        JLabel subtitle = new JLabel("Select User");
        subtitle.setFont(new Font("Arial", Font.BOLD, 23));
        subtitle.setForeground(white);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(subtitle);
        content.add(Box.createVerticalStrut(5));

        String[] roles = {
                "Staff", "Student", "Guidance", "Instructor"
        };

        String[] icons = {
                "STAFF", "STUDENT", "GUIDANCE", "INSTRUCTOR"
        };

        for (int i = 0; i < roles.length; i++) {

            final String role = roles[i];

            JButton button = createRoleButton(
                    (i + 1) + ".     " + icons[i]);

            button.addActionListener(e -> {
                selectedRole = role;
                loginRoleLabel.setText("Login as " + role);
                loginId.setText("");
                loginPassword.setText("");
                cardLayout.show(mainPanel, "LOGIN");
            });

            content.add(button);
            content.add(Box.createVerticalStrut(9));
        }

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(content);

        JButton backButton = createSmallButton("BACK");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "WELCOME"));

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setBorder(new EmptyBorder(5, 0, 12, 0));
        bottom.add(backButton);

        panel.add(center, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    // LOGIN SCREEN
    private JPanel createLoginScreen() {

        ImagePanel panel = new ImagePanel(backgroundImage, 0.34f);
        panel.setLayout(new GridBagLayout());

        JPanel loginCard = new JPanel();
        loginCard.setBackground(new Color(5, 12, 19, 245));

        loginCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(130, 35, 55), 1),
                new EmptyBorder(15, 35, 20, 35)));

        loginCard.setPreferredSize(new Dimension(460, 550));
        loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));

        loginCard.add(createLogo(210, 145));
        loginCard.add(Box.createVerticalStrut(5));

        JLabel loginTitle = new JLabel("Log In");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 25));
        loginTitle.setForeground(white);
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel description = new JLabel(
                "Enter your credentials to continue");

        description.setFont(new Font("Arial", Font.PLAIN, 13));
        description.setForeground(gray);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginRoleLabel = new JLabel("Login as");
        loginRoleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        loginRoleLabel.setForeground(lightRed);
        loginRoleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginId = new JTextField();
        loginId.setMaximumSize(new Dimension(330, 45));
        styleField(loginId);
        loginId.setToolTipText("Username / SR-Code");

        loginPassword = new JPasswordField();
        loginPassword.setMaximumSize(new Dimension(330, 45));
        styleField(loginPassword);
        loginPassword.setToolTipText("Password");

        JButton loginButton = createButton("LOG IN");
        loginButton.setMaximumSize(new Dimension(330, 48));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> processLogin());

        JButton backButton = new JButton("Back to Select User");
        backButton.setFont(new Font("Arial", Font.PLAIN, 13));
        backButton.setForeground(white);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "ROLE"));

        loginCard.add(loginTitle);
        loginCard.add(Box.createVerticalStrut(5));
        loginCard.add(description);
        loginCard.add(Box.createVerticalStrut(12));
        loginCard.add(loginRoleLabel);
        loginCard.add(Box.createVerticalStrut(18));
        loginCard.add(loginId);
        loginCard.add(Box.createVerticalStrut(12));
        loginCard.add(loginPassword);
        loginCard.add(Box.createVerticalStrut(20));
        loginCard.add(loginButton);
        loginCard.add(Box.createVerticalStrut(12));
        loginCard.add(backButton);

        panel.add(loginCard);

        return panel;
    }

    // LOGIN VALIDATION
    private void processLogin() {

        String id = loginId.getText().trim();
        String password = new String(loginPassword.getPassword());

        if (id.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter your ID and password.");
            return;
        }

        if (selectedRole.equals("Guidance")
                && id.equals("GC-1001")
                && password.equals("1234")) {

            loggedInUser = new GuidanceCounselor(
                    id, password, "Guidance Counselor");

        } else if (selectedRole.equals("Instructor")
                && id.equals("INS-1001")
                && password.equals("1234")) {

            loggedInUser = new Instructor(
                    id, password, "Instructor");

        } else if (selectedRole.equals("Staff")
                && id.equals("SS-1001")
                && password.equals("1234")) {

            loggedInUser = new SchoolStaff(
                    id, password, "School Staff");

        } else if (selectedRole.equals("Student")
                && id.startsWith("SR-")) {

            loggedInUser = new Student(
                    id, password, "Student");

        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid ID or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        showDashboard();
    }

    // DASHBOARD ROUTER
    public void showDashboard() {

        if (loggedInUser instanceof Student) {
            showStudentDashboard();

        } else if (loggedInUser instanceof GuidanceCounselor) {
            showGuidanceDashboard();

        } else if (loggedInUser instanceof SchoolStaff) {
            showStaffDashboard();

        } else if (loggedInUser instanceof Instructor) {
            showInstructorDashboard();
        }
    }

    // COMMON DASHBOARD
    private JPanel createDashboard(String title) {

        ImagePanel dashboard = new ImagePanel(backgroundImage, 0.32f);
        dashboard.setLayout(new BorderLayout());

        dashboardContent = new JPanel();
        dashboardContent.setOpaque(false);
        dashboardContent.setBorder(new EmptyBorder(25, 20, 25, 20));

        dashboardContent.setLayout(new BoxLayout(
                dashboardContent, BoxLayout.Y_AXIS));

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
        navigation.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton home = createNavButton("Home");
        JButton records = createNavButton("Records");
        JButton settings = createNavButton("Settings");

        home.addActionListener(e -> showDashboard());

        records.addActionListener(e -> {
            if (loggedInUser instanceof Student) {
                showMyRecords();
            } else {
                showAllRecords();
            }
        });

        settings.addActionListener(e -> showSettings());

        navigation.add(home);
        navigation.add(records);
        navigation.add(settings);

        dashboard.add(scrollPane, BorderLayout.CENTER);
        dashboard.add(navigation, BorderLayout.SOUTH);

        return dashboard;
    }

    // DASHBOARD TITLE
    private void addDashboardHeading(String title, String subtitle) {

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

    // STUDENT DASHBOARD
    private void showStudentDashboard() {

        JPanel dashboard = createDashboard("Student");

        addDashboardHeading("Check Your Violations",
                "Enter your details to view your record.");

        addDashboardButton(dashboardContent,
                "MY VIOLATION RECORDS",
                e -> showMyRecords());

        addDashboardButton(dashboardContent,
                "FILE A COMPLAINT",
                e -> fileComplaint());

        addDashboardButton(dashboardContent,
                "LOG OUT",
                e -> logout());

        displayDashboard(dashboard, "STUDENT");
    }

    // STAFF DASHBOARD
    private void showStaffDashboard() {

        JPanel dashboard = createDashboard("Staff");

        addDashboardHeading("Manage Student Violations",
                "Select a function to continue.");

        addDashboardButton(dashboardContent,
                "ADD VIOLATION",
                e -> addViolation());

        addDashboardButton(dashboardContent,
                "VIEW ALL RECORDS",
                e -> showAllRecords());

        addDashboardButton(dashboardContent,
                "LOG OUT",
                e -> logout());

        displayDashboard(dashboard, "STAFF");
    }

    // GUIDANCE DASHBOARD
    private void showGuidanceDashboard() {

        JPanel dashboard = createDashboard("Guidance");

        addDashboardHeading("Guidance Dashboard",
                "Manage student cases and complaints.");

        addDashboardButton(dashboardContent,
                "ADD VIOLATION",
                e -> addViolation());

        addDashboardButton(dashboardContent,
                "VIEW ALL RECORDS",
                e -> showAllRecords());

        addDashboardButton(dashboardContent,
                "UPDATE CASE STATUS",
                e -> updateStatus());

        addDashboardButton(dashboardContent,
                "VIEW STUDENT COMPLAINTS",
                e -> showComplaints());

        addDashboardButton(dashboardContent,
                "LOG OUT",
                e -> logout());

        displayDashboard(dashboard, "GUIDANCE");
    }

    // INSTRUCTOR DASHBOARD
    private void showInstructorDashboard() {

        JPanel dashboard = createDashboard("Instructor");

        addDashboardHeading("Faculty Dashboard",
                "Manage and view student violation records.");

        addDashboardButton(dashboardContent,
                "ADD VIOLATION",
                e -> addViolation());

        addDashboardButton(dashboardContent,
                "VIEW ALL RECORDS",
                e -> showAllRecords());

        addDashboardButton(dashboardContent,
                "LOG OUT",
                e -> logout());

        displayDashboard(dashboard, "INSTRUCTOR");
    }

    // DISPLAY DASHBOARD
    private void displayDashboard(JPanel dashboard, String screenName) {

        Component[] components = mainPanel.getComponents();

        for (Component component : components) {
            if (screenName.equals(component.getName())) {
                mainPanel.remove(component);
                break;
            }
        }

        dashboard.setName(screenName);
        mainPanel.add(dashboard, screenName);

        mainPanel.revalidate();
        mainPanel.repaint();

        cardLayout.show(mainPanel, screenName);
    }

    // DASHBOARD BUTTON
    private void addDashboardButton(JPanel panel, String text,
            ActionListener action) {

        JButton button = createButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(450, 52));
        button.setPreferredSize(new Dimension(450, 52));
        button.addActionListener(action);

        panel.add(button);
        panel.add(Box.createVerticalStrut(15));
    }

    // ADD VIOLATION
    public void addViolation() {

        JTextField studentId = new JTextField();
        JTextField studentName = new JTextField();

        String[] categories = {
                "Minor Violation",
                "Major Violation",
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
                "Unauthorized Accesss to Univerasity Systems",
        };

        JComboBox<String> categoryBox = new JComboBox<>(categories);
        JComboBox<String> typeBox = new JComboBox<>(minorTypes);

        categoryBox.addActionListener(e -> {
            typeBox.removeAllItems();

            String selectedCategory = (String) categoryBox.getSelectedItem();

            if ("Minor Violation".equals(selectedCategory)) {
                for (String type : minorTypes) {
                    typeBox.addItem(type);
                }
            } else if ("Major Violation".equals(selectedCategory)) {
                for (String type : majorViolations) {
                    typeBox.addItem(type);
                }
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

        int result = JOptionPane.showConfirmDialog(this, form,
                "Add Violation", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        if (studentId.getText().trim().isEmpty()
                || studentName.getText().trim().isEmpty()
                || description.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please complete all fields.");
            return;
        }

        Violation violation = new Violation(
                nextViolationId++,
                studentId.getText().trim(),
                studentName.getText().trim(),
                (String) typeBox.getSelectedItem(),
                description.getText().trim(),
                loggedInUser.getName());

        violations.add(violation);

        JOptionPane.showMessageDialog(this,
                "Violation added successfully!");
    }

    // VIEW ALL RECORDS
    public void showAllRecords() {
        showRecords(violations);
    }

    // VIEW STUDENT RECORDS
    public void showMyRecords() {

        Student student = (Student) loggedInUser;
        ArrayList<Violation> myRecords = new ArrayList<>();

        for (Violation v : violations) {
            if (v.getStudentId().equalsIgnoreCase(student.getId())) {
                myRecords.add(v);
            }
        }

        showRecords(myRecords);
    }

    // DISPLAY RECORDS

    // DISPLAY RECORDS
    private void showRecords(ArrayList<Violation> records) {

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setBackground(cardColor);
        area.setForeground(white);
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

        JOptionPane.showMessageDialog(this, scroll,
                "Violation Records", JOptionPane.INFORMATION_MESSAGE);
    }

    // UPDATE STATUS
    public void updateStatus() {

        if (violations.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No violation records available.");
            return;
        }

        String idInput = JOptionPane.showInputDialog(this,
                "Enter Violation ID:");

        if (idInput == null) {
            return;
        }

        int id;

        try {
            id = Integer.parseInt(idInput.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number.");
            return;
        }

        for (Violation v : violations) {

            if (v.getId() == id) {

                String[] options = { "Pending", "Resolved" };

                String status = (String) JOptionPane.showInputDialog(
                        this,
                        "Select new status:",
                        "Update Status",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        v.getStatus());

                if (status != null) {
                    v.setStatus(status);

                    JOptionPane.showMessageDialog(this,
                            "Status updated successfully!");
                }

                return;
            }
        }

        JOptionPane.showMessageDialog(this,
                "Violation ID not found.");
    }

    // FILE COMPLAINT
    public void fileComplaint() {

        String complaint = JOptionPane.showInputDialog(this,
                "Enter your complaint:");

        if (complaint == null) {
            return;
        }

        if (complaint.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Complaint cannot be empty.");
            return;
        }

        complaints.add(
                "Student ID: " + loggedInUser.getId()
                        + "\nStudent Name: " + loggedInUser.getName()
                        + "\nComplaint: " + complaint.trim());

        JOptionPane.showMessageDialog(this,
                "Complaint submitted successfully!");
    }

    // VIEW COMPLAINTS
    public void showComplaints() {

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        area.setBackground(cardColor);
        area.setForeground(white);

        if (complaints.isEmpty()) {
            area.setText("No student complaints found.");
        } else {
            for (int i = 0; i < complaints.size(); i++) {
                area.append("COMPLAINT " + (i + 1) + "\n");
                area.append(complaints.get(i) + "\n");
                area.append("----------------------------------\n\n");
            }
        }

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(this, scroll,
                "Student Complaints", JOptionPane.INFORMATION_MESSAGE);
    }

    // SETTINGS
    public void showSettings() {

        String[] options = {
                "Account Information",
                "Log Out",
                "Exit Application"
        };

        int choice = JOptionPane.showOptionDialog(
                this,
                "Select a setting:",
                "Settings",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            JOptionPane.showMessageDialog(this,
                    "Name: " + loggedInUser.getName()
                            + "\nID: " + loggedInUser.getId()
                            + "\nRole: " + loggedInUser.getRole(),
                    "Account Information",
                    JOptionPane.INFORMATION_MESSAGE);

        } else if (choice == 1) {
            logout();

        } else if (choice == 2) {
            cardLayout.show(mainPanel, "EXIT");
        }
    }

    // LOGOUT
    public void logout() {

        loggedInUser = null;
        loginId.setText("");
        loginPassword.setText("");

        cardLayout.show(mainPanel, "WELCOME");
    }

    // EXIT SCREEN
    private JPanel createExitScreen() {

        ImagePanel panel = new ImagePanel(backgroundImage, 0.32f);
        panel.setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setBackground(new Color(5, 12, 19, 245));

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(gray),
                new EmptyBorder(20, 40, 25, 40)));

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(480, 430));

        card.add(createLogo(220, 155));
        card.add(Box.createVerticalStrut(5));
        card.add(createTitle("Exit", 32));

        JLabel question = new JLabel(
                "Are you sure you want to exit the application?");

        question.setForeground(white);
        question.setFont(new Font("Arial", Font.PLAIN, 14));
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalStrut(15));
        card.add(question);
        card.add(Box.createVerticalStrut(25));

        JButton yesButton = createButton("YES, EXIT");
        yesButton.setMaximumSize(new Dimension(320, 48));
        yesButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        yesButton.addActionListener(e -> {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit STRIVE LOG?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                showThankYouThenClose();
            }
        });

        JButton cancelButton = createOutlineButton("CANCEL");
        cancelButton.setMaximumSize(new Dimension(320, 48));
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        cancelButton.addActionListener(e -> {
            if (loggedInUser == null) {
                cardLayout.show(mainPanel, "WELCOME");
            } else {
                showDashboard();
            }
        });

        card.add(yesButton);
        card.add(Box.createVerticalStrut(12));
        card.add(cancelButton);

        panel.add(card);

        return panel;
    }

    // THANK YOU SCREEN
    private JPanel createThankYouScreen() {

        ImagePanel panel = new ImagePanel(backgroundImage, 0.30f);
        panel.setLayout(new GridBagLayout());

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(createTitle("Thank You!", 52));
        content.add(Box.createVerticalStrut(15));

        JLabel message = new JLabel(
                "Together for a safer, more disciplined school.");

        message.setFont(new Font("Arial", Font.ITALIC, 16));
        message.setForeground(white);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(message);
        content.add(Box.createVerticalStrut(25));
        content.add(createLogo(390, 270));

        panel.add(content);

        return panel;
    }

    // SHOW THANK YOU BEFORE CLOSING
    private void showThankYouThenClose() {
        cardLayout.show(mainPanel, "THANKYOU");

        javax.swing.Timer timer = new javax.swing.Timer(3000, e -> {
            dispose();
            System.exit(0);
        });

        timer.setRepeats(false);
        timer.start();
    }

    // ROLE BUTTON DESIGN
    private JButton createRoleButton(String text) {

        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.BOLD, 17));
        button.setForeground(white);
        button.setBackground(cardColor);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(55, 75, 90), 1),
                new EmptyBorder(15, 22, 15, 22)));

        button.setMaximumSize(new Dimension(450, 55));
        button.setPreferredSize(new Dimension(450, 55));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(darkRed);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(cardColor);
            }
        });

        return button;
    }

    // MAIN BUTTON DESIGN
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

    // OUTLINE BUTTON
    private JButton createOutlineButton(String text) {

        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(white);
        button.setBackground(cardColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(gray, 1));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // SMALL BUTTON
    private JButton createSmallButton(String text) {

        JButton button = createButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 12));

        return button;
    }

    // NAVIGATION BUTTON
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

    // TEXT FIELD DESIGN
    private void styleField(JTextField field) {

        field.setFont(new Font("Arial", Font.PLAIN, 15));
        field.setForeground(white);
        field.setBackground(fieldColor);
        field.setCaretColor(white);

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(65, 85, 100), 1),
                new EmptyBorder(10, 14, 10, 14)));

        field.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // USER CLASSES
    static abstract class User {

        private String id;
        private String password;
        private String name;

        public User(String id, String password, String name) {
            this.id = id;
            this.password = password;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        public abstract String getRole();
    }

    static class Student extends User {

        public Student(String id, String password, String name) {
            super(id, password, name);
        }

        public String getRole() {
            return "Student";
        }
    }

    static class GuidanceCounselor extends User {

        public GuidanceCounselor(String id, String password, String name) {
            super(id, password, name);
        }

        public String getRole() {
            return "Guidance Counselor";
        }
    }

    static class Instructor extends User {

        public Instructor(String id, String password, String name) {
            super(id, password, name);
        }

        public String getRole() {
            return "Instructor";
        }
    }

    static class SchoolStaff extends User {

        public SchoolStaff(String id, String password, String name) {
            super(id, password, name);
        }

        public String getRole() {
            return "School Staff";
        }
    }

    // VIOLATION CLASS
    static class Violation {

        private int id;
        private String studentId;
        private String studentName;
        private String type;
        private String description;
        private String reportedBy;
        private String status;

        public Violation(int id, String studentId, String studentName,
                String type, String description, String reportedBy) {

            this.id = id;
            this.studentId = studentId;
            this.studentName = studentName;
            this.type = type;
            this.description = description;
            this.reportedBy = reportedBy;
            this.status = "Pending";
        }

        public int getId() {
            return id;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }

        public String getReportedBy() {
            return reportedBy;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public boolean isStudent() {
        return loggedInUser instanceof Student;
    }

    public int getNextViolationId() {
        return nextViolationId++;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void addViolationRecord(Violation violation) {
        violations.add(violation);
    }

    // MAIN METHOD
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            STRIVELOG app = new STRIVELOG();
            app.setVisible(true);
        });
    }
}
