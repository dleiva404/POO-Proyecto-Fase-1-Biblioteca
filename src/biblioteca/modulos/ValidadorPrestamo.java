package biblioteca.modulos;

import biblioteca.modelos.usuario.Alumno;
import biblioteca.modelos.usuario.Profesor;
import org.apache.logging.log4j.Logger;

/**
 * Validador de préstamos.
 * Valida si un usuario puede hacer un préstamo según las reglas de negocio.
 */
public class ValidadorPrestamo {

    private static final Logger logger = LoggerFactory.getValidadorLogger();
    
    // Límites de libros por tipo de usuario
    private static final int MAX_LIBROS_ALUMNO = 3;
    private static final int MAX_LIBROS_PROFESOR = 5;

    /**
     * Valida si un usuario puede hacer un préstamo.
     * 
     * Reglas:
     * - No puede prestar si tiene mora
     * - Alumnos: máximo 3 libros
     * - Profesores: máximo 5 libros
     * 
     * @param usuario Objeto del usuario (Alumno o Profesor)
     * @param cantidadLibrosAPrestar Cantidad de libros que desea prestar
     * @return true si puede hacer el préstamo, false si no
     */
    public static boolean puedeHacerPrestamo(Object usuario, int cantidadLibrosAPrestar) {
        
        if (usuario instanceof Alumno) {
            Alumno alumno = (Alumno) usuario;
            
            // Verificar si tiene mora
            if (alumno.isTieneMora()) {
                logger.warn("❌ PRÉSTAMO RECHAZADO - Alumno {} tiene mora", alumno.getNombre());
                return false;
            }
            
            // Verificar límite de libros
            int librosActuales = alumno.getCantidadLibrosPrestados();
            if (librosActuales + cantidadLibrosAPrestar > MAX_LIBROS_ALUMNO) {
                logger.warn("❌ PRÉSTAMO RECHAZADO - Alumno {} excede límite de {}. Actuales: {}, Intenta: {}", 
                    alumno.getNombre(), MAX_LIBROS_ALUMNO, librosActuales, cantidadLibrosAPrestar);
                return false;
            }
            
            logger.info("✅ PRÉSTAMO PERMITIDO - Alumno {} (Libros: {}/{})", 
                alumno.getNombre(), librosActuales + cantidadLibrosAPrestar, MAX_LIBROS_ALUMNO);
            return true;
            
        } else if (usuario instanceof Profesor) {
            Profesor profesor = (Profesor) usuario;
            
            // Verificar si tiene mora
           if (profesor.isTieneMora()) {
              logger.warn("❌ PRÉSTAMO RECHAZADO - Profesor {} tiene mora", profesor.getNombre());
                return false;
            }
            
            // Verificar límite de libros
            int librosActuales = profesor.getCantidadLibrosPrestados();
            if (librosActuales + cantidadLibrosAPrestar > MAX_LIBROS_PROFESOR) {
                logger.warn("❌ PRÉSTAMO RECHAZADO - Profesor {} excede límite de {}. Actuales: {}, Intenta: {}", 
                    profesor.getNombre(), MAX_LIBROS_PROFESOR, librosActuales, cantidadLibrosAPrestar);
                return false;
            }
            
            logger.info("✅ PRÉSTAMO PERMITIDO - Profesor {} (Libros: {}/{})", 
                profesor.getNombre(), librosActuales + cantidadLibrosAPrestar, MAX_LIBROS_PROFESOR);
            return true;
        }
        
        logger.error("❌ Tipo de usuario inválido");
        return false;
    }

    /**
     * Obtiene el límite máximo de libros para un alumno
     */
    public static int getMaxLibrosAlumno() {
        return MAX_LIBROS_ALUMNO;
    }

    /**
     * Obtiene el límite máximo de libros para un profesor
     */
    public static int getMaxLibrosProfesor() {
        return MAX_LIBROS_PROFESOR;
    }
}
