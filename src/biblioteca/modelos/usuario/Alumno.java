package biblioteca.modelos.usuario;

public class Alumno extends Usuario{

    private String carnet;
    private String carrera;
    private String departamento;
    private String fechaIngreso;
    private String turno;
    private String fechaNacimiento;
    private String direccion;
    private boolean tieneMora;
    private boolean activo;
    private double montoMora;
    private int cantidadLibrosPrestados;
    private int maxLibrosPrestados;
    private int anioEstudio;

    public Alumno(String nombre, String apellido, String dui, String username, String password, String correo, String telefono, String tipoUsuario, String carnet, String carrera, String departamento, String fechaIngreso, String turno, String fechaNacimiento, String direccion, boolean tieneMora, boolean activo, double montoMora, int cantidadLibrosPrestados, int maxLibrosPrestados, int anioEstudio) {
        super(nombre, apellido, dui, username, password, correo, telefono, tipoUsuario);
        this.carnet = carnet;
        this.carrera = carrera;
        this.departamento = departamento;
        this.fechaIngreso = fechaIngreso;
        this.turno = turno;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.tieneMora = tieneMora;
        this.activo = activo;
        this.montoMora = montoMora;
        this.cantidadLibrosPrestados = cantidadLibrosPrestados;
        this.maxLibrosPrestados = maxLibrosPrestados;
        this.anioEstudio = anioEstudio;
    }

    public String getCarnet() {
        return carnet;
    }
    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public boolean isTieneMora() {
        return tieneMora;
    }
    public void setTieneMora(boolean tieneMora) {
        this.tieneMora = tieneMora;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public double getMontoMora() {
        return montoMora;
    }
    public void setMontoMora(double montoMora) {
        this.montoMora = montoMora;
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
    public int getAnioEstudio() {
        return anioEstudio;
    }
    public void setAnioEstudio(int anioEstudio) {
        this.anioEstudio = anioEstudio;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Alumno{" +
                "carnet='" + carnet + '\'' +
                ", carrera='" + carrera + '\'' +
                ", departamento='" + departamento + '\'' +
                ", fechaIngreso='" + fechaIngreso + '\'' +
                ", turno='" + turno + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", direccion='" + direccion + '\'' +
                ", tieneMora=" + tieneMora +
                ", activo=" + activo +
                ", montoMora=" + montoMora +
                ", cantidadLibrosPrestados=" + cantidadLibrosPrestados +
                ", maxLibrosPrestados=" + maxLibrosPrestados +
                ", anioEstudio=" + anioEstudio +
                '}';
    }
}
