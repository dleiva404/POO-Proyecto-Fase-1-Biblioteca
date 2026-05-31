package biblioteca.modulos;

import biblioteca.dao.PrestamoDAO;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Manager de préstamos.
 * Gestiona la lógica completa de creación de préstamos.
 */
public class PrestamoManager {

    private static final Logger logger = LoggerFactory.getPrestamoLogger();

    private static final PrestamoDAO prestamoDAO = new PrestamoDAO();

    // Duración de préstamos en días
    private static final int DIAS_PRESTAMO_ALUMNO = 7;
    private static final int DIAS_PRESTAMO_PROFESOR = 14;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * CREA PRÉSTAMO
     * API ORIGINAL MANTENIDA
     */
    public static boolean crearPrestamo(int idUsuario, int idMaterial, String tipoUsuario) {

        try {

            String codigoPrestamo = generarCodigoPrestamo();

            LocalDate hoy = LocalDate.now();

            int diasPrestamo = getDiasPrestamo(tipoUsuario);

            LocalDate fechaDevolucion = hoy.plusDays(diasPrestamo);

            String fechaPrestamo = hoy.format(formatter);

            String fechaDevolucionStr = fechaDevolucion.format(formatter);

            // VALIDAR LIMITE
            int prestamosActivos = prestamoDAO.contarPrestamosActivos(idUsuario);

            int limite = tipoUsuario.equalsIgnoreCase("PROFESOR")
                    ? 5
                    : 3;

            if (prestamosActivos >= limite) {

                logger.warn("❌ Límite de préstamos alcanzado");

                return false;
            }

            if (prestamoDAO.tieneMaterialPrestado(idUsuario, idMaterial)) {

                logger.warn("❌ Material ya prestado");

                return false;
            }

            // GUARDAR EN BD
            prestamoDAO.insertarPrestamo(
                    idUsuario,
                    idMaterial,
                    fechaPrestamo,
                    fechaDevolucionStr,
                    "PRESTADO"
            );

            logger.info("NUEVO PRÉSTAMO CREADO");
            logger.info("  Código: {}", codigoPrestamo);
            logger.info("  ID Usuario: {}", idUsuario);
            logger.info("  ID Material: {}", idMaterial);
            logger.info("  Tipo Usuario: {}", tipoUsuario);
            logger.info("  Fecha Préstamo: {}", fechaPrestamo);
            logger.info("  Fecha Devolución: {}", fechaDevolucionStr);

            return true;

        } catch (Exception e) {

            logger.error("Error al crear préstamo: {}", e.getMessage());

            return false;
        }
    }

    /**
     * GENERAR CÓDIGO
     */
    public static String generarCodigoPrestamo() {

        String uuid = UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        return "PREST-" + uuid;
    }

    /**
     * OBTENER DÍAS PRÉSTAMO
     */
    public static int getDiasPrestamo(String tipoUsuario) {

        if (tipoUsuario.equalsIgnoreCase("ALUMNO")) {

            return DIAS_PRESTAMO_ALUMNO;

        } else if (tipoUsuario.equalsIgnoreCase("PROFESOR")) {

            return DIAS_PRESTAMO_PROFESOR;
        }

        return 0;
    }

    /**
     * CALCULAR FECHA DEVOLUCIÓN
     */
    public static String calcularFechaDevolucion(String tipoUsuario) {

        LocalDate hoy = LocalDate.now();

        int dias = getDiasPrestamo(tipoUsuario);

        return hoy.plusDays(dias).format(formatter);
    }

    /**
     * FECHA HOY
     */
    public static String getFechaHoy() {

        return LocalDate.now().format(formatter);
    }
}