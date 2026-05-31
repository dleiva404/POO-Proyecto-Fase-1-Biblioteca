package biblioteca.frames;

import biblioteca.SesionActual;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import biblioteca.dao.MaterialDAO;
import com.toedter.calendar.JDateChooser;

// Modulo Material
public class MaterialFrame extends JFrame {

    // campos base
    private JTextField txtCodigo, txtTitulo, txtCategoria, txtUbicacion, txtCantidad;
    private JComboBox<String> cbTipo, cmbTipoPasta, cmbIdiomaLibro, cmbIdiomaRevista, cmbIdiomaTesis, cmbIdiomaDoc, cmbIdiomaCD, cmbGeneroLibro, cmbGeneroCD, cmbGrado, cmbAutor, cmbEditorialLibro;
    private JPanel panelDinamico;
    private CardLayout cardLayout;

    // libro
    private JDateChooser fechaAnioLibro;
    private JTextField txtISBN, txtEdicionLibro, txtNumPaginasLibro;

    // revista
    private JDateChooser fechaPublicacion;
    private JTextField txtISSN, txtPeriodicidad, txtNumEdicionRevista, txtEditorialRevista, txtNumPaginasRevista;

    // tesis
    private JDateChooser fechaAnioTesis;
    private JTextField txtAutorTesis, txtCarrera, txtTema, txtAsesor, txtUniversidad, txtEditorialTesis, txtNumPaginasTesis;

    // documento
    private JDateChooser fechaAnioDoc;
    private JTextField txtAutorDoc, txtTipoDocumento, txtEditorialDoc, txtNumPaginasDoc;

    // cd
    private JDateChooser fechaAnioCD;
    private JTextField txtArtista, txtDisquera, txtCanciones, txtDuracion;

    private JButton btnGuardar, btnVolver;

    public MaterialFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Registro de Materiales - Colegio Don Bosco");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 51, 120));

        setLayout(new GridBagLayout());
        GridBagConstraints gbcPrincipal = new GridBagConstraints();

        JPanel cuadroPrincipal = new JPanel(new GridBagLayout());
        cuadroPrincipal.setOpaque(false);
        cuadroPrincipal.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        GridBagConstraints gbcInner = new GridBagConstraints();
        gbcInner.insets = new Insets(20, 40, 20, 40);
        gbcInner.fill = GridBagConstraints.HORIZONTAL;

        JPanel panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTipo = new JLabel("Tipo de Material:");
        estiloLabel(lblTipo);
        g.gridx = 0; g.gridy = 0; g.anchor = GridBagConstraints.EAST;
        panelSuperior.add(lblTipo, g);

        cbTipo = new JComboBox<>(new String[]{"Libro", "Revista", "Tesis", "Documento", "CD/DVD"});
        g.gridx = 1; g.weightx = 1.0;
        panelSuperior.add(cbTipo, g);

        txtCodigo = agregarCampo("Código:", 1, panelSuperior, g);
        txtCodigo.setEditable(false);
        txtCodigo.setFocusable(false);
        txtCodigo.setBackground(new Color(220, 220, 220));
        txtCodigo.setText("AUTOGENERADO");

        txtTitulo    = agregarCampo("Título:", 2, panelSuperior, g);
        txtCategoria = agregarCampo("Categoría:", 3, panelSuperior, g);
        txtUbicacion = agregarCampo("Ubicación:", 4, panelSuperior, g);
        txtCantidad  = agregarCampo("Cantidad:", 5, panelSuperior, g);

        gbcInner.gridy = 0;
        cuadroPrincipal.add(panelSuperior, gbcInner);

