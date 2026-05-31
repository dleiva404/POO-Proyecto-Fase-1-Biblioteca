package biblioteca.frames;

import biblioteca.SesionActual;
import biblioteca.dao.UsuarioDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;


// --- Módulo de Login ---
public class LoginFrame extends JFrame {

    // --- Componentes de la Interfaz ---
    private JLabel lblTitulo, lblUsuario, lblPassword, lblLogo;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegistrar, btnRestablecer;

    public LoginFrame() {
        initComponents();
    }

    private void initComponents() {
        // --- Configuracion login ---
        setTitle("Sistema Biblioteca - Login");
        setSize(800, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(0, 51, 120, 255));

        // --- Gestión del Logo ---
        try {
            ImageIcon logoOriginal = new ImageIcon(getClass().getResource("/logo.png"));
            Image logoEscalado = logoOriginal.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            lblLogo = new JLabel(new ImageIcon(logoEscalado));
        } catch (Exception e) {
            lblLogo = new JLabel("LOGO COLEGIO");
            lblLogo.setForeground(Color.WHITE);
        }

        // --- Etiquetas y Campos ---
        lblTitulo = new JLabel("Sistema de Biblioteca");
        lblUsuario = new JLabel("Carnet");
        lblPassword = new JLabel("Contraseña");
        txtUsuario = new JTextField(20);
        txtPassword = new JPasswordField(20);

        txtUsuario.setPreferredSize(new Dimension(300, 30));
        txtPassword.setPreferredSize(new Dimension(300, 30));

        // --- Definición de Botones ---
        btnLogin = new JButton("Iniciar Sesión");
        btnRegistrar = new JButton("¿Usuario nuevo? Regístrate aquí");
        btnRestablecer = new JButton("Olvidé mi contraseña");

        // --- Estilo del Título ---
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);

        // --- Estilo de Etiquetas ---
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 20));
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 20));

        // --- Estilo Botón Principal (Login) ---
        btnLogin.setBackground(new Color(255, 184, 0));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setOpaque(true);
        btnLogin.setContentAreaFilled(true);
        btnLogin.setBorderPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Estilos para botones Registrar y Restablecer ---
        estilizarBotonSecundario(btnRegistrar);
        estilizarBotonSecundario(btnRestablecer);

        // --- Organización del Layout ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Posicionamiento del Logo ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(lblLogo, gbc);

        // --- Título ---
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo, gbc);

        // --- Formulario ---
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 2; add(lblUsuario, gbc);
        gbc.gridx = 1; add(txtUsuario, gbc);
        gbc.gridx = 0; gbc.gridy = 3; add(lblPassword, gbc);
        gbc.gridx = 1; add(txtPassword, gbc);

        // --- Botones ---
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        add(btnLogin, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(5, 10, 5, 10);
        add(btnRegistrar, gbc);
        gbc.gridy = 6;
        add(btnRestablecer, gbc);

        // --- Boton Registro ---
        btnRegistrar.addActionListener(e -> {
            new RegistroFrame().setVisible(true);
            this.dispose();
        });

        // --- Acción para ir a restablecer contraseña ---
        btnRestablecer.addActionListener(e -> {
            new RestablecerContrasenaFrame().setVisible(true);
            this.dispose();
        });

        // --- Boton Login ---
        btnLogin.addActionListener(e -> {
            String carnet   = txtUsuario.getText().trim();
            String password = new String(txtPassword.getPassword());

            if (carnet.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese su carnet y contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            UsuarioDAO dao = new UsuarioDAO();
            ResultSet rs = dao.validarLogin(carnet, password);

            try {
                if (rs != null && rs.next()) {
                    String rol = rs.getString("tipo_usuario");
                    int idUsuario = rs.getInt("id_usuario");
                    String nombre = rs.getString("nombre") + " " + rs.getString("apellido");

                    SesionActual.setRol(rol);
                    SesionActual.setIdUsuario(idUsuario);
                    SesionActual.setNombre(nombre);

                    new MenuFrame(SesionActual.getRol()).setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Carnet o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Error al validar login: " + ex.getMessage());
            }
        });
    }

    // --- Método auxiliar para diseño de botones ---
    private void estilizarBotonSecundario(JButton btn) {
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.ITALIC, 14));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}