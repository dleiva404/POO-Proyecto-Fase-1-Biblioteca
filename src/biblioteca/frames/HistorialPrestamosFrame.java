package biblioteca.frames;

import biblioteca.SesionActual;
import biblioteca.dao.PrestamoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistorialPrestamosFrame extends JFrame {

    private JTable tablaHistorial;
    private JButton btnVolver;

    public HistorialPrestamosFrame() {
        setTitle("Sistema de Biblioteca - Historial de Préstamos");
        setSize(1100, 650); // Un poco más ancho para ver bien las fechas y la mora
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 51, 120));
        setLayout(new BorderLayout(15, 15));

        initComponents();
    }

    private void initComponents() {
        // --- Panel Header ---
        JPanel panelHeader = new JPanel();
        panelHeader.setOpaque(false);
        JLabel lblTitulo = new JLabel("Historial General de Préstamos");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        // --- Panel Central ---
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setOpaque(false);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 40, 10, 40));

        // Inicializar tabla con modelo no editable
        tablaHistorial = new JTable();
        tablaHistorial.setRowHeight(30);
        tablaHistorial.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(tablaHistorial);
        panelCentral.add(scroll, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        // --- Panel Botones ---
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        panelSur.setOpaque(false);

        btnVolver = new JButton("Volver al Menú");
        estilizarBoton(btnVolver);

        btnVolver.addActionListener(e -> {
            // Se asume que MenuFrame recibe el rol del usuario actual
            new MenuFrame(SesionActual.getRol()).setVisible(true);
            this.dispose();
        });

        panelSur.add(btnVolver);
        add(panelSur, BorderLayout.SOUTH);

        // Cargar datos
        actualizarTabla();
    }

    private void actualizarTabla() {
        String[] columnas = {"ID", "Material", "Usuario", "F. Préstamo", "F. Devolución", "Estado", "Mora ($)"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        PrestamoDAO dao = new PrestamoDAO();
        try (ResultSet rs = dao.listarHistorialPrestamos()) {
            while (rs != null && rs.next()) {
                Object[] fila = {
                        rs.getInt("id_prestamo"),
                        rs.getString("titulo"),
                        rs.getString("nombre") + " " + rs.getString("apellido"),
                        rs.getString("fecha_prestamo"),
                        rs.getString("fecha_devolucion"),
                        rs.getString("estado"),
                        String.format("$%.2f", rs.getDouble("mora"))
                };
                modelo.addRow(fila);
            }
            tablaHistorial.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage());
        }
    }

    private void estilizarBoton(JButton btn) {
        btn.setBackground(new Color(240, 240, 240));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(180, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new HistorialPrestamosFrame().setVisible(true));
    }
}