package main_page;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

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
        textField.setBorder(BorderFactory.createEmptyBorder());
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


}
