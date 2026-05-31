# MÓDULOS FUNCIONALES + LOG4J - Guía de Implementación

## 📋 Contenido de este Trabajo

He creado un sistema completo de **módulos funcionales** con **logging integrado** usando Log4J 2.x. Aquí está todo lo que necesitas:

---

## 🔧 Paso 1: Configuración de Dependencias (Ya hecho)

El archivo `pom.xml` ya incluye:
```xml
<!-- Log4J 2.x -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.23.1</version>
</dependency>

<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.23.1</version>
</dependency>
```

---

## 📁 Archivos Creados

### 1. **LoggerFactory.java** 🎯
**Ubicación:** `src/biblioteca/modulos/LoggerFactory.java`

**Qué hace:** Factory centralizada para obtener loggers específicos de cada módulo.

**Métodos disponibles:**
```java
LoggerFactory.getPrestamoLogger()    // Logger para préstamos
LoggerFactory.getDevolucionLogger()  // Logger para devoluciones
LoggerFactory.getMoraLogger()        // Logger para moras
LoggerFactory.getUsuarioLogger()     // Logger para usuarios
```

---

### 2. **ValidadorPrestamo.java** ✅
**Ubicación:** `src/biblioteca/modulos/ValidadorPrestamo.java`

**Funcionalidad:** Valida si un usuario puede hacer un préstamo.

**Reglas implementadas:**
- ✗ No puede prestar si tiene mora
- ✗ Los ALUMNOS pueden prestar máximo 3 libros
- ✗ Los PROFESORES pueden prestar máximo 5 libros

**Uso:**
```java
Object alumno = new Alumno(...);
if (ValidadorPrestamo.puedeHacerPrestamo(alumno, 2)) {
    logger.info("Puede hacer el préstamo");
} else {
    logger.error("No puede hacer el préstamo");
}
```

---

### 3. **PrestamoManager.java** 📚
**Ubicación:** `src/biblioteca/modulos/PrestamoManager.java`

**Funcionalidad:** Gestiona la lógica completa de préstamos.

**Características:**
- Crea un código único para cada préstamo (PREST-XXXXX)
- Calcula automáticamente la fecha de devolución:
  - Alumnos: 7 días
  - Profesores: 14 días
- Registra todo en logs

**Uso:**
```java
boolean exito = PrestamoManager.crearPrestamo(
    1,              // ID usuario
    5,              // ID material
    "ALUMNO"        // Tipo de usuario
);

int dias = PrestamoManager.getDiasPrestamo("ALUMNO"); // Devuelve 7
```

---

### 4. **MoraManager.java** 💰
**Ubicación:** `src/biblioteca/modulos/MoraManager.java`

**Funcionalidad:** Calcula multas/moras automáticamente.

**Reglas:**
- Q0.50 por día de atraso
- Calcula automáticamente los días entre hoy y la fecha de vencimiento

**Uso:**
```java
double mora = MoraManager.calcularMora("2026-04-25"); // Calcula automáticamente
MoraManager.registrarMora("Juan García", 15.00, 30);  // Registra en logs

double tasa = MoraManager.getTasaMoraDiaria(); // Devuelve 0.50
```

---

### 5. **DevolucionManager.java** ↩️
**Ubicación:** `src/biblioteca/modulos/DevolucionManager.java`

**Funcionalidad:** Gestiona las devoluciones de materiales.

**Características:**
- Procesa devoluciones
- Valida que el material devuelto sea correcto
- Registra todo en logs

**Uso:**
```java
boolean exito = DevolucionManager.procesarDevolucion(
    1,                    // ID del préstamo
    "Juan García",        // Nombre del usuario
    "Libro Java"          // Nombre del material
);

// Validar que sea el material correcto
if (DevolucionManager.validarMaterialDevuelto(1, 1)) {
    logger.info("Material correcto");
}
```

---

### 6. **UsuarioManager.java** 👥
**Ubicación:** `src/biblioteca/modulos/UsuarioManager.java`

**Funcionalidad:** Gestiona usuarios: validación, login, cambio de contraseñas.

**Características:**
- Valida email format automáticamente
- Valida que la contraseña tenga mínimo 4 caracteres
- Registra intentos de login
- Registra cambios de contraseña

**Uso:**
```java
// Validar datos antes de crear usuario
if (UsuarioManager.validarDatosUsuario("Juan", "juan@correo.com", "pass123")) {
    logger.info("Datos válidos");
}

// Registrar logins
UsuarioManager.registrarIntentLogin("juan.garcia", true);  // Login exitoso
UsuarioManager.registrarIntentLogin("juan.garcia", false); // Login fallido

// Registrar cambio de contraseña
UsuarioManager.registrarRestablecimientoContrasena("juan.garcia");
UsuarioManager.registrarCambioContrasena("juan.garcia");
```

---

### 7. **log4j2.xml** ⚙️
**Ubicación:** `src/main/resources/log4j2.xml`

**Qué hace:** Configuración central de Log4J.

