package com.company.pages.main_page;

import javax.swing.*;
import java.awt.*;

public class MainChatPanel {
    private JPanel chatPanel;
    private JPanel rightPanel;
    private JPanel textFieldPanel;
    private JTextField textField;
    private JButton sendButton;
    private JScrollPane chatScrollPanel;
    private JPanel messagesPanel;
    private JList mainListOfChat;
    private DefaultListModel<String> listModelOfRightChats;

    public MainChatPanel() {
        textField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        textField.setOpaque(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder());
        mainListOfChat.setBorder(BorderFactory.createEmptyBorder());

        ((DefaultListCellRenderer)mainListOfChat.getCellRenderer()).setHorizontalAlignment(SwingConstants.RIGHT);

        sendButton.addActionListener(e -> {
            if (!textField.getText().equals(""))
                listModelOfRightChats.addElement(textField.getText());
            textField.setText("");
        });

        textField.addActionListener(e -> {
            if (!textField.getText().equals(""))
                listModelOfRightChats.addElement(textField.getText());
            textField.setText("");
        });

        sendButton.setIcon(new ImageIcon("src/main/resources/imgs/send.png"));

    }

    public JPanel getChatPanel() {
        return chatPanel;
    }

    private void createUIComponents() {
        listModelOfRightChats = new DefaultListModel<>();
        mainListOfChat = new JList<>(listModelOfRightChats);
        chatScrollPanel = new JScrollPane(mainListOfChat);


        textField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(44, 44, 44));
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }
        };
    }
}
