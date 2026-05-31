package biblioteca.modelos.material;

public class Tesis extends MaterialEscrito {

    private String autor;
    private String carrera;
    private String tema;
    private String asesor;
    private String universidad;
    private String grado;
    private String idioma;
    private int anioPublicacion;

    public Tesis(String codigoIdentificacion, String titulo, String categoria, String ubicacionFisica, int cantidadTotal, int numeroPaginas, String editorial, String autor, String carrera, String tema, String asesor, String universidad, String grado, String idioma, int anioPublicacion) {
        super(codigoIdentificacion, titulo, categoria, ubicacionFisica, cantidadTotal, numeroPaginas, editorial);
        this.autor = autor;
        this.carrera = carrera;
        this.tema = tema;
        this.asesor = asesor;
        this.universidad = universidad;
        this.grado = grado;
        this.idioma = idioma;
        this.anioPublicacion = anioPublicacion;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getTema() {
        return tema;
    }
    public void setTema(String tema) {
        this.tema = tema;
    }
    public String getAsesor() {
        return asesor;
    }
    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }
    public String getUniversidad() {
        return universidad;
    }
    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }
    public String getGrado() {
        return grado;
    }
    public void setGrado(String grado) {
        this.grado = grado;
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
                " Tesis{" +
                "autor='" + autor + '\'' +
                ", carrera='" + carrera + '\'' +
                ", tema='" + tema + '\'' +
                ", asesor='" + asesor + '\'' +
                ", universidad='" + universidad + '\'' +
                ", grado='" + grado + '\'' +
                ", idioma='" + idioma + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                '}';
    }
}