        cardLayout = new CardLayout();
        panelDinamico = new JPanel(cardLayout);
        panelDinamico.setOpaque(false);
        panelDinamico.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE), "Detalles Específicos", 0, 0,
                new Font("Arial", Font.BOLD, 14), Color.WHITE));

        panelDinamico.add(crearPanelLibro(),     "Libro");
        panelDinamico.add(crearPanelRevista(),   "Revista");
        panelDinamico.add(crearPanelTesis(),     "Tesis");
        panelDinamico.add(crearPanelDocumento(), "Documento");
        panelDinamico.add(crearPanelCD(),        "CD/DVD");

        gbcInner.gridy = 1;
        cuadroPrincipal.add(panelDinamico, gbcInner);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        panelBotones.setOpaque(false);

        btnGuardar = new JButton("Guardar Registro");
        btnVolver  = new JButton("Volver al Menú");

        estiloBoton(btnGuardar);
        estiloBoton(btnVolver);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);

        gbcInner.gridy = 2;
        cuadroPrincipal.add(panelBotones, gbcInner);

        add(cuadroPrincipal, gbcPrincipal);

        cbTipo.addActionListener(e -> cardLayout.show(panelDinamico, (String) cbTipo.getSelectedItem()));

        btnGuardar.addActionListener(e -> {
            try {
                String titulo      = txtTitulo.getText();
                String categoria   = txtCategoria.getText();
                String ubicacion   = txtUbicacion.getText();
                String cantidadStr = txtCantidad.getText();
                String tipo        = cbTipo.getSelectedItem().toString();

                if (titulo.isEmpty() || ubicacion.isEmpty() || cantidadStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Complete los campos básicos.");
                    return;
                }

                int cantidad    = Integer.parseInt(cantidadStr);
                MaterialDAO dao = new MaterialDAO();

                String tipoEnum = tipo.equalsIgnoreCase("CD/DVD") ? "CD" : tipo.toUpperCase();
                int idGenerado  = dao.insertarMaterial("TEMP", titulo, categoria, ubicacion, cantidad, cantidad, tipoEnum);

                if (idGenerado != -1) {
                    switch (tipo.toUpperCase()) {
                        case "LIBRO":
                            int anioLibro = fechaAnioLibro.getDate() != null
                                    ? Integer.parseInt(new SimpleDateFormat("yyyy").format(fechaAnioLibro.getDate()))
                                    : Calendar.getInstance().get(Calendar.YEAR);
                            dao.guardarDetallesLibro(idGenerado, cmbAutor.getSelectedItem().toString(), cmbEditorialLibro.getSelectedItem().toString(), parsearInt(txtNumPaginasLibro.getText()), txtISBN.getText(), anioLibro, txtEdicionLibro.getText(), cmbTipoPasta.getSelectedItem().toString(), cmbIdiomaLibro.getSelectedItem().toString(), cmbGeneroLibro.getSelectedItem().toString());
                            break;
                        case "REVISTA":
                            String fecha = fechaPublicacion.getDate() != null
                                    ? new SimpleDateFormat("yyyy-MM-dd").format(fechaPublicacion.getDate())
                                    : "";
                            dao.guardarDetallesRevista(idGenerado, txtEditorialRevista.getText(), parsearInt(txtNumPaginasRevista.getText()), cmbIdiomaRevista.getSelectedItem().toString(), fecha, txtISSN.getText(), txtPeriodicidad.getText(), parsearInt(txtNumEdicionRevista.getText()));
                            break;
                        case "TESIS":
                            int anioTesis = fechaAnioTesis.getDate() != null
                                    ? Integer.parseInt(new SimpleDateFormat("yyyy").format(fechaAnioTesis.getDate()))
                                    : Calendar.getInstance().get(Calendar.YEAR);
                            dao.guardarDetallesTesis(idGenerado, txtAutorTesis.getText(), txtEditorialTesis.getText(), parsearInt(txtNumPaginasTesis.getText()), txtCarrera.getText(), txtTema.getText(), txtAsesor.getText(), txtUniversidad.getText(), cmbGrado.getSelectedItem().toString(), cmbIdiomaTesis.getSelectedItem().toString(), anioTesis);
                            break;
                        case "DOCUMENTO":
                            int anioDoc = fechaAnioDoc.getDate() != null
                                    ? Integer.parseInt(new SimpleDateFormat("yyyy").format(fechaAnioDoc.getDate()))
                                    : Calendar.getInstance().get(Calendar.YEAR);
                            dao.guardarDetallesDocumento(idGenerado, txtAutorDoc.getText(), txtEditorialDoc.getText(), parsearInt(txtNumPaginasDoc.getText()), txtTipoDocumento.getText(), cmbIdiomaDoc.getSelectedItem().toString(), anioDoc);
                            break;
                        case "CD/DVD":
                            int anioCD = fechaAnioCD.getDate() != null
                                    ? Integer.parseInt(new SimpleDateFormat("yyyy").format(fechaAnioCD.getDate()))
                                    : Calendar.getInstance().get(Calendar.YEAR);
                            dao.guardarDetallesCD(idGenerado, txtArtista.getText(), txtDisquera.getText(), cmbGeneroCD.getSelectedItem().toString(), cmbIdiomaCD.getSelectedItem().toString(), parsearInt(txtCanciones.getText()), anioCD, parsearInt(txtDuracion.getText()));
                            break;
                    }

                    String prefijo = "MAT";
                    switch (tipo.toUpperCase()) {
                        case "LIBRO":     prefijo = "LB";  break;
                        case "REVISTA":   prefijo = "RV";  break;
                        case "TESIS":     prefijo = "TS";  break;
                        case "DOCUMENTO": prefijo = "DOC"; break;
                        case "CD/DVD":    prefijo = "CD";  break;
                    }
                    String codigoFinal = String.format("%s%03d", prefijo, idGenerado);
                    dao.actualizarCodigo(idGenerado, codigoFinal);
                    txtCodigo.setText(codigoFinal);

                    JOptionPane.showMessageDialog(this, "Registro exitoso de " + tipo + ".\nCódigo: " + codigoFinal);
                    limpiarFormulario();

                    MaterialDAO daoActualizar = new MaterialDAO();
                    cmbAutor.setModel(new DefaultComboBoxModel<>(daoActualizar.obtenerAutores()));
                    cmbEditorialLibro.setModel(new DefaultComboBoxModel<>(daoActualizar.obtenerEditoriales()));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> {
            new MenuFrame(SesionActual.getRol()).setVisible(true);
            this.dispose();
        });
    }

    private JDateChooser crearDateChooserAnio() {
        JDateChooser dc = new JDateChooser();
        dc.setDateFormatString("yyyy");
        dc.setPreferredSize(new Dimension(136, 22));
        return dc;
    }

    private int parsearInt(String texto) {
        try { return Integer.parseInt(texto.trim()); }
        catch (NumberFormatException e) { return 0; }
    }

    private void limpiarFormulario() {
        txtTitulo.setText("");
        txtCategoria.setText("");
        txtUbicacion.setText("");
        txtCantidad.setText("");
        txtCodigo.setText("AUTOGENERADO");

        cmbAutor.setSelectedIndex(0);
        cmbEditorialLibro.setSelectedIndex(0);
        txtNumPaginasLibro.setText("");
        txtISBN.setText("");
        fechaAnioLibro.setDate(null);
        txtEdicionLibro.setText("");
        cmbTipoPasta.setSelectedIndex(0);
        cmbIdiomaLibro.setSelectedIndex(0);
        cmbGeneroLibro.setSelectedIndex(0);

        txtEditorialRevista.setText("");
        txtNumPaginasRevista.setText("");
        txtISSN.setText("");
        txtPeriodicidad.setText("");
        cmbIdiomaRevista.setSelectedIndex(0);
        fechaPublicacion.setDate(null);
        txtNumEdicionRevista.setText("");

        txtAutorTesis.setText("");
        txtEditorialTesis.setText("");
        txtNumPaginasTesis.setText("");
        txtCarrera.setText("");
        txtTema.setText("");
        txtAsesor.setText("");
        txtUniversidad.setText("");
        cmbGrado.setSelectedIndex(0);
        cmbIdiomaTesis.setSelectedIndex(0);
        fechaAnioTesis.setDate(null);

        txtAutorDoc.setText("");
        txtEditorialDoc.setText("");
        txtNumPaginasDoc.setText("");
        txtTipoDocumento.setText("");
        cmbIdiomaDoc.setSelectedIndex(0);
        fechaAnioDoc.setDate(null);

        txtArtista.setText("");
        txtDisquera.setText("");
        cmbGeneroCD.setSelectedIndex(0);
        cmbIdiomaCD.setSelectedIndex(0);
        txtCanciones.setText("");
        fechaAnioCD.setDate(null);
        txtDuracion.setText("");

        txtTitulo.requestFocus();
    }

    private JPanel crearPanelLibro() {
        JPanel p = new JPanel(new GridBagLayout()); p.setOpaque(false);
        GridBagConstraints g = iniciarGbc();
        MaterialDAO dao = new MaterialDAO();
        g.gridy = 0; g.gridx = 0;
        JLabel lblAutor = new JLabel("Autor:");
        estiloLabel(lblAutor);
        p.add(lblAutor, g);
        g.gridx = 1;
        cmbAutor = new JComboBox<>(dao.obtenerAutores());
        cmbAutor.setPreferredSize(new Dimension(136, 22));
        cmbAutor.setEditable(true);
        p.add(cmbAutor, g);
        g.gridy = 0; g.gridx = 2;
        JLabel lblEditorialLibro = new JLabel("Editorial:");
        estiloLabel(lblEditorialLibro);
        p.add(lblEditorialLibro, g);
        g.gridx = 3;
        cmbEditorialLibro = new JComboBox<>(dao.obtenerEditoriales());
        cmbEditorialLibro.setPreferredSize(new Dimension(136, 22));
        cmbEditorialLibro.setEditable(true);
        p.add(cmbEditorialLibro, g);
        txtNumPaginasLibro = addComp(p, "Número de Páginas:", 1, 0, g);
        txtISBN            = addComp(p, "ISBN:", 1, 1, g);

        g.gridy = 2; g.gridx = 0;
        JLabel lblAnioLibro = new JLabel("Año de Publicación:");
        estiloLabel(lblAnioLibro);
        p.add(lblAnioLibro, g);
        g.gridx = 1;
        fechaAnioLibro = crearDateChooserAnio();
        p.add(fechaAnioLibro, g);

        txtEdicionLibro = addComp(p, "Edición:", 2, 1, g);
        g.gridy = 3; g.gridx = 0;
        JLabel lblTipoPasta = new JLabel("Tipo Pasta:");
        estiloLabel(lblTipoPasta);
        p.add(lblTipoPasta, g);
        g.gridx = 1;
        cmbTipoPasta = new JComboBox<>(new String[]{"Dura", "Blanda"});
        cmbTipoPasta.setPreferredSize(new Dimension(136, 22));
        p.add(cmbTipoPasta, g);
        g.gridy = 3; g.gridx = 2;
        JLabel lblIdiomaLibro = new JLabel("Idioma:");
        estiloLabel(lblIdiomaLibro);
        p.add(lblIdiomaLibro, g);
        g.gridx = 3;
        cmbIdiomaLibro = new JComboBox<>(new String[]{"Español", "Inglés", "Francés"});
        cmbIdiomaLibro.setPreferredSize(new Dimension(136, 22));
        p.add(cmbIdiomaLibro, g);
        g.gridy = 4; g.gridx = 0;
        JLabel lblGeneroLibro = new JLabel("Género:");
        estiloLabel(lblGeneroLibro);
        p.add(lblGeneroLibro, g);
        g.gridx = 1;
        cmbGeneroLibro = new JComboBox<>(new String[]{"Educativo", "Ficción", "Ciencia Ficción", "Historia", "Tecnología"});
        cmbGeneroLibro.setPreferredSize(new Dimension(136, 22));
        p.add(cmbGeneroLibro, g);
        return p;
    }

    private JPanel crearPanelRevista() {
        JPanel p = new JPanel(new GridBagLayout()); p.setOpaque(false);
        GridBagConstraints g = iniciarGbc();
        txtEditorialRevista  = addComp(p, "Editorial:", 0, 0, g);
        txtNumPaginasRevista = addComp(p, "Número de Páginas:", 0, 1, g);
        txtISSN              = addComp(p, "ISSN:", 1, 0, g);
        txtPeriodicidad      = addComp(p, "Periodicidad:", 1, 1, g);
        g.gridy = 2; g.gridx = 0;
        JLabel lblIdiomaRevista = new JLabel("Idioma:");
        estiloLabel(lblIdiomaRevista);
        p.add(lblIdiomaRevista, g);
        g.gridx = 1;
        cmbIdiomaRevista = new JComboBox<>(new String[]{"Español", "Inglés", "Francés", "Portugués"});
        cmbIdiomaRevista.setPreferredSize(new Dimension(136, 22));
        p.add(cmbIdiomaRevista, g);
        g.gridy = 2; g.gridx = 2;
        JLabel lblFecha = new JLabel("Fecha Publicación:");
        estiloLabel(lblFecha);
        p.add(lblFecha, g);
        g.gridx = 3;
        fechaPublicacion = new JDateChooser();
        fechaPublicacion.setDateFormatString("yyyy-MM-dd");
        fechaPublicacion.setPreferredSize(new Dimension(136, 22));
        p.add(fechaPublicacion, g);
        txtNumEdicionRevista = addComp(p, "N° Edición:", 3, 0, g);
        return p;
    }

    private JPanel crearPanelTesis() {
        JPanel p = new JPanel(new GridBagLayout()); p.setOpaque(false);
        GridBagConstraints g = iniciarGbc();
        txtAutorTesis      = addComp(p, "Autor:", 0, 0, g);
        txtEditorialTesis  = addComp(p, "Editorial:", 0, 1, g);
        txtNumPaginasTesis = addComp(p, "Número de Páginas:", 1, 0, g);
        txtCarrera         = addComp(p, "Carrera:", 1, 1, g);
        txtTema            = addComp(p, "Tema:", 2, 0, g);
        txtAsesor          = addComp(p, "Asesor:", 2, 1, g);
        txtUniversidad     = addComp(p, "Universidad:", 3, 0, g);
        g.gridy = 3; g.gridx = 2;
        JLabel lblGrado = new JLabel("Grado:");
        estiloLabel(lblGrado);
        p.add(lblGrado, g);
        g.gridx = 3;
        cmbGrado = new JComboBox<>(new String[]{"Licenciatura", "Ingeniería", "Maestría", "Doctorado", "Técnico"});
        cmbGrado.setPreferredSize(new Dimension(136, 22));
        p.add(cmbGrado, g);
        g.gridy = 4; g.gridx = 0;
        JLabel lblIdiomaTesis = new JLabel("Idioma:");
        estiloLabel(lblIdiomaTesis);
        p.add(lblIdiomaTesis, g);
        g.gridx = 1;
        cmbIdiomaTesis = new JComboBox<>(new String[]{"Español", "Inglés", "Francés", "Portugués"});
        cmbIdiomaTesis.setPreferredSize(new Dimension(136, 22));
        p.add(cmbIdiomaTesis, g);
        g.gridy = 4; g.gridx = 2;
        JLabel lblAnioTesis = new JLabel("Año Publicación:");
        estiloLabel(lblAnioTesis);
        p.add(lblAnioTesis, g);
        g.gridx = 3;
        fechaAnioTesis = crearDateChooserAnio();
        p.add(fechaAnioTesis, g);
        return p;
    }

    private JPanel crearPanelDocumento() {
        JPanel p = new JPanel(new GridBagLayout()); p.setOpaque(false);
        GridBagConstraints g = iniciarGbc();
        txtAutorDoc      = addComp(p, "Autor:", 0, 0, g);
        txtEditorialDoc  = addComp(p, "Editorial:", 0, 1, g);
        txtNumPaginasDoc = addComp(p, "Número de Páginas:", 1, 0, g);
        txtTipoDocumento = addComp(p, "Tipo:", 1, 1, g);
        g.gridy = 2; g.gridx = 0;
        JLabel lblIdiomaDoc = new JLabel("Idioma:");
        estiloLabel(lblIdiomaDoc);
        p.add(lblIdiomaDoc, g);
        g.gridx = 1;
        cmbIdiomaDoc = new JComboBox<>(new String[]{"Español", "Inglés", "Francés", "Portugués"});
        cmbIdiomaDoc.setPreferredSize(new Dimension(136, 22));
        p.add(cmbIdiomaDoc, g);
        g.gridy = 2; g.gridx = 2;
        JLabel lblAnioDoc = new JLabel("Año:");
        estiloLabel(lblAnioDoc);
        p.add(lblAnioDoc, g);
        g.gridx = 3;
        fechaAnioDoc = crearDateChooserAnio();
        p.add(fechaAnioDoc, g);
        return p;
    }

    private JPanel crearPanelCD() {
        JPanel p = new JPanel(new GridBagLayout()); p.setOpaque(false);
        GridBagConstraints g = iniciarGbc();
        txtArtista  = addComp(p, "Artista:", 0, 0, g);
        txtDisquera = addComp(p, "Disquera:", 0, 1, g);
        g.gridy = 1; g.gridx = 0;
        JLabel lblGeneroCD = new JLabel("Género:");
        estiloLabel(lblGeneroCD);
        p.add(lblGeneroCD, g);
        g.gridx = 1;
        cmbGeneroCD = new JComboBox<>(new String[]{"Rock", "Pop", "Clásica", "Jazz", "Reggaeton", "Gospel", "Otro"});
        cmbGeneroCD.setPreferredSize(new Dimension(136, 22));
        p.add(cmbGeneroCD, g);
        g.gridy = 1; g.gridx = 2;
        JLabel lblIdiomaCD = new JLabel("Idioma:");
        estiloLabel(lblIdiomaCD);
        p.add(lblIdiomaCD, g);
        g.gridx = 3;
        cmbIdiomaCD = new JComboBox<>(new String[]{"Español", "Inglés", "Francés", "Portugués"});
        cmbIdiomaCD.setPreferredSize(new Dimension(136, 22));
        p.add(cmbIdiomaCD, g);
        txtCanciones = addComp(p, "Canciones:", 2, 0, g);
        g.gridy = 2; g.gridx = 2;
        JLabel lblAnioCD = new JLabel("Año:");
        estiloLabel(lblAnioCD);
        p.add(lblAnioCD, g);
        g.gridx = 3;
        fechaAnioCD = crearDateChooserAnio();
        p.add(fechaAnioCD, g);
        txtDuracion = addComp(p, "Duración (min):", 3, 0, g);
        return p;
    }

    private JTextField agregarCampo(String texto, int fila, JPanel panel, GridBagConstraints g) {
        g.gridy = fila; g.gridx = 0; g.weightx = 0; g.anchor = GridBagConstraints.EAST;
        JLabel lbl = new JLabel(texto); estiloLabel(lbl);
        panel.add(lbl, g);
        g.gridx = 1; g.weightx = 1.0;
        JTextField txt = new JTextField(20);
        panel.add(txt, g);
        return txt;
    }

    private void estiloLabel(JLabel l) {
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void estiloBoton(JButton b) {
        b.setPreferredSize(new Dimension(180, 45));
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(Color.WHITE);
    }

    private GridBagConstraints iniciarGbc() {
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 10, 5, 10);
        g.anchor = GridBagConstraints.WEST;
        return g;
    }

    private JTextField addComp(JPanel p, String n, int fila, int col, GridBagConstraints g) {
        g.gridy = fila; g.gridx = col * 2;
        JLabel l = new JLabel(n); estiloLabel(l);
        p.add(l, g);
        g.gridx = (col * 2) + 1;
        JTextField t = new JTextField(12);
        p.add(t, g);
        return t;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MaterialFrame().setVisible(true));
    }
}