package biblioteca.modelos.material;

public class Obra  extends MaterialEscrito {

    private String autor;
    private String edicion;
    private String tipoPasta;
    private String genero;
    private String idioma;
    private String ISBN;
    private int anioPublicacion;

    public Obra(String codigoIdentificacion, String titulo, String categoria, String ubicacionFisica, int cantidadTotal, int numeroPaginas, String editorial, String autor, String edicion, String tipoPasta, String genero, String idioma, String ISBN, int anioPublicacion) {
        super(codigoIdentificacion, titulo, categoria, ubicacionFisica, cantidadTotal, numeroPaginas, editorial);
        this.autor = autor;
        this.edicion = edicion;
        this.tipoPasta = tipoPasta;
        this.genero = genero;
        this.idioma = idioma;
        this.ISBN = ISBN;
        this.anioPublicacion = anioPublicacion;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
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
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
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

    @Override
    public String toString() {
        return super.toString() +
                " Obra{" +
                "autor='" + autor + '\'' +
                ", edicion='" + edicion + '\'' +
                ", tipoPasta='" + tipoPasta + '\'' +
                ", genero='" + genero + '\'' +
                ", idioma='" + idioma + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                '}';
    }
}
