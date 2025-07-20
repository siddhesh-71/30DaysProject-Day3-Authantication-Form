import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class Dashboard extends JFrame {
    public Dashboard(String username, String email) {
        setTitle(" Welcome Dashboard");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🌈 Main gradient panel
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color c1 = new Color(102, 204, 255);
                Color c2 = new Color(255, 255, 255);
                GradientPaint gp = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new GridBagLayout());

        // 🧾 Profile Card
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(400, 350));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(173, 216, 230), 2),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        // 👤 Profile Icon
        JLabel icon = new JLabel("👤", SwingConstants.CENTER);
        icon.setFont(new Font("SansSerif", Font.PLAIN, 50));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 🧾 Info Labels
        JLabel userLabel = new JLabel("Hello, " + username + "!");
        userLabel.setFont(new Font("Arial", Font.BOLD, 26));
        userLabel.setForeground(new Color(25, 25, 112));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel(" Email: " + email);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel timeLabel = new JLabel(" Logged in at: " + LocalTime.now().withNano(0));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 🚪 Logout button
        JButton logout = new JButton(" Logout");
        logout.setFont(new Font("Arial", Font.BOLD, 16));
        logout.setBackground(new Color(255, 69, 58));
        logout.setForeground(Color.WHITE);
        logout.setFocusPainted(false);
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        logout.addActionListener(e -> {
            dispose();
            new LoginForm();
        });

        card.add(icon);
        card.add(Box.createVerticalStrut(10));
        card.add(userLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(emailLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(timeLabel);
        card.add(Box.createVerticalStrut(30));
        card.add(logout);

         background.add(card);
        add(background);
        setVisible(true);
    }
}
