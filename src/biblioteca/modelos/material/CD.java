package biblioteca.modelos.material;

public class CD extends MaterialAudiovisual{

    private String artista;
    private String disquera;
    private String genero;
    private String idioma;
    private int cantidadCanciones;
    private int anioLanzamiento;

    public CD(String codigoIdentificacion, String titulo, String categoria, String ubicacionFisica, int cantidadTotal, int duracion, String artista, String disquera, String genero, String idioma, int cantidadCanciones, int anioLanzamiento) {
        super(codigoIdentificacion, titulo, categoria, ubicacionFisica, cantidadTotal, duracion);
        this.artista = artista;
        this.disquera = disquera;
        this.genero = genero;
        this.idioma = idioma;
        this.cantidadCanciones = cantidadCanciones;
        this.anioLanzamiento = anioLanzamiento;
    }

    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public String getDisquera() {
        return disquera;
    }
    public void setDisquera(String disquera) {
        this.disquera = disquera;
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
    public int getCantidadCanciones() {
        return cantidadCanciones;
    }
    public void setCantidadCanciones(int cantidadCanciones) {
        this.cantidadCanciones = cantidadCanciones;
    }
    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }
    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    @Override
    public String toString() {
        return super.toString() +
                " CD{" +
                "artista='" + artista + '\'' +
                ", disquera='" + disquera + '\'' +
                ", genero='" + genero + '\'' +
                ", idioma='" + idioma + '\'' +
                ", cantidadCanciones=" + cantidadCanciones +
                ", anioLanzamiento=" + anioLanzamiento +
                '}';
    }
}
