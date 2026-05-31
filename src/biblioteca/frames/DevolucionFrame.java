package biblioteca.frames;

import biblioteca.SesionActual;
import biblioteca.dao.PrestamoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import biblioteca.modulos.DevolucionManager;

public class DevolucionFrame extends JFrame {

    private JTextField txtIdPrestamo, txtIdUsuario;
    private JTable tablaPrestamosActivos;
    private JButton btnDevolver, btnVolver;

    public DevolucionFrame() {
        setTitle("Sistema de Biblioteca - Gestión de Devoluciones");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color azulFondo = new Color(0, 51, 120);
        getContentPane().setBackground(azulFondo);
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        // --- Panel Header ---
        JPanel panelHeader = new JPanel();
        panelHeader.setOpaque(false);
        JLabel lblTitulo = new JLabel("Devolución de Materiales");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        // --- Panel Central ---
        JPanel panelCentral = new JPanel(new BorderLayout(15, 15));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        // --- Panel de Busqueda ---
        JPanel panelBusqueda = new JPanel(new GridBagLayout());
        panelBusqueda.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblId = new JLabel("ID Préstamo:");
        lblId.setForeground(Color.WHITE);
        lblId.setFont(new Font("Arial", Font.BOLD, 16));
        panelBusqueda.add(lblId, gbc);

        gbc.gridx = 1;
        txtIdPrestamo = new JTextField(15);
        panelBusqueda.add(txtIdPrestamo, gbc);

        panelCentral.add(panelBusqueda, BorderLayout.NORTH);

        // --- Tabla de Prestamos Activos ---
        String[] columnas = {"ID Préstamo", "ID Material", "Material", "Usuario", "Fecha Préstamo", "Fecha Devolución", "Estado", "Mora ($)"};
        DefaultTableModel model = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaPrestamosActivos = new JTable(model);
        tablaPrestamosActivos.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(tablaPrestamosActivos);
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        // --- Panel de botones ---
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        panelAcciones.setOpaque(false);
        panelAcciones.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 40));

        btnVolver = new JButton("Volver al Menú");
        btnDevolver = new JButton("Procesar Devolución");

        estilizarBoton(btnVolver);
        estilizarBoton(btnDevolver);

        btnVolver.addActionListener(e -> {
            new MenuFrame(SesionActual.getRol()).setVisible(true);
            this.dispose();
        });

        btnDevolver.addActionListener(e -> {
            int filaSeleccionada = tablaPrestamosActivos.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un préstamo de la tabla.");
                return;
            }

            int idPrestamo = Integer.parseInt(tablaPrestamosActivos.getValueAt(filaSeleccionada, 0).toString());
            int idMaterial = Integer.parseInt(tablaPrestamosActivos.getValueAt(filaSeleccionada, 1).toString());
            String material = tablaPrestamosActivos.getValueAt(filaSeleccionada, 2).toString();
            String fechaDevolucion = tablaPrestamosActivos.getValueAt(filaSeleccionada, 5).toString();

            PrestamoDAO dao = new PrestamoDAO();
            double mora = dao.calcularMora(fechaDevolucion);

            String mensaje = "¿Confirmar devolución de: " + material + "?";
            if (mora > 0) mensaje += "\n\nMora acumulada: $" + String.format("%.2f", mora);

            int confirmacion = JOptionPane.showConfirmDialog(this, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {

                boolean exito = DevolucionManager.procesarDevolucion(idPrestamo, idMaterial,
                        SesionActual.getNombre(), material);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "¡Éxito! El material ha sido devuelto.");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al procesar en base de datos.");
                }
            }
        });

        panelAcciones.add(btnVolver);
        panelAcciones.add(btnDevolver);
        add(panelAcciones, BorderLayout.SOUTH);

        actualizarTabla();
    }

    private void actualizarTabla() {
        String[] columnas = {"ID Préstamo", "ID Material", "Material", "Usuario", "Fecha Préstamo", "Fecha Devolución", "Estado", "Mora"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        PrestamoDAO dao = new PrestamoDAO();
        try (ResultSet rs = dao.listarPrestamosActivos()) {
            while (rs != null && rs.next()) {
                String fechaDev = rs.getString("fecha_devolucion");
                double moraCalculada = dao.calcularMora(fechaDev);

                Object[] fila = {
                        Integer.valueOf(rs.getInt("id_prestamo")),
                        Integer.valueOf(rs.getInt("id_material")),
                        rs.getString("titulo"),
                        rs.getString("nombre") + " " + rs.getString("apellido"),
                        rs.getString("fecha_prestamo"),
                        fechaDev,
                        rs.getString("estado"),
                        String.format("$%.2f", moraCalculada)
                };
                modelo.addRow(fila);
            }
            tablaPrestamosActivos.setModel(modelo);
            tablaPrestamosActivos.getColumnModel().getColumn(1).setMinWidth(0);
            tablaPrestamosActivos.getColumnModel().getColumn(1).setMaxWidth(0);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void estilizarBoton(JButton btn) {
        btn.setPreferredSize(new Dimension(210, 50));
        btn.setBackground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new DevolucionFrame().setVisible(true));
    }
}