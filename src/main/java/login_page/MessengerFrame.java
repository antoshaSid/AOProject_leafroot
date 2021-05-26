package login_page;

import main_page.MainPageForm;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

public class MessengerFrame extends JFrame {
    private JPanel mainFramePanel; // главная панелька нашого фрейма
    private JLabel appBadgeLabel; // значок листка
    private JTextField loginTextField; // поле ввода логина
    private JPasswordField passwordTextField; // поле ввода пароля
    private JButton loginButton; // кнопка входа
    private final MainPageForm mainPageForm = new MainPageForm();

    public MessengerFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 800); // размер окна
        loginTextField.setBorder(BorderFactory.createEmptyBorder());
        loginTextField.setOpaque(false);
        passwordTextField.setBorder(BorderFactory.createEmptyBorder());
        passwordTextField.setOpaque(false);
        appBadgeLabel.setIcon(new ImageIcon("src/main/resources/imgs/badge_leaf.png"));
        loginButton.setIcon(new ImageIcon("src/main/resources/imgs/button_login.png"));

        loginButton.addActionListener(e -> {
            this.getContentPane().removeAll();
            this.getContentPane().add(mainPageForm.getMainFramePanel());
            this.setVisible(true);
        });

        getContentPane().add(mainFramePanel); // добавления главную панельку на фрейм
        setVisible(true);
    }

    public static void main(String[] args) {
        MessengerFrame messengerFrame = new MessengerFrame(); // запуск окна
    }

    private void createUIComponents() {
        loginTextField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(241, 242, 246));
                g.fillRoundRect(0, 0, 360, 40, 20, 20);
                ImageIcon image = new ImageIcon("src/main/resources/imgs/badge_login.png");
                image.paintIcon(this, g, 165, 10);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(206, 214, 224));
                g.drawRoundRect(0, 0, 360, 40, 20, 20);
                super.paintBorder(g);
            }
        }; // создаем поле для ввода логина и перегруз методов для прорисовки (чтобы были полукруглые)
        passwordTextField = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(241, 242, 246));
                g.fillRoundRect(0, 0, 360, 40, 20, 20);
                ImageIcon image = new ImageIcon("src/main/resources/imgs/badge_password.png");
                image.paintIcon(this, g, 165, 10);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(206, 214, 224));
                g.drawRoundRect(0, 0, 360, 40, 20, 20);
                super.paintBorder(g);
            }
        }; // создаем поле для ввода пароля и перегруз методов для прорисовки (чтобы были полукруглые)
    }

}
