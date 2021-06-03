package com.company.pages.login_page;

import com.company.Client.Authorization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegistrationFrame extends JFrame {

    private JPanel mainPanel;
    private JLabel appBadgeLabel;
    private JPanel textFieldsPanel;
    private JTextField loginTextField;
    private JTextField passwordTextField;
    private JButton registerButton;

    public RegistrationFrame(String title) throws HeadlessException {
        super(title);
        setSize(700,350);
        setMaximumSize(new Dimension(700,350));
        setMinimumSize(new Dimension(700,350));

        loginTextField.setBorder(BorderFactory.createEmptyBorder());
        passwordTextField.setBorder(BorderFactory.createEmptyBorder());
        registerButton.setBorder(BorderFactory.createEmptyBorder());

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

        registerButton.addActionListener(e -> {
            try {
                String temp = loginTextField.getText();
                Authorization.signUp(temp, temp, "", passwordTextField.getText(), "");
            } catch (NoSuchAlgorithmException | InterruptedException | URISyntaxException | InvalidKeySpecException | IOException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            }
            this.dispose();
        });

        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private void createUIComponents() {
        loginTextField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(44, 44, 44));
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() -1, 20, 20);
                if (!this.hasFocus() && loginTextField.getText().length() == 0) {
                    ImageIcon image = new ImageIcon("src/main/resources/imgs/badge-login.png");
                    image.paintIcon(this, g, 165, 10);
                }
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(44, 44, 44));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() -1, 20, 20);
                super.paintBorder(g);
            }

        }; // создаем поле для ввода логина и перегруз методов для прорисовки (чтобы были полукруглые)
        passwordTextField = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(44, 44, 44));
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() -1, 20, 20);
                if (!this.hasFocus() && passwordTextField.getText().length() == 0) {
                    ImageIcon image = new ImageIcon("src/main/resources/imgs/badge-password.png");
                    image.paintIcon(this, g, 165, 10);
                }
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(44, 44, 44));
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() -1, 20, 20);
                super.paintBorder(g);
            }
        }; // создаем поле для ввода пароля и перегруз методов для прорисовки (чтобы были полукруглые)
    }

    public JTextField getLoginTextField() {
        return loginTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

}
