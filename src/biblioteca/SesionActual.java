package biblioteca;

public class SesionActual {

    private static String rol = null;
    private static int idUsuario = -1;
    private static String nombre = "";

    public static String getRol() {
        return rol;
    }

    public static void setRol(String rol) {
        SesionActual.rol = rol;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int idUsuario) {
        SesionActual.idUsuario = idUsuario;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        SesionActual.nombre = nombre;
    }
}