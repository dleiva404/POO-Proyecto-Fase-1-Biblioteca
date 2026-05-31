package biblioteca.modulos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Factory centralizado para obtener loggers específicos de cada módulo.
 * Facilita el acceso a loggers en toda la aplicación.
 */
public class LoggerFactory {

    private static final Logger prestamoLogger = LogManager.getLogger(PrestamoManager.class);
    private static final Logger devolucionLogger = LogManager.getLogger(DevolucionManager.class);
    private static final Logger moraLogger = LogManager.getLogger(MoraManager.class);
    private static final Logger usuarioLogger = LogManager.getLogger(UsuarioManager.class);
    private static final Logger validadorLogger = LogManager.getLogger(ValidadorPrestamo.class);

    /**
     * Obtiene el logger para operaciones de préstamo
     */
    public static Logger getPrestamoLogger() {
        return prestamoLogger;
    }

    /**
     * Obtiene el logger para operaciones de devolución
     */
    public static Logger getDevolucionLogger() {
        return devolucionLogger;
    }

    /**
     * Obtiene el logger para cálculo de moras
     */
    public static Logger getMoraLogger() {
        return moraLogger;
    }

    /**
     * Obtiene el logger para gestión de usuarios
     */
    public static Logger getUsuarioLogger() {
        return usuarioLogger;
    }

    /**
     * Obtiene el logger para validaciones de préstamo
     */
    public static Logger getValidadorLogger() {
        return validadorLogger;
    }
}
