package org.leafroot.pages.login_page;

import org.leafroot.pages.main_page.MainPageForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import com.apple.eawt.Application;

public class MessengerFrame extends JFrame {
    private JPanel mainFramePanel; // главная панелька нашого фрейма
    private JLabel appBadgeLabel; // значок листка
    private JTextField loginTextField; // поле ввода логина
    private JPasswordField passwordTextField; // поле ввода пароля
    private JButton loginButton; // кнопка входа
    private final MainPageForm mainPageForm = new MainPageForm();

    public MessengerFrame() {
        setIconImage(new ImageIcon("src/main/resources/imgs/badge-leaf.png").getImage());
        Application.getApplication().setDockIconImage(new ImageIcon("src/main/resources/imgs/badge-leaf.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 800); // размер окна
        setMinimumSize(new Dimension(600,450));
        loginTextField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        loginTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                loginTextField.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                loginTextField.repaint();
            }
        });
        loginTextField.setOpaque(false);

        passwordTextField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        passwordTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordTextField.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwordTextField.repaint();
            }
        });
        passwordTextField.setOpaque(false);

        appBadgeLabel.setIcon(new ImageIcon("src/main/resources/imgs/badge-leaf.png"));
        loginButton.setIcon(new ImageIcon("src/main/resources/imgs/button-login.png"));

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
                g.setColor(new Color(44, 44, 44));
                g.fillRoundRect(0, 0, 360, 40, 20, 20);
                if (!this.hasFocus() && loginTextField.getText().length() == 0) {
                    ImageIcon image = new ImageIcon("src/main/resources/imgs/badge-login.png");
                    image.paintIcon(this, g, 165, 10);
                }
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(44, 44, 44));
                g.drawRoundRect(0, 0, 360, 40, 20, 20);
                super.paintBorder(g);
            }

        }; // создаем поле для ввода логина и перегруз методов для прорисовки (чтобы были полукруглые)
        passwordTextField = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(44, 44, 44));
                g.fillRoundRect(0, 0, 360, 40, 20, 20);
                if (!this.hasFocus() && passwordTextField.getPassword().length == 0) {
                    ImageIcon image = new ImageIcon("src/main/resources/imgs/badge-password.png");
                    image.paintIcon(this, g, 165, 10);
                }
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(44, 44, 44));
                g.drawRoundRect(0, 0, 360, 40, 20, 20);
                super.paintBorder(g);
            }
        }; // создаем поле для ввода пароля и перегруз методов для прорисовки (чтобы были полукруглые)
    }


}