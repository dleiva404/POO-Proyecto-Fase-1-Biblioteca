package biblioteca.util;

import java.util.regex.Pattern;

/**
 * Utilidades para validaciones comunes
 */
public class ValidationUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@(.+)$"
    );
    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^[0-9]{4}-[0-9]{4}$"
    );

    /**
     * Valida formato de email
     */
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Valida formato de teléfono
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * Valida que la contraseña cumpla requisitos
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 4) {
            return false;
        }
        return true;
    }

    /**
     * Valida que no esté vacío
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Escapa caracteres especiales para evitar SQL injection
     */
    public static String escapeSQL(String input) {
        if (input == null) return null;
        return input.replace("'", "''")
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"");
    }
}
