package org.leafroot.pages.main_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MainPageForm {
    private final SettingsForm settingsForm = new SettingsForm();
    private JPanel mainFramePanel;
    private JSplitPane splitPane;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel textFieldPanel;
    private JTextField textField;
    private JButton sendButton;
    private JPanel settingsPanel;
    private JPanel chatsPanel;
    private JButton addChatButton;
    private JButton settingsButton;

    public MainPageForm() {
        textField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        textField.setOpaque(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder());
        addChatButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.addActionListener(e -> {
            splitPane.setLeftComponent(settingsForm.getMainSettingsPanel());
            splitPane.repaint();
        });
        settingsButton.setIcon(new ImageIcon("src/main/resources/imgs/settings-badge.png"));
        addChatButton.setIcon(new ImageIcon("src/main/resources/imgs/add-chat-badge.png"));
        sendButton.setIcon(new ImageIcon("src/main/resources/imgs/send.png"));
    }

    public JPanel getMainFramePanel() {
        return this.mainFramePanel;
    }


    private void createUIComponents() {
        textField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(44, 44, 44));
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }
        };
    }

    private class SettingsForm {
    //    private final MainPageForm mainPageForm = new MainPageForm();
        private JPanel mainSettingsPanel;
        private JPanel topPanel;
        private JButton exitButton;
        private JLabel settingsLabel;
        private JTextField changePasswordTextField;
        private JButton changePasswordButton;
        private JTextField changePasswordConfirmTextField;
        private boolean showHintPassword = true;
        private boolean showHintConfirmPassword = true;

        public SettingsForm() {
            exitButton.setBorder(BorderFactory.createEmptyBorder());
            exitButton.setOpaque(false);
            exitButton.setIcon(new ImageIcon("src/main/resources/imgs/exit-arrow.png"));
            exitButton.addActionListener(e -> {
                splitPane.setLeftComponent(leftPanel);
                splitPane.repaint();
            });

            changePasswordButton.setIcon(new ImageIcon("src/main/resources/imgs/change-password-button.png"));
            changePasswordButton.setBorder(BorderFactory.createEmptyBorder());

            changePasswordTextField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
            changePasswordConfirmTextField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));


            changePasswordTextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (showHintPassword) {
                        changePasswordTextField.setText("");
                        changePasswordTextField.setForeground(new Color(161,161,161));
                    }
                    showHintPassword = false;
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (changePasswordTextField.getText().length() == 0) {
                        changePasswordTextField.setForeground(new Color(80,80,80));
                        changePasswordTextField.setText("New password");
                        showHintPassword = true;
                    }
                }
            });
            changePasswordConfirmTextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (showHintConfirmPassword) {
                        changePasswordConfirmTextField.setText("");
                        changePasswordConfirmTextField.setForeground(new Color(161,161,161));
                        showHintConfirmPassword = false;
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (changePasswordConfirmTextField.getText().length() == 0) {
                        changePasswordConfirmTextField.setForeground(new Color(80,80,80));
                        changePasswordConfirmTextField.setText("Confirm password");
                        showHintConfirmPassword = true;
                    }
                }
            });
        }

        public JPanel getMainSettingsPanel() {
            return mainSettingsPanel;
        }

        private void createUIComponents() {
            changePasswordTextField = new JTextField() {
                @Override
                protected void paintComponent(Graphics g) {
                    g.setColor(new Color(27, 27, 27));
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    super.paintComponent(g);
                }
            };
            changePasswordConfirmTextField = new JTextField() {
                @Override
                protected void paintComponent(Graphics g) {
                    g.setColor(new Color(27, 27, 27));
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    super.paintComponent(g);
                }
            };
        }
    }
}
