package biblioteca.modelos.material;

public class Revista extends MaterialEscrito {

    private String idioma;
    private String fechaPublicacion;
    private String ISSN;
    private String periodicidad;
    private int numeroEdicion;

    public Revista(String codigoIdentificacion, String titulo, String categoria, String ubicacionFisica, int cantidadTotal, int numeroPaginas, String editorial, String idioma, String fechaPublicacion, String ISSN, String periodicidad, int numeroEdicion) {
        super(codigoIdentificacion, titulo, categoria, ubicacionFisica, cantidadTotal, numeroPaginas, editorial);
        this.idioma = idioma;
        this.fechaPublicacion = fechaPublicacion;
        this.ISSN = ISSN;
        this.periodicidad = periodicidad;
        this.numeroEdicion = numeroEdicion;
    }

    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }
    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    public String getISSN() {
        return ISSN;
    }
    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }
    public String getPeriodicidad() {
        return periodicidad;
    }
    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }
    public int getNumeroEdicion() {
        return numeroEdicion;
    }
    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Revista{" +
                "idioma='" + idioma + '\'' +
                ", fechaPublicacion='" + fechaPublicacion + '\'' +
                ", ISSN='" + ISSN + '\'' +
                ", periodicidad='" + periodicidad + '\'' +
                ", numeroEdicion=" + numeroEdicion +
                '}';
    }
}
