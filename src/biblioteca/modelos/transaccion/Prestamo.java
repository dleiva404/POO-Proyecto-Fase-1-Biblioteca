package biblioteca.modelos.transaccion;

import biblioteca.modelos.material.Material;
import biblioteca.modelos.usuario.Usuario;

public class Prestamo {

    private String codigoPrestamo;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private String fechaDevolucionReal;
    private Usuario usuario;
    private Material material;
    private String estadoPrestamo;
    private double mora;

    public Prestamo(String codigoPrestamo, String fechaPrestamo, String fechaDevolucion, String fechaDevolucionReal, Usuario usuario, Material material, String estadoPrestamo, double mora) {
        this.codigoPrestamo = codigoPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.usuario = usuario;
        this.material = material;
        this.estadoPrestamo = estadoPrestamo;
        this.mora = mora;
    }

    public String getCodigoPrestamo() {
        return codigoPrestamo;
    }
    public void setCodigoPrestamo(String codigoPrestamo) {
        this.codigoPrestamo = codigoPrestamo;
    }
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }
    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    public String getFechaDevolucion() {
        return fechaDevolucion;
    }
    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    public String getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }
    public void setFechaDevolucionReal(String fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }
    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }
    public double getMora() {
        return mora;
    }
    public void setMora(double mora) {
        this.mora = mora;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "codigoPrestamo='" + codigoPrestamo + '\'' +
                ", fechaPrestamo='" + fechaPrestamo + '\'' +
                ", fechaDevolucion='" + fechaDevolucion + '\'' +
                ", fechaDevolucionReal='" + fechaDevolucionReal + '\'' +
                ", usuario=" + usuario +
                ", material=" + material +
                ", estadoPrestamo='" + estadoPrestamo + '\'' +
                ", mora=" + mora +
                '}';
    }
}
