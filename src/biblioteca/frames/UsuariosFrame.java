package biblioteca.frames;

import biblioteca.SesionActual;
import biblioteca.dao.UsuarioDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

// --- Módulo Gestión de Usuarios (Solo Administrador) --
public class UsuariosFrame extends JFrame {

    private JTable tablaUsuarios;
    private JButton btnCambiarRol, btnEliminar, btnVolver;

    public UsuariosFrame() {
        setTitle("Sistema de Biblioteca - Gestión de Usuarios");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 51, 120));
        setLayout(new BorderLayout(15, 15));

        initComponents();
    }

    private void initComponents() {
        // --- Inicio de Componentes ---
        JPanel panelHeader = new JPanel();
        panelHeader.setOpaque(false);
        JLabel lblTitulo = new JLabel("Gestión de Usuarios");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        // --- Panel Central ---
        JPanel panelCentral = new JPanel(new BorderLayout(20, 20));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        // --- Tabla de Usuarios ---
        String[] columnas = {"ID", "Carnet", "Nombre", "Apellido", "Correo", "Rol"};
        tablaUsuarios = new JTable(new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tablaUsuarios.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(tablaUsuarios);
        panelCentral.add(scroll, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // --- Botones ---
        JPanel panelBtns = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBtns.setOpaque(false);

        btnCambiarRol = new JButton("Cambiar Rol");
        btnEliminar   = new JButton("Eliminar");
        btnVolver     = new JButton("Volver al Menú");

        estilizarBoton(btnCambiarRol, Color.white, Color.BLACK);
        estilizarBoton(btnEliminar, Color.white, Color.BLACK);
        estilizarBoton(btnVolver, Color.white, Color.BLACK);

        panelBtns.add(btnVolver);
        panelBtns.add(btnEliminar);
        panelBtns.add(btnCambiarRol);
        add(panelBtns, BorderLayout.SOUTH);

        // --- Cargar tabla al abrir ---
        actualizarTabla();

        // --- Boton Volver ---
        btnVolver.addActionListener(e -> {
            new MenuFrame(SesionActual.getRol()).setVisible(true);
            this.dispose();
        });

        // --- Boton Cambiar Rol ---
        btnCambiarRol.addActionListener(e -> {
            int filaSeleccionada = tablaUsuarios.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idUsuario    = (int) tablaUsuarios.getValueAt(filaSeleccionada, 0);
            String nombre    = (String) tablaUsuarios.getValueAt(filaSeleccionada, 2);
            String rolActual = (String) tablaUsuarios.getValueAt(filaSeleccionada, 5);

            String[] roles = {"ALUMNO", "PROFESOR", "ADMINISTRADOR"};
            String nuevoRol = (String) JOptionPane.showInputDialog(
                    this,
                    "Seleccione el nuevo rol para " + nombre + ":",
                    "Cambiar Rol",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    roles,
                    rolActual
            );

            if (nuevoRol != null && !nuevoRol.equals(rolActual)) {
                UsuarioDAO dao = new UsuarioDAO();
                dao.actualizarRol(idUsuario, nuevoRol);
                JOptionPane.showMessageDialog(this, "Rol actualizado correctamente.");
                actualizarTabla();
            }
        });

        // --- Boton Eliminar ---
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaUsuarios.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idUsuario = (int) tablaUsuarios.getValueAt(filaSeleccionada, 0);
            String nombre = (String) tablaUsuarios.getValueAt(filaSeleccionada, 2);

            // --- Verificar si tiene préstamos activos ---
            UsuarioDAO dao = new UsuarioDAO();
            if (dao.tienePrestamosActivos(idUsuario)) {
                JOptionPane.showMessageDialog(this,
                        "No se puede eliminar a " + nombre + " porque tiene préstamos activos pendientes de devolución.",
                        "Eliminación no permitida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar a " + nombre + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                dao.eliminarUsuario(idUsuario);
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
                actualizarTabla();
            }
        });
    }

    private void actualizarTabla() {
        String[] columnas = {"ID", "Carnet", "Nombre", "Apellido", "Correo", "Rol"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        UsuarioDAO dao = new UsuarioDAO();
        ResultSet rs = dao.listarUsuariosResultSet();

        try {
            while (rs != null && rs.next()) {
                Object[] fila = {
                        rs.getInt("id_usuario"),
                        rs.getString("carnet"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("tipo_usuario")
                };
                modelo.addRow(fila);
            }
            tablaUsuarios.setModel(modelo);
        } catch (SQLException ex) {
            System.out.println("Error al cargar tabla: " + ex.getMessage());
        }
    }

    // --- Método de apoyo para etiquetas ---
    private void estilizarBoton(JButton btn, Color fondo, Color texto) {
        btn.setBackground(fondo);
        btn.setForeground(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(180, 45));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new UsuariosFrame().setVisible(true));
    }
}