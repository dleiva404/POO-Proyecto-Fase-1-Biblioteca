package biblioteca.frames;

import biblioteca.SesionActual;
import biblioteca.dao.PrestamoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import biblioteca.modulos.PrestamoManager;

// --- Módulo Préstamos ---
public class PrestamoFrame extends JFrame {

    private JTextField txtBuscarMaterial;
    private JTable tablaMateriales;
    private JButton btnPrestar, btnVolver;
    private JLabel lblUsuario;

    public PrestamoFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Préstamos - Sistema Biblioteca");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 51, 120));
        setLayout(new BorderLayout(20, 20));

        // --- Panel de Búsqueda ---
        JPanel panelNorte = new JPanel(new FlowLayout());
        panelNorte.setOpaque(false);
        panelNorte.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel lblBusca = new JLabel("Buscar Material (Título/Código):");
        lblBusca.setForeground(Color.WHITE);
        lblBusca.setFont(new Font("Arial", Font.BOLD, 14));

        txtBuscarMaterial = new JTextField(25);
        JButton btnBuscar = new JButton("Buscar");
        estilizarBoton(btnBuscar, Color.white, Color.BLACK);

        panelNorte.add(lblBusca);
        panelNorte.add(txtBuscarMaterial);
        panelNorte.add(btnBuscar);
        add(panelNorte, BorderLayout.NORTH);

        // --- Tabla de Materiales Disponibles ---
        String[] columnas = {"ID", "Código", "Título", "Tipo", "Disponibles"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaMateriales = new JTable(modelo);
        tablaMateriales.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tablaMateriales);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().setBackground(new Color(0, 51, 120));
        scrollPane.setBackground(new Color(0, 51, 120));
        scrollPane.setOpaque(false);

        // --- Panel Sur ---
        JPanel panelSur = new JPanel(new GridBagLayout());
        panelSur.setOpaque(false);
        panelSur.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        lblUsuario = new JLabel("");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));

        // --- Botones Realizar y Volver ---
        btnPrestar = new JButton("Realizar Préstamo");
        btnVolver  = new JButton("Volver al Menú");

        estilizarBoton(btnVolver, Color.WHITE, Color.BLACK);
        estilizarBoton(btnPrestar, Color.WHITE, Color.BLACK);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; panelSur.add(lblUsuario, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1; panelSur.add(btnVolver, gbc);
        gbc.gridx = 1; panelSur.add(btnPrestar, gbc);

        add(panelSur, BorderLayout.SOUTH);

        actualizarTabla();

        btnBuscar.addActionListener(e -> actualizarTabla());

        btnVolver.addActionListener(e -> {
            new MenuFrame(SesionActual.getRol()).setVisible(true);
            this.dispose();
        });

        btnPrestar.addActionListener(e -> {
            int filaSeleccionada = tablaMateriales.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un material de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idMaterial  = (int) tablaMateriales.getValueAt(filaSeleccionada, 0);
            String titulo   = (String) tablaMateriales.getValueAt(filaSeleccionada, 2);
            int disponibles = (int) tablaMateriales.getValueAt(filaSeleccionada, 4);

            PrestamoDAO prestamoDAO = new PrestamoDAO();

            if (disponibles <= 0) {
                JOptionPane.showMessageDialog(this, "No hay ejemplares disponibles de este material.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int activos   = prestamoDAO.contarPrestamosActivos(SesionActual.getIdUsuario());
            int limiteMax = prestamoDAO.getLimitePrestamos(SesionActual.getRol());
            if (activos >= limiteMax) {
                JOptionPane.showMessageDialog(this,
                        "Ha alcanzado el límite de " + limiteMax + " préstamos activos para su tipo de usuario.",
                        "Límite alcanzado",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (prestamoDAO.tieneMaterialPrestado(SesionActual.getIdUsuario(), idMaterial)) {
                JOptionPane.showMessageDialog(this,
                        "Ya tiene este material prestado. Debe devolverlo antes de volver a solicitarlo.",
                        "Material ya prestado",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Confirmar préstamo de: " + titulo + "?",
                    "Confirmar Préstamo",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = PrestamoManager.crearPrestamo(SesionActual.getIdUsuario(), idMaterial, SesionActual.getRol());
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Préstamo registrado correctamente.");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el préstamo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void actualizarTabla() {
        String[] columnas = {"ID", "Código", "Título", "Tipo", "Disponibles"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String filtro = txtBuscarMaterial.getText().trim().toLowerCase();
        PrestamoDAO dao = new PrestamoDAO();
        ResultSet rs = dao.listarMaterialesDisponibles();

        try {
            while (rs != null && rs.next()) {
                String titulo = rs.getString("titulo");
                String codigo = rs.getString("codigo");
                if (filtro.isEmpty() || titulo.toLowerCase().contains(filtro) || codigo.toLowerCase().contains(filtro)) {
                    Object[] fila = {
                            rs.getInt("id_material"),
                            codigo,
                            titulo,
                            rs.getString("tipo_material"),
                            rs.getInt("cantidad_disponible")
                    };
                    modelo.addRow(fila);
                }
            }
            tablaMateriales.setModel(modelo);
        } catch (SQLException ex) {
            System.out.println("Error al cargar tabla: " + ex.getMessage());
        }

        PrestamoDAO dao2 = new PrestamoDAO();
        int activos = dao2.contarPrestamosActivos(SesionActual.getIdUsuario());
        int limite  = dao2.getLimitePrestamos(SesionActual.getRol());
        lblUsuario.setText("Usuario: " + SesionActual.getNombre() +
                "  |  Préstamos activos: " + activos + "/" + limite);
    }

    private void estilizarBoton(JButton btn, Color fondo, Color texto) {
        btn.setBackground(fondo);
        btn.setForeground(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(175, 35));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new PrestamoFrame().setVisible(true);
        });
    }
}