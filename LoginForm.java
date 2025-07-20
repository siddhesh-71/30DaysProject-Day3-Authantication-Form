import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginForm extends JFrame {
    JTextField tfUsername;
    JPasswordField pfPassword;

    public LoginForm() {
        setTitle("🔐 Login Page");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel bg = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(0, 0, new Color(135, 206, 250), 0, getHeight(), Color.WHITE));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bg.setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(400, 300));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(173, 216, 230), 2),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        JLabel title = new JLabel(" User Login", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(25, 25, 112));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        tfUsername = new JTextField(20);
        pfPassword = new JPasswordField(20);

        JButton btnLogin = new JButton("Login ");
        JButton btnRegister = new JButton("Register ");

        styleButton(btnLogin, new Color(34, 139, 34));
        styleButton(btnRegister, new Color(30, 144, 255));

        card.add(title);
        card.add(Box.createVerticalStrut(20));
        card.add(new JLabel("👤 Username:"));
        card.add(tfUsername);
        card.add(Box.createVerticalStrut(10));
        card.add(new JLabel("🔑 Password:"));
        card.add(pfPassword);
        card.add(Box.createVerticalStrut(20));
        card.add(btnLogin);
        card.add(Box.createVerticalStrut(10));
        card.add(btnRegister);

        bg.add(card);
        add(bg);

        btnLogin.addActionListener(e -> loginUser());
        btnRegister.addActionListener(e -> {
            dispose();
            new RegisterForm();
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

    void loginUser() {
        String username = tfUsername.getText();
        String password = String.valueOf(pfPassword.getPassword());

                     try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://localhost:3306/user_login_db";
            Connection con = DriverManager.getConnection(url, "root", "abc123");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "✅ Login Successful!");
                dispose();
                new Dashboard(username, rs.getString("email"));
            } else {
                JOptionPane.showMessageDialog(this, "❌ Invalid Username or Password");
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
