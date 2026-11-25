
import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private final JTextField idField;
    private final JPasswordField passwordField;
    private final JComboBox<String> roleBox;
    private final Authentication authentication;

    public LoginPanel() {
        authentication = new Authentication();

        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("User ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Role:"));
        roleBox = new JComboBox<>(new String[] {
                "Student",
                "Guidance Counselor",
                "Instructor",
                "School Staff"
        });
        add(roleBox);

        JButton loginButton = new JButton("Login");
        add(new JLabel());
        add(loginButton);

        loginButton.addActionListener(e -> login());
    }

    private void login() {
        String id = idField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleBox.getSelectedItem();

        if (id.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your ID and password.");
            return;
        }

        User user = authentication.authenticate(id, password, role);

        if (user != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Welcome, " + user.getName() + "!");
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid ID or role.");
        }
    }
}
