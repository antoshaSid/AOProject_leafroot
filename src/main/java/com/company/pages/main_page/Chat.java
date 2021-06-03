package com.company.pages.main_page;

import com.company.Client.ClientSocket;
import com.company.Data.Caching;
import com.company.Data.User;
import com.company.Messages.Enums.MessageAuthor;
import com.company.Messages.Enums.MessageType;
import com.company.Messages.Message;
import com.company.Messages.MessageWrapperFactory;
import com.company.Messages.SendHolders.SendMessageHolder;
import com.company.Utilities.Generator;
import com.company.Utilities.MessageUtilities;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

public class Chat {
    private JPanel chatPanel;
    private JPanel rightPanel;
    private JPanel textFieldPanel;
    private JTextField textField;
    private JButton sendButton;
    private JScrollPane chatScrollPanel;
    private JPanel messagesPanel;
    private JList<String> mainListOfMyChat;
    private JList<String> mainListOfYourChat;
    private DefaultListModel<String> listModelOfRightChat;
    private DefaultListModel<String> listModelOfLeftChat;
    private final String from;
    private final String to;

    public Chat(String from, String to) {
        this.from = from;
        this.to = to;

        textField.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        sendButton.setBorder(BorderFactory.createEmptyBorder());
        mainListOfMyChat.setBorder(BorderFactory.createEmptyBorder());

        ((DefaultListCellRenderer) mainListOfMyChat.getCellRenderer()).setHorizontalAlignment(SwingConstants.RIGHT);
        ((DefaultListCellRenderer) mainListOfYourChat.getCellRenderer()).setHorizontalAlignment(SwingConstants.LEFT);

        sendButton.addActionListener(e -> {
            if (!textField.getText().equals("")){

                try {
                    ClientSocket.send(MessageWrapperFactory.createSendMessageWrapper(
                            new SendMessageHolder(from,to,new Message(MessageType.Text, textField.getText(), Generator.getRandomMessageID()))
                    ));
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | IOException | InvalidKeySpecException noSuchPaddingException) {
                    noSuchPaddingException.printStackTrace();
                }
                listModelOfRightChat.addElement(textField.getText());
                listModelOfLeftChat.addElement("");
                textField.setText("");
            }
        });
        textField.addActionListener(e -> {
            if (!textField.getText().equals("")){
                try {

                    ClientSocket.send(MessageWrapperFactory.createSendMessageWrapper(
                            new SendMessageHolder(from,to,new Message(MessageType.Text, textField.getText(), Generator.getRandomMessageID()))
                    ));
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | IOException | InvalidKeySpecException noSuchPaddingException) {
                    noSuchPaddingException.printStackTrace();
                }
                listModelOfRightChat.addElement(textField.getText());
                listModelOfLeftChat.addElement("");
                textField.setText("");
            }
        });

        sendButton.setIcon(new ImageIcon("src/main/resources/imgs/send.png"));
    }

    public Chat(String from, String to, ArrayList<Message> messagesHistory) {
        this(from, to);

        for (Message message: messagesHistory) {
            if (message.sender == MessageAuthor.Me) {
                listModelOfRightChat.addElement(MessageUtilities.getDecodedTextMessage(message));
                listModelOfLeftChat.addElement("");
            }
            else {
                listModelOfLeftChat.addElement(MessageUtilities.getDecodedTextMessage(message));
                listModelOfRightChat.addElement("");
            }
        }
    }

    public JPanel getChatPanel() {
        return chatPanel;
    }

    private void createUIComponents() {
        listModelOfRightChat = new DefaultListModel<>();
        mainListOfMyChat = new JList<>(listModelOfRightChat);
        listModelOfLeftChat = new DefaultListModel<>();

        mainListOfYourChat = new JList<>(listModelOfLeftChat);
        chatScrollPanel = new JScrollPane(mainListOfMyChat);




        textField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(44, 44, 44));
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }
        };
    }

    public DefaultListModel<String> getListModelOfRightChat() {
        return listModelOfRightChat;
    }

    public DefaultListModel<String> getListModelOfLeftChat() {
        return listModelOfLeftChat;
    }
}
