package biblioteca.modelo;

public class Usuario {
    private int id_usuario;
    private String nombre;
    private String apellido;
    private String carnet;
    private String dui;
    private String telefono;
    private String correo;
    private String tipo_usuario;
    private String password;

    public Usuario(String password, String tipo_usuario, String correo, String telefono, String dui, String carnet, String apellido, String nombre, int id_usuario) {
        this.password = password;
        this.tipo_usuario = tipo_usuario;
        this.correo = correo;
        this.telefono = telefono;
        this.dui = dui;
        this.carnet = carnet;
        this.apellido = apellido;
        this.nombre = nombre;
        this.id_usuario = id_usuario;
    }

    public Usuario() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public String getTipo_Usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUsuario() {
        return id_usuario;
    }

    public String getTipoUsuario() {
        return tipo_usuario;
    }
}