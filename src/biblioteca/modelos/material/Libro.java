package biblioteca.modelos.material;

public class Libro extends MaterialEscrito {

    private String autor;
    private String ISBN;
    private int anioPublicacion;
    private String edicion;
    private String tipoPasta;
    private String idioma;

    public Libro(String codigoIdentificacion, String titulo, String categoria, String ubicacionFisica, int cantidadTotal, int numeroPaginas, String editorial, String autor, String ISBN, int anioPublicacion, String edicion, String tipoPasta, String idioma) {
        super(codigoIdentificacion, titulo, categoria, ubicacionFisica, cantidadTotal, numeroPaginas, editorial);
        this.autor = autor;
        this.ISBN = ISBN;
        this.anioPublicacion = anioPublicacion;
        this.edicion = edicion;
        this.tipoPasta = tipoPasta;
        this.idioma = idioma;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public int getAnioPublicacion() {
        return anioPublicacion;
    }
    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
    public String getEdicion() {
        return edicion;
    }
    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }
    public String getTipoPasta() {
        return tipoPasta;
    }
    public void setTipoPasta(String tipoPasta) {
        this.tipoPasta = tipoPasta;
    }
    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Libro{" +
                "autor='" + autor + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                ", edicion='" + edicion + '\'' +
                ", tipoPasta='" + tipoPasta + '\'' +
                ", idioma='" + idioma + '\'' +
                '}';
    }
}
