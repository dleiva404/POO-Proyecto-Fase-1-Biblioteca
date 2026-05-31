package biblioteca.modelos.usuario;

public class Administrador extends Usuario{

    private String nivelAcceso;
    private String fechaRegistro;
    private boolean activo;

    public Administrador(String nombre, String apellido, String dui, String username, String password, String correo, String telefono, String tipoUsuario, String nivelAcceso, String fechaRegistro, boolean activo) {
        super(nombre, apellido, dui, username, password, correo, telefono, tipoUsuario);
        this.nivelAcceso = nivelAcceso;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }
    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
    public String getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Administrador{" +
                "nivelAcceso='" + nivelAcceso + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                ", activo=" + activo +
                '}';
    }
}
