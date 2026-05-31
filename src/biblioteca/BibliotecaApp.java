package biblioteca;

import biblioteca.frames.LoginFrame;
import javax.swing.*;

public class BibliotecaApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}