**Características:**
- 📄 Log general: `logs/biblioteca.log`
- ❌ Log de errores: `logs/errores.log`
- 📚 Log de préstamos: `logs/prestamos.log`
- ↩️ Log de devoluciones: `logs/devoluciones.log`
- 💰 Log de moras: `logs/moras.log`
- 👥 Log de usuarios: `logs/usuarios.log`
- 🖥️ Consola en tiempo real

**Formato:** `[fecha hora] [thread] [nivel] [logger] - mensaje`

---

### 8. **EjemploUsoModulos.java** 📖
**Ubicación:** `src/biblioteca/modulos/EjemploUsoModulos.java`

Archivo demostrativo que muestra cómo usar TODOS los módulos juntos.

---

## 🚀 Cómo Usar

### Paso 1: Importar en tus clases
```java
import biblioteca.modulos.*;
import org.apache.logging.log4j.Logger;
```

### Paso 2: Usar los managers
```java
// En LoginFrame.java
public void validarLogin() {
    if (UsuarioManager.validarDatosUsuario(usuario, correo, password)) {
        UsuarioManager.registrarIntentLogin(usuario, true);
    }
}

// En PrestamoFrame.java
public void crearPrestamo() {
    if (ValidadorPrestamo.puedeHacerPrestamo(usuarioSeleccionado, 1)) {
        PrestamoManager.crearPrestamo(usuarioId, materialId, tipoUsuario);
    }
}

// En DevolucionFrame.java
public void devolverMaterial() {
    double mora = MoraManager.calcularMora(fechaVencimiento);
    if (mora > 0) {
        MoraManager.registrarMora(nombreUsuario, mora, diasAtraso);
    }
    DevolucionManager.procesarDevolucion(idPrestamo, usuario, material);
}
```

---

## 📊 Estructura de Logs

Los logs se guardan en carpeta `logs/` en la raíz del proyecto:

```
logs/
├── biblioteca.log        (General)
├── errores.log          (Solo errores)
├── prestamos.log        (Operaciones de préstamo)
├── devoluciones.log     (Operaciones de devolución)
├── moras.log            (Cálculo y registro de moras)
└── usuarios.log         (Gestión de usuarios)
```

**Ejemplo de contenido:**
```
2026-05-07 14:30:25 [main] INFO  biblioteca.modulos.PrestamoManager - NUEVO PRÉSTAMO - Código: PREST-A1B2C3D4 | Usuario: 1 | Material: 5 | Fecha devolución: 2026-05-14

2026-05-07 14:30:26 [main] INFO  biblioteca.modulos.MoraManager - Mora calculada: Q15.00 (30 días de atraso)

2026-05-07 14:30:27 [main] WARN  biblioteca.modulos.UsuarioManager - LOGIN FALLIDO - Usuario: juan.garcia
```

---

## ✨ Características Principales

| Módulo | Función | Log Level |
|--------|---------|-----------|
| **ValidadorPrestamo** | Valida reglas de préstamo | WARN (si falla) |
| **PrestamoManager** | Crea préstamos | INFO |
| **MoraManager** | Calcula moras | INFO/WARN |
| **DevolucionManager** | Procesa devoluciones | INFO |
| **UsuarioManager** | Gestiona usuarios | INFO/WARN |

---

## 🔍 Niveles de Log Usados

- 🔵 **INFO**: Operaciones exitosas
- 🟡 **WARN**: Advertencias (usuario con mora, datos inválidos)
- 🔴 **ERROR**: Errores críticos

---

## 📝 Tareas Pendientes (Para tus compañeros)

1. **Persona 1 (POO):** Ya listos los modelos, falta validar con los managers
2. **Persona 2 (BD):** Integrar los métodos DAO con los managers
3. **Persona 3 (Swing):** Llamar a los managers desde los frames
4. **Tú (Módulos):** ✅ COMPLETADO

---

## 💡 Ejemplo Completo de Integración

```java
// En tu LoginFrame.java
private void btnLoginActionPerformed(ActionEvent evt) {
    String usuario = txtUsuario.getText();
    String password = txtPassword.getText();
    
    // Validar datos
    if (!UsuarioManager.validarDatosUsuario(usuario, usuario + "@colegio.com", password)) {
        JOptionPane.showMessageDialog(this, "Datos inválidos");
        UsuarioManager.registrarIntentLogin(usuario, false);
        return;
    }
    
    // Intentar login (con DAO)
    Usuario usuarioLogin = usuarioDAO.obtenerUsuario(usuario, password);
    if (usuarioLogin != null) {
        UsuarioManager.registrarIntentLogin(usuario, true);
        new MenuFrame().setVisible(true);
    } else {
        UsuarioManager.registrarIntentLogin(usuario, false);
    }
}
```

---

## 🎯 Resumen

✅ **Log4J completamente configurado**
✅ **5 Managers funcionales**
✅ **Validaciones de negocio implementadas**
✅ **Cálculo de moras automático**
✅ **Logging en todos los módulos**
✅ **Ejemplo de uso documentado**

¡Listo para integrar con el resto del proyecto! 🚀
