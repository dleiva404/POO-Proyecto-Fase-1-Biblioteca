package biblioteca.frames;

import javax.swing.*;
import java.awt.*;
import biblioteca.dao.UsuarioDAO;

// --- Módulo de Registro Público ---
public class RegistroFrame extends JFrame {

    private JTextField txtCarnet, txtNombre, txtApellido, txtDui, txtTelefono, txtCorreo;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JButton btnRegistrar, btnVolver;

    public RegistroFrame() {
        setTitle("Sistema de Biblioteca - Registro de Usuario");
        setSize(600, 600);
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

        // --- Título ---
        JLabel lblTitulo = new JLabel("Registro de Usuario");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        // --- Campos ---
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1; add(crearLabel("Número de Carnet:"), gbc);
        gbc.gridx = 1; txtCarnet = new JTextField(15); add(txtCarnet, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(crearLabel("Nombres:"), gbc);
        gbc.gridx = 1; txtNombre = new JTextField(15); add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(crearLabel("Apellidos:"), gbc);
        gbc.gridx = 1; txtApellido = new JTextField(15); add(txtApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 4; add(crearLabel("DUI:"), gbc);
        gbc.gridx = 1; txtDui = new JTextField(15); add(txtDui, gbc);

        gbc.gridx = 0; gbc.gridy = 5; add(crearLabel("Teléfono:"), gbc);
        gbc.gridx = 1; txtTelefono = new JTextField(15); add(txtTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = 6; add(crearLabel("Correo:"), gbc);
        gbc.gridx = 1; txtCorreo = new JTextField(15); add(txtCorreo, gbc);

        gbc.gridx = 0; gbc.gridy = 7; add(crearLabel("Contraseña:"), gbc);
        gbc.gridx = 1; txtPassword = new JPasswordField(15); add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 8; add(crearLabel("Confirmar Contraseña:"), gbc);
        gbc.gridx = 1; txtConfirmPassword = new JPasswordField(15); add(txtConfirmPassword, gbc);

        // --- Botones ---
        btnVolver = new JButton("Volver");
        btnRegistrar = new JButton("Registrarse");
        estilizarBoton(btnVolver);
        estilizarBoton(btnRegistrar);

        gbc.gridx = 0; gbc.gridy = 9; add(btnVolver, gbc);
        gbc.gridx = 1; add(btnRegistrar, gbc);

        // --- Boton Volver ---
        btnVolver.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        // --- Boton Registrar ---
        btnRegistrar.addActionListener(e -> {
            String carnet    = txtCarnet.getText().trim();
            String nombre    = txtNombre.getText().trim();
            String apellido  = txtApellido.getText().trim();
            String dui       = txtDui.getText().trim();
            String telefono  = txtTelefono.getText().trim();
            String correo    = txtCorreo.getText().trim();
            String password  = new String(txtPassword.getPassword());
            String confirmar = new String(txtConfirmPassword.getPassword());

            if (carnet.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmar)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            UsuarioDAO dao = new UsuarioDAO();
            dao.insertarUsuario(nombre, apellido, carnet, dui, telefono, correo, "ALUMNO", password);

            JOptionPane.showMessageDialog(this, "Registro exitoso. Ya puedes iniciar sesión con tu carnet.");
            new LoginFrame().setVisible(true);
            this.dispose();
        });
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void estilizarBoton(JButton btn) {
        btn.setBackground(Color.white);
        btn.setForeground(Color.BLACK);
        btn.setPreferredSize(new Dimension(150, 40));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new RegistroFrame().setVisible(true));
    }
}