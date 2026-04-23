package biblioteca.modelos.transaccion;

import biblioteca.modelos.usuario.Usuario;
public class Devolucion {

    private String codigoDevolucion;
    private String fechaDevolucion;
    private String estadoDevolucion;
    private String observaciones;
    private Usuario usuario;
    private Prestamo prestamo;
    private double mora;

    public Devolucion(String codigoDevolucion, String fechaDevolucion, String estadoDevolucion, String observaciones, Usuario usuario, Prestamo prestamo, double mora) {
        this.codigoDevolucion = codigoDevolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
        this.observaciones = observaciones;
        this.usuario = usuario;
        this.prestamo = prestamo;
        this.mora = mora;
    }

    public String getCodigoDevolucion() {
        return codigoDevolucion;
    }
    public void setCodigoDevolucion(String codigoDevolucion) {
        this.codigoDevolucion = codigoDevolucion;
    }
    public String getFechaDevolucion() {
        return fechaDevolucion;
    }
    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    public String getEstadoDevolucion() {
        return estadoDevolucion;
    }
    public void setEstadoDevolucion(String estadoDevolucion) {
        this.estadoDevolucion = estadoDevolucion;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Prestamo getPrestamo() {
        return prestamo;
    }
    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
    public double getMora() {
        return mora;
    }
    public void setMora(double mora) {
        this.mora = mora;
    }

    @Override
    public String toString() {
        return "Devolucion{" +
                "codigoDevolucion='" + codigoDevolucion + '\'' +
                ", fechaDevolucion='" + fechaDevolucion + '\'' +
                ", estadoDevolucion='" + estadoDevolucion + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", usuario=" + usuario +
                ", prestamo=" + prestamo +
                ", mora=" + mora +
                '}';
    }
}
