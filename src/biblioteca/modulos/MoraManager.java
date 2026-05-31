package biblioteca.modulos;

import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Manager de moras.
 * Gestiona el cálculo de multas por atraso en devoluciones.
 */
public class MoraManager {

    private static final Logger logger = LoggerFactory.getMoraLogger();
    
    // Tasa de mora: $0.25 por día de atraso
    private static double getTasa() {
        return new biblioteca.dao.ConfiguracionDAO().getMoraDiaria();
    }
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Calcula la mora basada en la fecha de vencimiento.
     * 
     * @param fechaVencimiento Fecha de vencimiento en formato yyyy-MM-dd
     * @return Monto de mora a pagar
     */
    public static double calcularMora(String fechaVencimiento) {
        try {
            LocalDate vencimiento = LocalDate.parse(fechaVencimiento, formatter);
            LocalDate hoy = LocalDate.now();
            
            // Si no ha vencido, no hay mora
            if (!hoy.isAfter(vencimiento)) {
                logger.info("✅ Sin mora - Fecha de vencimiento: {} (días restantes: {})", 
                    fechaVencimiento, ChronoUnit.DAYS.between(hoy, vencimiento));
                return 0.0;
            }
            
            // Calcular días de atraso
            long diasAtraso = ChronoUnit.DAYS.between(vencimiento, hoy);
            double mora = diasAtraso * getTasa();
            
            logger.warn("⚠️ MORA CALCULADA");
            logger.warn("  Fecha Vencimiento: {}", fechaVencimiento);
            logger.warn("  Días de Atraso: {}", diasAtraso);
            logger.warn("  Tasa Diaria: ${}", getTasa());
            logger.warn("  TOTAL MORA: ${}", String.format("%.2f", mora));
            
            return mora;
            
        } catch (Exception e) {
            logger.error("Error al calcular mora: {}", e.getMessage());
            return 0.0;
        }
    }

    /**
     * Calcula los días de atraso.
     * 
     * @param fechaVencimiento Fecha de vencimiento en formato yyyy-MM-dd
     * @return Número de días de atraso (0 si no hay atraso)
     */
    public static long calcularDiasAtraso(String fechaVencimiento) {
        try {
            LocalDate vencimiento = LocalDate.parse(fechaVencimiento, formatter);
            LocalDate hoy = LocalDate.now();
            
            if (!hoy.isAfter(vencimiento)) {
                return 0;
            }
            
            return ChronoUnit.DAYS.between(vencimiento, hoy);
            
        } catch (Exception e) {
            logger.error("Error al calcular días de atraso: {}", e.getMessage());
            return 0;
        }
    }

    /**
     * Registra una mora en el sistema.
     * 
     * @param nombreUsuario Nombre del usuario
     * @param montoMora Monto a pagar
     * @param diasAtraso Cantidad de días de atraso
     */
    public static void registrarMora(String nombreUsuario, double montoMora, long diasAtraso) {
        logger.warn("MORA REGISTRADA");
        logger.warn("  Usuario: {}", nombreUsuario);
        logger.warn("  Monto: ${}", String.format("%.2f", montoMora));
        logger.warn("  Días de Atraso: {}", diasAtraso);
    }

    /**
     * Registra un pago de mora.
     * 
     * @param nombreUsuario Nombre del usuario
     * @param monto Monto pagado
     */
    public static void registrarPagoMora(String nombreUsuario, double monto) {
        logger.info("PAGO DE MORA REGISTRADO");
        logger.info("  Usuario: {}", nombreUsuario);
        logger.info("  Monto Pagado: ${}", String.format("%.2f", monto));
    }

    /**
     * Obtiene la tasa de mora diaria.
     */
    public static double getTasaMoraDiaria() {
        return getTasa();
    }

    /**
     * Verifica si un usuario tiene mora vencida.
     * 
     * @param fechaVencimiento Fecha de vencimiento
     * @return true si hay mora
     */
    public static boolean tieneMora(String fechaVencimiento) {
        return calcularMora(fechaVencimiento) > 0;
    }
}
