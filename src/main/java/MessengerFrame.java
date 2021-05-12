import javax.swing.*;

public class MessengerFrame extends JFrame {
    public MessengerFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 800);
        setVisible(true);
    }

    public static void main(String[] args) {
        MessengerFrame messengerFrame = new MessengerFrame();
    }
}
