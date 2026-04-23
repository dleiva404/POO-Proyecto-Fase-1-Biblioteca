package biblioteca.modelos.material;

public abstract class Material {

    private String codigoIdentificacion;
    private String titulo;
    private String categoria;
    private String ubicacionFisica;
    private int cantidadTotal;
    private int cantidadDisponible;
    private int cantidadPrestada;

    public Material(String codigoIdentificacion, String titulo, String categoria, String ubicacionFisica, int cantidadTotal) {
        this.codigoIdentificacion = codigoIdentificacion;
        this.titulo = titulo;
        this.categoria = categoria;
        this.ubicacionFisica = ubicacionFisica;
        this.cantidadTotal = cantidadTotal;
        this.cantidadDisponible = cantidadTotal;
        this.cantidadPrestada = 0;
    }
    public String getCodigoIdentificacion() {
        return codigoIdentificacion;
    }
    public void setCodigoIdentificacion(String codigoIdentificacion) {
        this.codigoIdentificacion = codigoIdentificacion;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getUbicacionFisica() {
        return ubicacionFisica;
    }
    public void setUbicacionFisica(String ubicacionFisica) {
        this.ubicacionFisica = ubicacionFisica;
    }
    public int getCantidadTotal() {
        return cantidadTotal;
    }
    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    public int getCantidadPrestada() {
        return cantidadPrestada;
    }
    public void setCantidadPrestada(int cantidadPrestada) {
        this.cantidadPrestada = cantidadPrestada;
    }

    @Override
    public String toString() {
        return "Material{" +
                "codigoIdentificacion='" + codigoIdentificacion + '\'' +
                ", titulo='" + titulo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", ubicacionFisica='" + ubicacionFisica + '\'' +
                ", cantidadTotal=" + cantidadTotal +
                ", cantidadDisponible=" + cantidadDisponible +
                ", cantidadPrestada=" + cantidadPrestada +
                '}';
    }
}
