package biblioteca.modelos.usuario;

public class Profesor extends Usuario{

    private String especialidad;
    private String carrera;
    private String titulacion;
    private String departamento;
    private String codigoEmpleado;
    private String fechaContratacion;
    private boolean activo;
    private int cantidadLibrosPrestados;
    private int maxLibrosPrestados;

    public Profesor(String nombre, String apellido, String dui, String username, String password, String correo, String telefono, String tipoUsuario, String especialidad, String carrera, String titulacion, String departamento, String codigoEmpleado, String fechaContratacion, boolean activo, int cantidadLibrosPrestados, int maxLibrosPrestados) {
        super(nombre, apellido, dui, username, password, correo, telefono, tipoUsuario);
        this.especialidad = especialidad;
        this.carrera = carrera;
        this.titulacion = titulacion;
        this.departamento = departamento;
        this.codigoEmpleado = codigoEmpleado;
        this.fechaContratacion = fechaContratacion;
        this.activo = activo;
        this.cantidadLibrosPrestados = cantidadLibrosPrestados;
        this.maxLibrosPrestados = maxLibrosPrestados;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getTitulacion() {
        return titulacion;
    }
    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }
    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }
    public String getFechaContratacion() {
        return fechaContratacion;
    }
    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public int getCantidadLibrosPrestados() {
        return cantidadLibrosPrestados;
    }
    public void setCantidadLibrosPrestados(int cantidadLibrosPrestados) {
        this.cantidadLibrosPrestados = cantidadLibrosPrestados;
    }
    public int getMaxLibrosPrestados() {
        return maxLibrosPrestados;
    }
    public void setMaxLibrosPrestados(int maxLibrosPrestados) {
        this.maxLibrosPrestados = maxLibrosPrestados;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Profesor{" +
                "especialidad='" + especialidad + '\'' +
                ", carrera='" + carrera + '\'' +
                ", titulacion='" + titulacion + '\'' +
                ", departamento='" + departamento + '\'' +
                ", codigoEmpleado='" + codigoEmpleado + '\'' +
                ", fechaContratacion='" + fechaContratacion + '\'' +
                ", activo=" + activo +
                ", cantidadLibrosPrestados=" + cantidadLibrosPrestados +
                ", maxLibrosPrestados=" + maxLibrosPrestados +
                '}';
    }
}
