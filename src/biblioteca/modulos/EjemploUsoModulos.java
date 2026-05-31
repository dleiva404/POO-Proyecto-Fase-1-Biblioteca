package biblioteca.modulos;

import biblioteca.modelos.usuario.Alumno;
import biblioteca.modelos.usuario.Profesor;

public class EjemploUsoModulos {

    public static void main(String[] args) {
        System.out.println("=== PRUEBA INTEGRAL DE MÓDULOS BIBLIOTECA ===\n");

        // 1. Datos de prueba
        int idPrestamoEjemplo = 1;
        int idMaterialEjemplo = 5;

        // 2. MÓDULO: VALIDACIÓN DE USUARIO (Ejemplo)
        System.out.println("--- Validando Datos de Usuario ---");
        boolean esValido = UsuarioManager.validarDatosUsuario("Carlos Cornejo", "carlos@correo.com", "pass123");
        System.out.println("Usuario válido: " + esValido);

        // 3. MÓDULO 10: PROCESAR DEVOLUCIÓN
        System.out.println("\n1️⃣0️⃣ MÓDULO 10: PROCESAR DEVOLUCIÓN");
        DevolucionManager.procesarDevolucion(idPrestamoEjemplo, idMaterialEjemplo, "Carlos Cornejo", "2026-05-10");

        // 4. MÓDULO 11: PROCESAR DEVOLUCIÓN CON MORA
        System.out.println("\n1️⃣1️⃣ MÓDULO 11: PROCESAR DEVOLUCIÓN CON MORA");
        double moraDevolucion = DevolucionManager.procesarDevolucionConMora(
                2,
                10,
                "Ana Martínez",
                "Libro de Java",
                "2026-04-25"
        );

        System.out.println("Resultado: Mora a pagar: $" + String.format("%.2f", moraDevolucion));

        System.out.println("\n=== FIN DE LAS PRUEBAS ===");
    }
}