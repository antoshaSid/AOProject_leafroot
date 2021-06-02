package com.company.pages.main_page;

import com.company.Client.ClientSocket;
import com.company.Messages.Enums.MessageType;
import com.company.Messages.Message;
import com.company.Messages.MessageWrapperFactory;
import com.company.Messages.SendHolders.SendMessageHolder;
import com.company.Utilities.Generator;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

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
    private final String from;
    private final String to;

    public MainChatPanel(String from, String to) {
        this.from = from;
        this.to = to;

        textField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        textField.setOpaque(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder());
        mainListOfChat.setBorder(BorderFactory.createEmptyBorder());


        sendButton.addActionListener(e -> {
            if (!textField.getText().equals("")){
                ((DefaultListCellRenderer)mainListOfChat.getCellRenderer()).setHorizontalAlignment(SwingConstants.RIGHT);
                try {
                    ClientSocket.send(MessageWrapperFactory.createSendMessageWrapper(
                            new SendMessageHolder(from,to,new Message(MessageType.Text, textField.getText(), Generator.getRandomMessageID()))
                    ));
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | IOException | InvalidKeySpecException noSuchPaddingException) {
                    noSuchPaddingException.printStackTrace();
                }
                listModelOfRightChats.addElement(textField.getText());
                textField.setText("");
            }
        });

        textField.addActionListener(e -> {
            if (!textField.getText().equals("")){
                try {
                    ((DefaultListCellRenderer)mainListOfChat.getCellRenderer()).setHorizontalAlignment(SwingConstants.RIGHT);
                    ClientSocket.send(MessageWrapperFactory.createSendMessageWrapper(
                            new SendMessageHolder(from,to,new Message(MessageType.Text, textField.getText(), Generator.getRandomMessageID()))
                    ));
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | IOException | InvalidKeySpecException noSuchPaddingException) {
                    noSuchPaddingException.printStackTrace();
                }
                listModelOfRightChats.addElement(textField.getText());
                textField.setText("");
            }
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
