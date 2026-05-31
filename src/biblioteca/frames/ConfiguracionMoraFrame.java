package biblioteca.frames;

import biblioteca.SesionActual;
import biblioteca.dao.ConfiguracionDAO;
import javax.swing.*;
import java.awt.*;

// --- Modulo de Moras  ---
public class ConfiguracionMoraFrame extends JFrame {

    private JTextField txtValorMora;
    private JButton btnGuardar, btnVolver;

    public ConfiguracionMoraFrame() {
        setTitle("Configuración de Parámetros - Mora");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 51, 120));
        setLayout(new GridBagLayout());

        initComponents();
        ConfiguracionDAO configDAO = new ConfiguracionDAO();
        txtValorMora.setText(String.valueOf(configDAO.getMoraDiaria()));
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // --- Título ---
        JLabel lblTitulo = new JLabel("Configuración de Mora Diaria");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, gbc);

        // --- Etiqueta ---
        JLabel lblMora = new JLabel("Valor de recargo por día ($):");
        lblMora.setForeground(Color.WHITE);
        lblMora.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblMora, gbc);

        // --- Caja de Texto Monto ---
        txtValorMora = new JTextField(12);
        txtValorMora.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtValorMora, gbc);

        // --- Botones Volver y Guardar ---
        btnVolver = new JButton("Volver al Menú");
        btnGuardar = new JButton("Guardar Cambios");

        estilizarBoton(btnVolver);
        estilizarBoton(btnGuardar);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(40, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnVolver, gbc);

        gbc.gridx = 1;
        add(btnGuardar, gbc);

        // --- ACCIÓN VOLVER AL MENÚ ---
        btnVolver.addActionListener(e -> {
            new MenuFrame(SesionActual.getRol()).setVisible(true);
            this.dispose();
        });

        // --- Acción Guardar ---
        btnGuardar.addActionListener(e -> {
            String texto = txtValorMora.getText().trim();
            try {
                double valor = Double.parseDouble(texto);
                ConfiguracionDAO configDAO = new ConfiguracionDAO();
                configDAO.setMoraDiaria(valor);
                JOptionPane.showMessageDialog(this, "Mora actualizada a $" + texto + " por día.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void estilizarBoton(JButton btn) {
        btn.setBackground(new Color(225, 225, 225));
        btn.setForeground(Color.BLACK);
        btn.setPreferredSize(new Dimension(180, 55));
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new ConfiguracionMoraFrame().setVisible(true));
    }
}