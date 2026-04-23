package biblioteca.modelos.material;

public class Documento extends MaterialEscrito{

    private String autor;
    private String tipoDocumento;
    private String idioma;
    private int anioPublicacion;

    public Documento(String codigoIdentificacion, String titulo, String categoria, String ubicacionFisica, int cantidadTotal, int numeroPaginas, String editorial, String autor, String tipoDocumento, String idioma, int anioPublicacion) {
        super(codigoIdentificacion, titulo, categoria, ubicacionFisica, cantidadTotal, numeroPaginas, editorial);
        this.autor = autor;
        this.tipoDocumento = tipoDocumento;
        this.idioma = idioma;
        this.anioPublicacion = anioPublicacion;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
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
                " Documento{" +
                "autor='" + autor + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", idioma='" + idioma + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                '}';
    }
}
