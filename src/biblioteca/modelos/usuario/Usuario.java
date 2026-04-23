package biblioteca.modelos.usuario;

public abstract class Usuario {

    private String nombre;
    private String apellido;
    private String dui;
    private String username;
    private String password;
    private String correo;
    private String telefono;
    private String tipoUsuario;

    public Usuario(String nombre, String apellido, String dui, String username, String password, String correo, String telefono, String tipoUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
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
    public String getDui() {
        return dui;
    }
    public void setDui(String dui) {
        this.dui = dui;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dui='" + dui + '\'' +
                ", username='" + username + '\'' +
                ", password='********'" +  // Oculto por seguridad
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                '}';
    }
}
