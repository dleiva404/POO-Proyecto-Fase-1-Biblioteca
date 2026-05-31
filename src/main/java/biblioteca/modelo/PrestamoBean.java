package biblioteca.modelo;

import java.io.Serializable;

/**
 * Java Bean para Prestamo
 * Utilizado para pasar datos entre Servlets y JSP
 */
public class PrestamoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int idUsuario;
    private int idMaterial;
    private String codigoPrestamo;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private String fechaDevolucionReal;
    private String estado; // PRESTADO, DEVUELTO
    private double mora;
    private String nombreUsuario;
    private String nombreMaterial;

    // Constructores
    public PrestamoBean() {
    }

    public PrestamoBean(int id, int idUsuario, int idMaterial, String codigoPrestamo, 
                       String fechaPrestamo, String fechaDevolucion, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idMaterial = idMaterial;
        this.codigoPrestamo = codigoPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMora() {
        return mora;
    }

    public void setMora(double mora) {
        this.mora = mora;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    @Override
    public String toString() {
        return "PrestamoBean{" +
                "id=" + id +
                ", codigoPrestamo='" + codigoPrestamo + '\'' +
                ", estado='" + estado + '\'' +
                ", mora=" + mora +
                '}';
    }
}
