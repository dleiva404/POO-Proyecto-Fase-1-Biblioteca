package biblioteca.frames;

import javax.swing.*;
import java.awt.*;

// --- Menú Principal ---
public class MenuFrame extends JFrame {

    private JButton btnMateriales, btnConsultar, btnPrestamos, btnDevoluciones,btnConfigMora, btnHistorial, btnUsuarios, btnCerrarSesion;
    private JLabel lblTitulo, lblSubtitulo;
    private String rolUsuario;

    public MenuFrame(String rolUsuario) {
        this.rolUsuario = rolUsuario;
        initComponents();
    }

    private void initComponents() {
        // --- Configuración ---
        setTitle("Sistema de Biblioteca - Menú Principal");
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 51, 120));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // --- Encabezado ---
        lblTitulo = new JLabel("Menú Principal");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(lblTitulo, gbc);

        lblSubtitulo = new JLabel("Seleccione una opción para continuar");
        lblSubtitulo.setFont(new Font("Arial", Font.ITALIC, 18));
        lblSubtitulo.setForeground(new Color(200, 200, 200));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 40, 0);
        add(lblSubtitulo, gbc);

        // --- Botones de Módulos ---
        Font fuenteBotones = new Font("Arial", Font.BOLD, 18);
        gbc.insets = new Insets(15, 15, 15, 15);

        if (rolUsuario.equalsIgnoreCase("ALUMNO")) {
            // --- Menú ALUMNO ---
            btnConsultar = crearBoton("Consultar Inventario", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
            add(btnConsultar, gbc);

            btnPrestamos = crearBoton("Realizar Préstamo", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
            add(btnPrestamos, gbc);

            btnCerrarSesion = crearBoton("Cerrar Sesión", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
            add(btnCerrarSesion, gbc);

        } else if (rolUsuario.equalsIgnoreCase("PROFESOR")) {
            // --- Menú PROFESOR ---
            gbc.gridwidth = 1;

            btnMateriales = crearBoton("Registrar Material", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 2;
            add(btnMateriales, gbc);

            btnConsultar = crearBoton("Consultar Inventario", fuenteBotones);
            gbc.gridx = 1; gbc.gridy = 2;
            add(btnConsultar, gbc);

            btnPrestamos = crearBoton("Realizar Préstamo", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 3;
            add(btnPrestamos, gbc);

            btnDevoluciones = crearBoton("Devoluciones", fuenteBotones);
            gbc.gridx = 1; gbc.gridy = 3;
            add(btnDevoluciones, gbc);

            btnHistorial = crearBoton("Historial de Préstamos", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
            add(btnHistorial, gbc);

            btnCerrarSesion = crearBoton("Cerrar Sesión", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
            add(btnCerrarSesion, gbc);

            btnMateriales.addActionListener(e -> { new MaterialFrame().setVisible(true); this.dispose(); });
            btnDevoluciones.addActionListener(e -> { new DevolucionFrame().setVisible(true); this.dispose(); });
            btnHistorial.addActionListener(e -> { new HistorialPrestamosFrame().setVisible(true); this.dispose(); });

        } else {
            // --- Menú ADMINISTRADOR ---
            gbc.gridwidth = 1;

            // --- Registro Material ---
            btnMateriales = crearBoton("Registrar Material", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 2;
            add(btnMateriales, gbc);

            // --- Consultar Material ---
            btnConsultar = crearBoton("Consultar Inventario", fuenteBotones);
            gbc.gridx = 1; gbc.gridy = 2;
            add(btnConsultar, gbc);

            // --- Realizar Prestamo ---
            btnPrestamos = crearBoton("Realizar Préstamo", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 3;
            add(btnPrestamos, gbc);

            btnDevoluciones = crearBoton("Devoluciones", fuenteBotones);
            gbc.gridx = 1; gbc.gridy = 3;
            add(btnDevoluciones, gbc);

            // --- Historial Prestamos ---
            btnHistorial = crearBoton("Historial de Préstamos", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 4;
            add(btnHistorial, gbc);

            // --- Configuracion Mora ---
            btnConfigMora = crearBoton("Configuración Mora", fuenteBotones);
            gbc.gridx = 1; gbc.gridy = 4;
            add(btnConfigMora, gbc);

            // --- Gestión de Usuarios ---
            btnUsuarios = crearBoton("Gestión de Usuarios", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
            add(btnUsuarios, gbc);

            // Fila 6: Cerrar Sesión
            btnCerrarSesion = crearBoton("Cerrar Sesión", fuenteBotones);
            gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.NONE;
            add(btnCerrarSesion, gbc);

            // --- Action Listeners específicos ---
            btnMateriales.addActionListener(e -> { new MaterialFrame().setVisible(true); this.dispose(); });
            btnDevoluciones.addActionListener(e -> { new DevolucionFrame().setVisible(true); this.dispose(); });
            btnHistorial.addActionListener(e -> { new HistorialPrestamosFrame().setVisible(true); this.dispose(); });
            btnUsuarios.addActionListener(e -> { new UsuariosFrame().setVisible(true); this.dispose(); });

            // Acción para abrir la Configuración de Mora
            btnConfigMora.addActionListener(e -> {
                new ConfiguracionMoraFrame().setVisible(true);
                this.dispose();
            });
        }
        // --- Eventos comunes ---
        btnConsultar.addActionListener(e -> { new ConsultarMaterialFrame().setVisible(true); this.dispose(); });
        btnPrestamos.addActionListener(e -> { new PrestamoFrame().setVisible(true); this.dispose(); });
        btnCerrarSesion.addActionListener(e -> { new LoginFrame().setVisible(true); this.dispose(); });
    }

    // --- Método Auxiliar ----
    private JButton crearBoton(String texto, Font fuente) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(300, 70));
        boton.setFont(fuente);
        boton.setFocusPainted(false);
        boton.setBackground(Color.WHITE);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { e.printStackTrace(); }

        EventQueue.invokeLater(() -> {
            new MenuFrame("ADMINISTRADOR").setVisible(true);
        });
    }
}