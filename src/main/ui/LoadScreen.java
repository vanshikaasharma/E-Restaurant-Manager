package ui;

import javax.swing.*;
import model.Restaurant;
import java.awt.*;
import java.io.File;

public class LoadScreen {

    private static JFrame splashScreen;
    private static Timer progressTimer;
    private static int progress = 0;

    // EFFECTS: Launches the splash screen and starts the application.
    public static void main(String[] args) {
        createSplashScreen();
    }

    // EFFECTS: Creates and displays a splash screen with a progress bar and
    // launches the main application after a delay.
    @SuppressWarnings("methodlength")
    private static void createSplashScreen() {
        splashScreen = new JFrame();
        splashScreen.setSize(800, 700);
        splashScreen.setUndecorated(true);
        splashScreen.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                File imageFile = new File("lib/wallpaper_food.jpg");
                if (imageFile.exists()) {
                    ImageIcon backgroundImage = new ImageIcon(imageFile.getAbsolutePath());
                    Image img = backgroundImage.getImage();
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(Color.LIGHT_GRAY); // Light background if image not found
                    g.fillRect(0, 0, getWidth(), getHeight());
                }

                g2d.setColor(new Color(0, 0, 0, 120)); // Semi-transparent overlay
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        // Cute welcome message with a fun font
        JLabel welcomeLabel = new JLabel("Welcome to E-Restaurant Manager!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        welcomeLabel.setForeground(new Color(255, 255, 225)); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0);
        centerPanel.add(welcomeLabel, gbc);

        // Cute progress bar
        JPanel progressPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int barWidth = 400;
                int barHeight = 30;
                int x = (getWidth() - barWidth) / 2;
                int y = (getHeight() - barHeight) / 2;

                g2d.setColor(new Color(255, 255, 225)); 
                g2d.fillRect(x, y, barWidth, barHeight);

                g2d.setColor(new Color(58, 123, 213)); 
                g2d.fillRect(x, y, (int) (barWidth * (progress / 100.0)), barHeight);

                g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                g2d.setColor(Color.BLACK);
                String percentageText = progress + "%";
                int textWidth = g2d.getFontMetrics().stringWidth(percentageText);
                int textX = x + (barWidth - textWidth) / 2;
                int textY = y + barHeight / 2 + g2d.getFontMetrics().getAscent() / 2 - 2;
                g2d.drawString(percentageText, textX, textY);
            }
        };
        progressPanel.setPreferredSize(new Dimension(500, 100));
        progressPanel.setOpaque(false);
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        centerPanel.add(progressPanel, gbc);

        panel.add(centerPanel, BorderLayout.CENTER);

        progressTimer = new Timer(30, e -> {
            progress++;
            progressPanel.repaint();
            if (progress >= 100) {
                progressTimer.stop();
            }
        });
        progressTimer.start();

        // Timer to launch the main application after the splash screen
        Timer timer = new Timer(3400, e -> {
            splashScreen.setVisible(false);
            splashScreen.dispose();

            ERestaurantManagerGUI mainGUI = new ERestaurantManagerGUI();
            mainGUI.setVisible(true);
        });
        timer.setRepeats(false);
        timer.start();

        splashScreen.getContentPane().add(panel);
        splashScreen.setVisible(true);
    }
}
