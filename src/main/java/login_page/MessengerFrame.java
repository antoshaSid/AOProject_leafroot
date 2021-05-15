package login_page;

import javax.swing.*;
import java.awt.*;

public class MessengerFrame extends JFrame {
    private JPanel mainFramePanel; // главная панелька нашого фрейма
    private JLabel appBadgeLabel; // значок листка
    private JTextField loginTextField; // поле ввода логина
    private JPasswordField passwordTextField; // поле ввода пароля
    private JButton loginButton; // кнопка входа

    public MessengerFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 800); // размер окна

        loginTextField.setBorder(BorderFactory.createEmptyBorder());
        loginTextField.setOpaque(false);
        passwordTextField.setBorder(BorderFactory.createEmptyBorder());
        passwordTextField.setOpaque(false);

        getContentPane().add(mainFramePanel); // добавления главную панельку на фрейм
        setVisible(true);
    }

    public static void main(String[] args) {
        MessengerFrame messengerFrame = new MessengerFrame(); // запуск окна
    }

    private void createUIComponents() { // это метод Intellij IDEa для создания своих конструкторов для компонентов
        appBadgeLabel = new JLabel(new ImageIcon("src/main/java/login_page/badge_leaf.png")); // добавление значка
        loginTextField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(241, 242, 246));
                g.fillRoundRect(0,0,360,40,20,20);
                ImageIcon image = new ImageIcon("src/main/java/login_page/badge_login.png");
                image.paintIcon(this,g,165,10);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(206, 214, 224));
                g.drawRoundRect(0,0,360,40,20,20);
                super.paintBorder(g);
            }
        }; // создаем поле для ввода логина и перегруз методов для прорисовки (чтобы были полукруглые)
        passwordTextField = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(241, 242, 246));
                g.fillRoundRect(0,0,360,40,20,20);
                ImageIcon image = new ImageIcon("src/main/java/login_page/badge_password.png");
                image.paintIcon(this,g,165,10);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(206, 214, 224));
                g.drawRoundRect(0,0,360,40,20,20);
                super.paintBorder(g);
            }
        }; // создаем поле для ввода пароля и перегруз методов для прорисовки (чтобы были полукруглые)
        loginButton = new JButton() {
            @Override
            protected void paintBorder(Graphics g) {
                super.paintBorder(g);
                g.drawRoundRect(0,0,360,40,20,20);
            }

            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(40,233,107));
                g.fillRoundRect(0,0,360,40,20,20);
                super.paintComponent(g);
            }
        }; // // создаем кнопку для входа и перегруз методов для прорисовки (чтобы была полукруглая)
    }
}
