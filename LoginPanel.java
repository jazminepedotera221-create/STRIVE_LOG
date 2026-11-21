import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField loginId;
    private JPasswordField loginPassword;
    private JLabel loginRoleLabel;

    public LoginPanel() {

        setLayout(new GridBagLayout());
        setOpaque(false);

        JPanel loginCard = new JPanel();
        loginCard.setBackground(new Color(5, 12, 19, 245));

        loginCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(130, 35, 55), 1),
                new EmptyBorder(15, 35, 20, 35)));

        loginCard.setPreferredSize(new Dimension(460, 550));
        loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));

        JLabel loginTitle = new JLabel("Log In");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 25));
        loginTitle.setForeground(Color.WHITE);
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel description = new JLabel(
                "Enter your credentials to continue");
        description.setFont(new Font("Arial", Font.PLAIN, 13));
        description.setForeground(Color.GRAY);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginRoleLabel = new JLabel("Login as");
        loginRoleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        loginRoleLabel.setForeground(new Color(220, 20, 60));
        loginRoleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginId = new JTextField();
        loginId.setMaximumSize(new Dimension(330, 45));

        loginPassword = new JPasswordField();
        loginPassword.setMaximumSize(new Dimension(330, 45));

        loginCard.add(loginTitle);
        loginCard.add(Box.createVerticalStrut(5));
        loginCard.add(description);
        loginCard.add(Box.createVerticalStrut(12));
        loginCard.add(loginRoleLabel);
        loginCard.add(Box.createVerticalStrut(18));
        loginCard.add(loginId);
        loginCard.add(Box.createVerticalStrut(12));
        loginCard.add(loginPassword);

        add(loginCard);
    }

    public String getLoginId() {
        return loginId.getText();
    }

    public String getLoginPassword() {
        return new String(loginPassword.getPassword());
    }

    public void setRole(String role) {
        loginRoleLabel.setText("Login as " + role);
    }
}