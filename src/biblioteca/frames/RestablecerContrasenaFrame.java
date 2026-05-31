package biblioteca.frames;

import biblioteca.dao.UsuarioDAO;
import javax.swing.*;
import java.awt.*;

// --- Modulo Restablecer Contraseña ---
public class RestablecerContrasenaFrame extends JFrame {
    private JTextField txtCarnet, txtCorreo;
    private JPasswordField txtNewPass, txtConfirmPass;
    private JButton btnActualizar, btnVolver;

    public RestablecerContrasenaFrame() {
        setTitle("Seguridad - Restablecer Contraseña");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 51, 120));
        setLayout(new GridBagLayout());

        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Cambio de Contraseña");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 30, 10);
        add(lblTitle, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1; add(crearLabel("Número de Carnet:"), gbc);
        gbc.gridx = 1; txtCarnet = new JTextField(15);
        txtCarnet.setFont(new Font("Arial", Font.PLAIN, 14));
        add(txtCarnet, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(crearLabel("Correo Electrónico:"), gbc);
        gbc.gridx = 1; txtCorreo = new JTextField(15);
        txtCorreo.setFont(new Font("Arial", Font.PLAIN, 14));
        add(txtCorreo, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(crearLabel("Nueva Contraseña:"), gbc);
        gbc.gridx = 1; txtNewPass = new JPasswordField(15); add(txtNewPass, gbc);

        gbc.gridx = 0; gbc.gridy = 4; add(crearLabel("Confirmar Contraseña:"), gbc);
        gbc.gridx = 1; txtConfirmPass = new JPasswordField(15); add(txtConfirmPass, gbc);

        // --- Botones ---
        btnVolver = new JButton("Volver");
        btnActualizar = new JButton("Actualizar");

        estilizarBoton(btnVolver, 140, 40);
        estilizarBoton(btnActualizar, 140, 40);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.insets = new Insets(30, 10, 10, 10);
        add(btnVolver, gbc);

        gbc.gridx = 1;
        add(btnActualizar, gbc);

        // --- Boton Volver ---
        btnVolver.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        // --- Boton Actualizar ---
        btnActualizar.addActionListener(e -> {
            String carnet    = txtCarnet.getText().trim();
            String correo    = txtCorreo.getText().trim();
            String nuevaPass = new String(txtNewPass.getPassword());
            String confirmar = new String(txtConfirmPass.getPassword());

            if (carnet.isEmpty() || correo.isEmpty() || nuevaPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!nuevaPass.equals(confirmar)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nuevaPass.length() < 4) {
                JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 4 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            UsuarioDAO dao = new UsuarioDAO();
            if (!dao.verificarCarnetCorreo(carnet, correo)) {
                JOptionPane.showMessageDialog(this, "El carnet y correo no coinciden con ningún usuario registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dao.actualizarPassword(carnet, nuevaPass);
            JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente. Ya puedes iniciar sesión.");
            new LoginFrame().setVisible(true);
            this.dispose();
        });
    }

    private JLabel crearLabel(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        return l;
    }

    // --- Método ---
    private void estilizarBoton(JButton btn, int ancho, int alto) {
        btn.setPreferredSize(new Dimension(ancho, alto));
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new RestablecerContrasenaFrame().setVisible(true));
    }
}