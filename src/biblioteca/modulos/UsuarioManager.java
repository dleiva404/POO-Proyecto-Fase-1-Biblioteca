package biblioteca.modulos;

import biblioteca.dao.UsuarioDAO;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * Manager de usuarios.
 * Gestiona validación, login y operaciones de usuarios.
 */
public class UsuarioManager {

    private static final Logger logger = LoggerFactory.getUsuarioLogger();

    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Requisitos mínimos
    private static final int MIN_LONGITUD_PASSWORD = 4;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
    );

    /**
     * VALIDAR DATOS USUARIO
     */
    public static boolean validarDatosUsuario(String nombre, String email, String password) {

        try {

            if (nombre == null || nombre.trim().isEmpty()) {

                logger.warn("❌ Validación fallida - Nombre vacío");
                return false;
            }

            if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {

                logger.warn("❌ Validación fallida - Email inválido: {}", email);
                return false;
            }

            if (password == null || password.length() < MIN_LONGITUD_PASSWORD) {

                logger.warn("❌ Validación fallida - Contraseña muy corta");
                return false;
            }

            logger.info("✅ Validación exitosa - Usuario: {}", nombre);

            return true;

        } catch (Exception e) {

            logger.error("Error durante validación: {}", e.getMessage());
            return false;
        }
    }

    /**
     * REGISTRAR NUEVO USUARIO
     * API ORIGINAL MANTENIDA
     */
    public static void registrarNuevoUsuario(String nombre, String tipoUsuario) {

        try {

            usuarioDAO.insertarUsuario(
                    nombre,
                    "",
                    "",
                    "",
                    "",
                    "",
                    tipoUsuario,
                    "1234"
            );

            logger.info("NUEVO USUARIO REGISTRADO");
            logger.info("  Nombre: {}", nombre);
            logger.info("  Tipo: {}", tipoUsuario);

        } catch (Exception e) {

            logger.error("Error al registrar usuario: {}", e.getMessage());
        }
    }

    /**
     * REGISTRAR LOGIN
     */
    public static void registrarIntentLogin(String nombreUsuario, boolean exitoso) {

        if (exitoso) {

            logger.info("✅ LOGIN EXITOSO - Usuario: {}", nombreUsuario);

        } else {

            logger.warn("❌ LOGIN FALLIDO - Usuario: {}", nombreUsuario);
        }
    }

    /**
     * REGISTRAR CAMBIO PASSWORD
     */
    public static void registrarCambioContrasena(String nombreUsuario) {

        logger.info("CAMBIO DE CONTRASEÑA - Usuario: {}", nombreUsuario);
    }

    /**
     * REGISTRAR RESTABLECIMIENTO PASSWORD
     */
    public static void registrarRestablecimientoContrasena(String nombreUsuario) {

        logger.info("RESTABLECIMIENTO DE CONTRASEÑA - Usuario: {}", nombreUsuario);
    }

    /**
     * VALIDAR PASSWORD
     */
    public static boolean validarFortalezaPassword(String password) {

        if (password == null || password.length() < MIN_LONGITUD_PASSWORD) {

            logger.warn("❌ Contraseña muy corta");
            return false;
        }

        logger.info("✅ Contraseña válida");

        return true;
    }

    /**
     * VALIDAR EMAIL
     */
    public static boolean validarEmail(String email) {

        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {

            logger.warn("❌ Email inválido: {}", email);
            return false;
        }

        logger.info("✅ Email válido: {}", email);

        return true;
    }
}