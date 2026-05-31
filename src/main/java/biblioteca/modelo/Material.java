package biblioteca.modelo;


public class Material {
    private int id_material;
    private String codigo;
    private String titulo;
    private String categoria;
    private String ubicacion;
    private int cantidad_total;
    private int cantidad_disponible;
    private String tipo_material;

    public Material(int id_material, String codigo, String titulo, String categoria, String ubicacion, int cantidad_total, int cantidad_disponible, String tipo_material) {
        this.id_material = id_material;
        this.codigo = codigo;
        this.titulo = titulo;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.cantidad_total = cantidad_total;
        this.cantidad_disponible = cantidad_disponible;
        this.tipo_material = tipo_material;
    }

    public Material() {
    }

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCantidad_total() {
        return cantidad_total;
    }

    public void setCantidad_total(int cantidad_total) {
        this.cantidad_total = cantidad_total;
    }

    public int getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setCantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    public String getTipo_material() {
        return tipo_material;
    }

    public void setTipo_material(String tipo_material) {
        this.tipo_material = tipo_material;
    }
}
