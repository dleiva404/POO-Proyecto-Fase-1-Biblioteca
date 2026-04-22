package biblioteca.modelos.material;

public abstract class Material {

    private String codigoIdentificacion;
    private String titulo;
    private String categoria;
    private String ubicacionFisica;
    private int cantidadTotal;
    private int cantidadDisponible;
    private int cantidadPrestada;

    public Material(String codigoIdentificacion, String titulo,
                    String categoria, String ubicacionFisica,
                    int cantidadTotal) {
        this.codigoIdentificacion = codigoIdentificacion;
        this.titulo = titulo;
        this.categoria = categoria;
        this.ubicacionFisica = ubicacionFisica;
        this.cantidadTotal = cantidadTotal;
        this.cantidadDisponible = cantidadTotal;
        this.cantidadPrestada = 0;
    }

}
