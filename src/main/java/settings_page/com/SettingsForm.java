package settings_page.com;

import javax.swing.*;

public class SettingsForm extends JFrame {
    private JButton button1;
    private JPanel myPanel;
    private JFormattedTextField dfdffdFormattedTextField;

    public SettingsForm(String s){
        super(s);
        setContentPane(myPanel);
        setVisible(true);

        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        new SettingsForm("New program");
    }
}
