package biblioteca.frames;

import biblioteca.SesionActual;
import biblioteca.dao.MaterialDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultarMaterialFrame extends JFrame {

    private JTextField txtFiltro;
    private JTable tablaMateriales;
    private JButton btnBuscar, btnVolver;

    public ConsultarMaterialFrame() {
        initComponents();
        actualizarTabla("");
    }

    private void initComponents() {
        setTitle("Sistema de Biblioteca - Consulta de Materiales");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color azulFondo = new Color(0, 51, 120);
        getContentPane().setBackground(azulFondo);
        setLayout(new BorderLayout());

        // --- Panel Norte ---
        JPanel panelNorte = new JPanel(new GridBagLayout());
        panelNorte.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitulo = new JLabel("Consulta de Materiales");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 0, 30, 0);
        panelNorte.add(lblTitulo, gbc);

        JLabel lblBuscar = new JLabel("Buscar por título o código:  ");
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 20, 0);
        panelNorte.add(lblBuscar, gbc);

        txtFiltro = new JTextField(15);
        txtFiltro.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelNorte.add(txtFiltro, gbc);

        add(panelNorte, BorderLayout.NORTH);

        // --- Panel Central ---
        String[] columnas = {"Código", "Título", "Tipo", "Ubicación", "Stock", "Disponible"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaMateriales = new JTable(modelo);
        tablaMateriales.setRowHeight(30);
        tablaMateriales.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tablaMateriales);

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 40, 10, 40));
        scrollPane.setBackground(azulFondo);
        scrollPane.getViewport().setBackground(azulFondo);

        add(scrollPane, BorderLayout.CENTER);

        // --- Botones Volver y Busqueda ---
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        panelSur.setOpaque(false);
        panelSur.setBorder(BorderFactory.createEmptyBorder(10, 0, 50, 40));

        btnVolver = new JButton("Volver al Menú");
        btnBuscar = new JButton("Realizar Búsqueda");

        estilizarBoton(btnVolver, 180, 45);
        estilizarBoton(btnBuscar, 200, 45);

        btnBuscar.addActionListener(e -> actualizarTabla(txtFiltro.getText().trim()));
        btnVolver.addActionListener(e -> {
            new MenuFrame(SesionActual.getRol()).setVisible(true);
            this.dispose();
        });

        panelSur.add(btnVolver);
        panelSur.add(btnBuscar);
        add(panelSur, BorderLayout.SOUTH);
    }

    private void actualizarTabla(String filtro) {
        String[] columnas = {"Código", "Título", "Tipo", "Ubicación", "Stock", "Disponible"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        MaterialDAO dao = new MaterialDAO();
        ResultSet rs = dao.buscarMateriales(filtro);

        try {
            while (rs != null && rs.next()) {
                Object[] fila = {
                        rs.getString("codigo"),
                        rs.getString("titulo"),
                        rs.getString("tipo_material"),
                        rs.getString("ubicacion"),
                        rs.getInt("cantidad_total"),
                        rs.getInt("cantidad_disponible")
                };
                modelo.addRow(fila);
            }
            tablaMateriales.setModel(modelo);
        } catch (SQLException ex) {
            System.out.println("Error al cargar tabla: " + ex.getMessage());
        }
    }

    private void estilizarBoton(JButton btn, int ancho, int alto) {
        btn.setPreferredSize(new Dimension(ancho, alto));
        btn.setBackground(new Color(220, 220, 220));
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.white));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new ConsultarMaterialFrame().setVisible(true));
    }
}