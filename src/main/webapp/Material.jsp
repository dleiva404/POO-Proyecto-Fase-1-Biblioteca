<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty usuario or (usuario.tipo_usuario != 'PROFESOR' and usuario.tipo_usuario != 'ADMINISTRADOR')}">
  <c:redirect url="Menu.jsp" />
</c:if>
<!doctype html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Registro de Materiales - Colegio Don Bosco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/styless.css" />
  </head>
  <body class="d-flex flex-column" style="min-height: 100vh">
    <nav class="navbar px-4 d-flex justify-content-between">
      <span class="fw-bold text-white">Biblioteca - Colegio Amigos de Don Bosco</span>
      <div>
        <span class="text-white me-3">Hola, ${usuario.nombre}</span>
        <a href="LogoutServlet" class="btn btn-logout btn-sm fw-bold">Cerrar Sesión</a>
      </div>
    </nav>

    <div class="container text-center" style="margin-top: 30px">
      <h2>Registrar Material</h2>
    </div>

    <div class="container d-flex justify-content-center pt-3 pb-5">
      <div class="row justify-content-center w-100">
        <div class="col-lg-9">
          <div class="marco">
            <form action="MaterialServlet" method="post">
              <div class="row align-items-center mb-3">
                <label class="col-sm-3 form-label lbl-der">Tipo de Material:</label>
                <div class="col-sm-9">
                  <select class="form-select" id="tipoMaterial" name="tipoMaterial">
                    <option value="LIBRO" selected>Libro</option>
                    <option value="REVISTA">Revista</option>
                    <option value="TESIS">Tesis</option>
                    <option value="DOCUMENTO">Documento</option>
                    <option value="CD">CD/DVD</option>
                  </select>
                </div>
              </div>

              <div class="row align-items-center mb-3">
                <label class="col-sm-3 form-label lbl-der">Código:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control readonly-campo" name="codigo" value="AUTOGENERADO" readonly />
                </div>
              </div>

              <div class="row align-items-center mb-3">
                <label class="col-sm-3 form-label lbl-der">Título:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" name="titulo" required />
                </div>
              </div>

              <div class="row align-items-center mb-3">
                <label class="col-sm-3 form-label lbl-der">Categoría:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" name="categoria" />
                </div>
              </div>

              <div class="row align-items-center mb-3">
                <label class="col-sm-3 form-label lbl-der">Ubicación:</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" name="ubicacion" />
                </div>
              </div>

              <div class="row align-items-center mb-3">
                <label class="col-sm-3 form-label lbl-der">Cantidad:</label>
                <div class="col-sm-9">
                  <input type="number" class="form-control" name="cantidad" min="1" />
                </div>
              </div>

              <!-- LIBRO -->
              <fieldset class="detalles-box detalle-tipo mt-4 mb-2" id="detalle-LIBRO">
                <legend class="detalles-titulo">Detalles Específicos</legend>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Autor:</label>
                    <div class="col-7">
                      <input class="form-control" name="autor" list="listaAutores" />
                      <datalist id="listaAutores">
                        <c:forEach var="a" items="${autores}">
                          <option value="${a}"></option>
                        </c:forEach>
                      </datalist>
                    </div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Editorial:</label>
                    <div class="col-7">
                      <input class="form-control" name="editorial" list="listaEditoriales" />
                      <datalist id="listaEditoriales">
                        <c:forEach var="e" items="${editoriales}">
                          <option value="${e}"></option>
                        </c:forEach>
                      </datalist>
                    </div>
                  </div></div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Número de Páginas:</label>
                    <div class="col-7"><input type="number" class="form-control" name="numPaginas" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">ISBN:</label>
                    <div class="col-7"><input type="text" class="form-control" name="isbn" /></div>
                  </div></div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Año de Publicación:</label>
                    <div class="col-7"><input type="number" class="form-control" name="anioPublicacion" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Edición:</label>
                    <div class="col-7"><input type="text" class="form-control" name="edicion" /></div>
                  </div></div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Tipo Pasta:</label>
                    <div class="col-7">
                      <select class="form-select" name="tipoPasta"><option>Dura</option><option>Blanda</option></select>
                    </div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Idioma:</label>
                    <div class="col-7">
                      <select class="form-select" name="idioma"><option>Español</option><option>Inglés</option><option>Francés</option></select>
                    </div>
                  </div></div>
                </div>
                <div class="row mb-1">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Género:</label>
                    <div class="col-7">
                      <select class="form-select" name="genero">
                        <option>Educativo</option><option>Ficción</option><option>Ciencia Ficción</option><option>Historia</option><option>Tecnología</option>
                      </select>
                    </div>
                  </div></div>
                  <div class="col-md-6"></div>
                </div>
              </fieldset>

              <!-- REVISTA -->
              <fieldset class="detalles-box detalle-tipo mt-4 mb-2" id="detalle-REVISTA">
                <legend class="detalles-titulo">Detalles Específicos</legend>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Editorial:</label>
                    <div class="col-7"><input type="text" class="form-control" name="editorial" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Número de Páginas:</label>
                    <div class="col-7"><input type="number" class="form-control" name="numPaginas" /></div>
                  </div></div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">ISSN:</label>
                    <div class="col-7"><input type="text" class="form-control" name="issn" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Periodicidad:</label>
                    <div class="col-7"><input type="text" class="form-control" name="periodicidad" /></div>
                  </div></div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Idioma:</label>
                    <div class="col-7">
                      <select class="form-select" name="idioma"><option>Español</option><option>Inglés</option><option>Francés</option><option>Portugués</option></select>
                    </div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Fecha Publicación:</label>
                    <div class="col-7"><input type="text" class="form-control" name="fechaPublicacion" /></div>
                  </div></div>
                </div>
                <div class="row mb-1">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">N° Edición:</label>
                    <div class="col-7"><input type="number" class="form-control" name="numEdicion" /></div>
                  </div></div>
                  <div class="col-md-6"></div>
                </div>
              </fieldset>

              <!-- TESIS -->
              <fieldset class="detalles-box detalle-tipo mt-4 mb-2" id="detalle-TESIS">
                <legend class="detalles-titulo">Detalles Específicos</legend>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Autor:</label>
                    <div class="col-7"><input type="text" class="form-control" name="autor" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Editorial:</label>
                    <div class="col-7"><input type="text" class="form-control" name="editorial" /></div>
                  </div></div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Número de Páginas:</label>
                    <div class="col-7"><input type="number" class="form-control" name="numPaginas" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Carrera:</label>
                    <div class="col-7"><input type="text" class="form-control" name="carrera" /></div>
                  </div></div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Tema:</label>
                    <div class="col-7"><input type="text" class="form-control" name="tema" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Asesor:</label>
                    <div class="col-7"><input type="text" class="form-control" name="asesor" /></div>
                  </div></div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Universidad:</label>
                    <div class="col-7"><input type="text" class="form-control" name="universidad" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Grado:</label>
                    <div class="col-7">
                      <select class="form-select" name="grado"><option>Licenciatura</option><option>Ingeniería</option><option>Maestría</option><option>Doctorado</option><option>Técnico</option></select>
                    </div>
                  </div></div>
                </div>
                <div class="row mb-1">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Idioma:</label>
                    <div class="col-7">
                      <select class="form-select" name="idioma"><option>Español</option><option>Inglés</option><option>Francés</option><option>Portugués</option></select>
                    </div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Año Publicación:</label>
                    <div class="col-7"><input type="number" class="form-control" name="anioPublicacion" /></div>
                  </div></div>
                </div>
              </fieldset>

              <!-- DOCUMENTO -->
              <fieldset class="detalles-box detalle-tipo mt-4 mb-2" id="detalle-DOCUMENTO">
                <legend class="detalles-titulo">Detalles Específicos</legend>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Autor:</label>
                    <div class="col-7"><input type="text" class="form-control" name="autor" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Editorial:</label>
                    <div class="col-7"><input type="text" class="form-control" name="editorial" /></div>
                  </div></div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Número de Páginas:</label>
                    <div class="col-7"><input type="number" class="form-control" name="numPaginas" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Tipo:</label>
                    <div class="col-7"><input type="text" class="form-control" name="tipoDocumento" /></div>
                  </div></div>
                </div>
                <div class="row mb-1">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Idioma:</label>
                    <div class="col-7">
                      <select class="form-select" name="idioma"><option>Español</option><option>Inglés</option><option>Francés</option><option>Portugués</option></select>
                    </div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Año:</label>
                    <div class="col-7"><input type="number" class="form-control" name="anio" /></div>
                  </div></div>
                </div>
              </fieldset>

              <!-- CD/DVD -->
              <fieldset class="detalles-box detalle-tipo mt-4 mb-2" id="detalle-CD">
                <legend class="detalles-titulo">Detalles Específicos</legend>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Artista:</label>
                    <div class="col-7"><input type="text" class="form-control" name="artista" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Disquera:</label>
                    <div class="col-7"><input type="text" class="form-control" name="disquera" /></div>
                  </div></div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Género:</label>
                    <div class="col-7">
                      <select class="form-select" name="genero"><option>Rock</option><option>Pop</option><option>Clásica</option><option>Jazz</option><option>Reggaeton</option><option>Gospel</option><option>Otro</option></select>
                    </div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Idioma:</label>
                    <div class="col-7">
                      <select class="form-select" name="idioma"><option>Español</option><option>Inglés</option><option>Francés</option><option>Portugués</option></select>
                    </div>
                  </div></div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Canciones:</label>
                    <div class="col-7"><input type="number" class="form-control" name="canciones" /></div>
                  </div></div>
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Año:</label>
                    <div class="col-7"><input type="number" class="form-control" name="anio" /></div>
                  </div></div>
                </div>
                <div class="row mb-1">
                  <div class="col-md-6"><div class="row align-items-center">
                    <label class="col-5 form-label lbl-der">Duración (min):</label>
                    <div class="col-7"><input type="number" class="form-control" name="duracion" /></div>
                  </div></div>
                  <div class="col-md-6"></div>
                </div>
              </fieldset>

              <div class="d-flex justify-content-center gap-4 mt-4">
                <button type="submit" class="btn btn-guardar">Guardar Registro</button>
                <a href="Menu.jsp" class="btn btn-volver text-decoration-none">Volver al Menú</a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    <script>
      const selectTipo = document.getElementById("tipoMaterial");
      function mostrarDetalle() {
        document.querySelectorAll(".detalle-tipo").forEach(function (bloque) {
          bloque.style.display = "none";
          bloque.querySelectorAll("input, select").forEach((c) => (c.disabled = true));
        });
        const activo = document.getElementById("detalle-" + selectTipo.value);
        if (activo) {
          activo.style.display = "block";
          activo.querySelectorAll("input, select").forEach((c) => (c.disabled = false));
        }
      }
      selectTipo.addEventListener("change", mostrarDetalle);
      mostrarDetalle();
    </script>
  </body>
</html>