import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterForm extends JFrame {
    JTextField tfUsername, tfEmail;
    JPasswordField pfPassword;

    public RegisterForm() {
        setTitle(" Register");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel bg = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(0, 0, new Color(255, 182, 193), 0, getHeight(), Color.WHITE));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bg.setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(400, 350));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 160, 122), 2),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        JLabel title = new JLabel(" Register", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(128, 0, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        tfUsername = new JTextField(20);
        tfEmail = new JTextField(20);
        pfPassword = new JPasswordField(20);

        JButton btnRegister = new JButton("Register ✅");
        JButton btnBack = new JButton("Back ⬅️");

        styleButton(btnRegister, new Color(0, 128, 0));
        styleButton(btnBack, new Color(100, 100, 100));

        card.add(title);
        card.add(Box.createVerticalStrut(20));
        card.add(new JLabel("👤 Username:"));
        card.add(tfUsername);
        card.add(Box.createVerticalStrut(10));
        card.add(new JLabel(" Email:"));
        card.add(tfEmail);
        card.add(Box.createVerticalStrut(10));
        card.add(new JLabel(" Password:"));
        card.add(pfPassword);
        card.add(Box.createVerticalStrut(20));
        card.add(btnRegister);
        card.add(Box.createVerticalStrut(10));
        card.add(btnBack);

        bg.add(card);
        add(bg);

        btnRegister.addActionListener(e -> registerUser());
        btnBack.addActionListener(e -> {
            dispose();
            new LoginForm();
        });

        setVisible(true);
    }

    void styleButton(JButton btn, Color bgColor) {
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    void registerUser() {
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());

                     try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://localhost:3306/user_login_db";
            Connection con = DriverManager.getConnection(url, "root", "abc123");

            PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "✅ Registration Successful!");
            con.close();
            dispose();
            new LoginForm();

        } catch (SQLIntegrityConstraintViolationException dup) {
            JOptionPane.showMessageDialog(this, "⚠️ Username already exists!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
