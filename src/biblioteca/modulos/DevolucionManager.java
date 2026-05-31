package biblioteca.modulos;

import biblioteca.dao.PrestamoDAO;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DevolucionManager {

    private static final Logger logger = LoggerFactory.getDevolucionLogger();
    private static final PrestamoDAO prestamoDAO = new PrestamoDAO();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * PROCESAR DEVOLUCIÓN
     * Se agrega idMaterial para que coincida con la llamada del Frame y el DAO.
     */
    public static boolean procesarDevolucion(
            int idPrestamo,
            int idMaterial,
            String nombreUsuario,
            String nombreMaterial
    ) {
        try {
            LocalDate hoy = LocalDate.now();
            String fechaDevolucion = hoy.format(formatter);

            boolean exito = prestamoDAO.procesarDevolucion(idPrestamo, idMaterial, 0.0);

            if (exito) {
                logger.info("DEVOLUCIÓN PROCESADA");
                logger.info("  ID Préstamo: {}", idPrestamo);
                logger.info("  Usuario: {}", nombreUsuario);
                logger.info("  Material: {}", nombreMaterial);
                return true;
            }
            return false;

        } catch (Exception e) {
            logger.error("Error al procesar devolución: {}", e.getMessage());
            return false;
        }
    }

    public static double procesarDevolucionConMora(
            int idPrestamo,
            int idMaterial, // Agregado para consistencia
            String nombreUsuario,
            String nombreMaterial,
            String fechaVencimiento
    ) {
        try {
            double mora = MoraManager.calcularMora(fechaVencimiento);

            prestamoDAO.procesarDevolucion(idPrestamo, idMaterial, mora);

            logger.info("DEVOLUCIÓN CON MORA PROCESADA - Usuario: {}", nombreUsuario);
            return mora;

        } catch (Exception e) {
            logger.error("Error en mora: {}", e.getMessage());
            return 0.0;
        }
    }
}