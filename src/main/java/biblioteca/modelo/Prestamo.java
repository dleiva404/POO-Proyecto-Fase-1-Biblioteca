package biblioteca.modelo;

public class Prestamo {
    private int id_prestamo;
    private int id_usuario;
    private int id_material;
    private String fecha_prestamo;
    private String fecha_devolucion;
    private String estado;
    private double mora;
    private String titulo;
    private String nombre;
    private String apellido;

    public Prestamo(int id_prestamo, int id_usuario, int id_material, String fecha_prestamo, String fecha_devolucion, String estado, double mora) {
        this.id_prestamo = id_prestamo;
        this.id_usuario = id_usuario;
        this.id_material = id_material;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.estado = estado;
        this.mora = mora;
    }

    public Prestamo() {
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public String getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(String fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getIdPrestamo() {
        return id_prestamo;
    }

    public int getIdMaterial() {
        return id_material;
    }

    public String getFechaPrestamo() {
        return fecha_prestamo;
    }

    public String getFechaDevolucion() {
        return fecha_devolucion;
    }
}