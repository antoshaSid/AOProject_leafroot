package com.company.pages.main_page;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Map;

public class MainPageForm {
    private final SettingsForm settingsForm = new SettingsForm();
    private final Map<String, JPanel> userChatsMap;
    private JPanel mainFramePanel;
    private JSplitPane splitPane;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel settingsPanel;
    private JButton addChatButton;
    private JButton settingsButton;
    private JScrollPane chatsScroll;
    private JList<String> listOfChats;
    private JLabel chatsLabel;
    private JLabel noChatsLabel;
    private DefaultListModel<String> listModelOfLeftChats;
    private final String userPhone;

    public MainPageForm(String userPhone) {
        this.userPhone = userPhone;
        userChatsMap = new HashMap<>();
        addChatButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        chatsScroll.setBorder(BorderFactory.createEmptyBorder());
        listOfChats.setBorder(BorderFactory.createEmptyBorder());

        ((DefaultListCellRenderer)listOfChats.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        settingsButton.addActionListener(e -> {
            splitPane.setLeftComponent(settingsForm.getMainSettingsPanel());
        });

        addChatButton.addActionListener(e -> {
            String phoneNumber = (String) JOptionPane.showInputDialog(mainFramePanel, "Enter phone number", "Chat with", JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("src/main/resources/imgs/badge-leaf.png"), null, null);
            if (!userChatsMap.containsKey(phoneNumber)) {
                listModelOfLeftChats.addElement(phoneNumber);
                userChatsMap.put(phoneNumber, new MainChatPanel(userPhone, phoneNumber).getChatPanel());
            }
        });

        listOfChats.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                splitPane.setRightComponent(userChatsMap.get(listOfChats.getSelectedValue()));

            }
        });

        settingsButton.setIcon(new ImageIcon("src/main/resources/imgs/settings-badge.png"));
        addChatButton.setIcon(new ImageIcon("src/main/resources/imgs/add-chat-badge.png"));

    }

    private void createUIComponents() {
        rightPanel = new JPanel();
        listModelOfLeftChats = new DefaultListModel<>();
        listOfChats = new JList<>(listModelOfLeftChats);
        chatsScroll = new JScrollPane(listOfChats);
    }

    public JPanel getMainFramePanel() {
        return this.mainFramePanel;
    }

    private class SettingsForm {
        private JPanel mainSettingsPanel;
        private JPanel topPanel;
        private JButton exitButton;
        private JLabel settingsLabel;
        private JTextField changePasswordTextField;
        private JButton changePasswordButton;
        private JTextField changePasswordConfirmTextField;
        private JPanel nicknameChangePanel;
        private JTextField changeFirstNameTextField;
        private JTextField changeSurnameTextField;
        private JButton changeNickNameButton;
        private JButton logoutButton;

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
            changeNickNameButton.setIcon(new ImageIcon("src/main/resources/imgs/save-nickname-button.png"));
            changeNickNameButton.setBorder(BorderFactory.createEmptyBorder());
            logoutButton.setIcon(new ImageIcon("src/main/resources/imgs/button-log-out.png"));
            logoutButton.setBorder(BorderFactory.createEmptyBorder());

            changePasswordTextField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
            changePasswordConfirmTextField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
            changeFirstNameTextField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
            changeSurnameTextField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));


            changePasswordTextField.addFocusListener(new HintText("New password", changePasswordTextField));
            changePasswordConfirmTextField.addFocusListener(new HintText("Confirm password", changePasswordConfirmTextField));
            changeFirstNameTextField.addFocusListener(new HintText("New first name", changeFirstNameTextField));
            changeSurnameTextField.addFocusListener(new HintText("New surname", changeSurnameTextField));

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

            changeFirstNameTextField = new JTextField() {
                @Override
                protected void paintComponent(Graphics g) {
                    g.setColor(new Color(27, 27, 27));
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    super.paintComponent(g);
                }
            };

            changeSurnameTextField = new JTextField() {
                @Override
                protected void paintComponent(Graphics g) {
                    g.setColor(new Color(27, 27, 27));
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    super.paintComponent(g);
                }
            };
        }
    }

//    private static class MyListCellRenderer extends JLabel implements ListCellRenderer<JLabel> {
//        public MyListCellRenderer() {
//            setOpaque(true);
//        }
//
//        @Override
//        public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel value, int index, boolean isSelected, boolean cellHasFocus) {
//            setIcon(new ImageIcon("src/main/resources/imgs/person.png"));
//            setHorizontalAlignment(SwingConstants.CENTER);
//            return this;
//        }
//    }

    private static class HintText implements FocusListener{
        private boolean isHintText = true;
        private final String hintText;
        private final JTextField textField;
        private HintText(String text, JTextField textField) {
            this.hintText = text;
            this.textField = textField;
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (isHintText) {
                textField.setText("");
                textField.setForeground(new Color(161,161,161));
            }
            isHintText = false;
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (textField.getText().length() == 0) {
                textField.setForeground(new Color(80,80,80));
                textField.setText(hintText);
                isHintText = true;
            }
        }
    }
}
