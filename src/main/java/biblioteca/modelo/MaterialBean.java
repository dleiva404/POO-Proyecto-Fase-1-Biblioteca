package biblioteca.modelo;

import java.io.Serializable;

/**
 * Java Bean para Material
 * Utilizado para pasar datos entre Servlets y JSP
 */
public class MaterialBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private String tipo; // LIBRO, REVISTA, CD, DOCUMENTO, TESIS, OBRA
    private String ubicacion;
    private int cantidadTotal;
    private int cantidadDisponible;
    private String autor;
    private String anioPublicacion;
    private String editorial;

    // Constructores
    public MaterialBean() {
    }

    public MaterialBean(int id, String titulo, String tipo, int cantidadDisponible) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.cantidadDisponible = cantidadDisponible;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(String anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "MaterialBean{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}
