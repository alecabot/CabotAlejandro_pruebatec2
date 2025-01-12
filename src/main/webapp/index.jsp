<%@page import="com.mycompany.cabotalejandro_pruebatech2.persistencia.ControladoraPersistencia"%>
<%@page import="com.mycompany.cabotalejandro_pruebatech2.logica.Turno"%>
<%@page import="com.mycompany.cabotalejandro_pruebatech2.logica.Ciudadano"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Crear una instancia de ControladoraPersistencia
    ControladoraPersistencia controladoraPersi = new ControladoraPersistencia();

    // Establecer la lista de ciudadanos en la sesión
    session.setAttribute("ciudadanos", controladoraPersi.traerCiudadanos());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestionturnos</title>
        <!-- Agregar estilos de Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">        
    </head>
    <body>

        <div class="container mt-4">
            <h2>Formulario Ciudadanos</h2>
            <form action="SvCiudadanos" method="POST">
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" class="form-control" id="nombre_ciudadano" name="nombre_ciudadano">
                    <div class="text-danger">
                        ${requestScope.errores.nombre != null ? requestScope.errores.nombre : ""}
                    </div>
                </div>
                <div class="form-group">
                    <label for="nombre">Apellido:</label>
                    <input type="text" class="form-control" id="apellido_ciudadano" name="apellido_ciudadano">
                    <div class="text-danger">
                        ${requestScope.errores.apellido != null ? requestScope.errores.apellido : ""}
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Guardar</button>

            </form>
            <div class="text-danger">
                ${requestScope.errores.nombreDuplicado != null ? requestScope.errores.nombreDuplicado : ""}
            </div>
            <c:if test="${not empty sessionScope.crear_ciudadano}">
                <br>
                <div class="alert alert-success" role="alert">
                    ${sessionScope.crear_ciudadano}
                </div>
                <c:remove var="crear_ciudadano" scope="session"/>
            </c:if>

            <br>
            <form action="SvCiudadanos" method="GET">    
                <button type="submit" class="btn btn-primary">Ver ciudadanos</button>
            </form>             
            <br>  
            <!-- Resultados en tabla CIUDADANOS -->
            <div class="results-table">
                <% if (request.getAttribute("ciudadanos") != null) { %>
                <h3>Ciudadanos registrados:</h3>
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Ciudadano ciudadano : (List<Ciudadano>) request.getAttribute("ciudadanos")) {%>
                        <tr>
                            <td><%= ciudadano.getId()%></td>
                            <td><%= ciudadano.getNombre()%></td>
                            <td><%= ciudadano.getApellido()%></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <% } %>
            </div>      

            <hr>
            <h2>Formulario Turnos</h2>
            <form action="SvTurnos" method="POST">
                <div class="form-group">
                    <label for="fecha">Fecha:</label>
                    <input type="date" class="form-control" id="fecha_turno" name="fecha_turno">
                    <div class="text-danger">
                        ${requestScope.errores.fecha != null ? requestScope.errores.fecha : ""}
                    </div>
                </div>     
                <div class="form-group">
                    <label for="numero">Numero:</label>
                    <input type="text" class="form-control" id="numero_turno" name="numero_turno">
                    <div class="text-danger">
                        ${requestScope.errores.numero != null ? requestScope.errores.numero : ""}
                    </div>
                </div>                
                <div class="form-group">
                    <label for="descripcion">Descripcion:</label>
                    <input type="text" class="form-control" id="descripcion_turno" name="descripcion_turno">
                    <div class="text-danger">
                        ${requestScope.errores.descripcion != null ? requestScope.errores.descripcion : ""}
                    </div>
                </div>                
                <div class="form-group">
                    <label for="estado">Estado:</label>
                    <select class="form-control" id="estado_turno" name="estado_turno">
                        <option value="">Seleccione una opcion</option>
                        <option value="EN_ESPERA">en espera</option>
                        <option value="YA_ATENDIDO">ya atendido</option>
                    </select>
                    <div class="text-danger">
                        ${requestScope.errores.estado != null ? requestScope.errores.estado : ""}
                    </div>
                </div>

                <div class="form-group">
                    <label for="estado">Ciudadano</label>
                    <select class="form-control" id="ciudadano_turno" name="ciudadano_turno">
                        <option value="">Seleccione una opcion</option>  
                        <% for (Ciudadano ciudadano : (List<Ciudadano>) session.getAttribute("ciudadanos")) {%>
                        <option value="<%= ciudadano.getId()%>"><%= ciudadano.getNombre() + " " + ciudadano.getApellido()%></option>
                        <% } %>
                    </select>
                    <div class="text-danger">
                        ${requestScope.errores.ciudadano != null ? requestScope.errores.ciudadano : ""}
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Guardar</button>
            </form>
            <c:if test="${not empty sessionScope.crear_turno}">
                <br>
                <div class="alert alert-success" role="alert">
                    ${sessionScope.crear_turno}
                </div>
                <c:remove var="crear_turno" scope="session"/>
            </c:if>
            <hr>
            <form action="SvTurnos" method="GET" class="form-inline">
                <div class="form-group">
                    <input type="date" class="form-control" placeholder="Fecha" name="fecha_filtro">
                    <div class="text-danger">
                        ${requestScope.errores.fechaFiltro != null ? requestScope.errores.fechaFiltro : ""}
                    </div>
                </div>
                <div class="form-group">
                    <select class="form-control" id="estado_turno" name="estado_filtro">
                        <option value="">Seleccione una opcion</option>
                        <option value="EN_ESPERA">en espera</option>
                        <option value="YA_ATENDIDO">ya atendido</option>
                    </select>
                    <div class="text-danger">
                        ${requestScope.errores.estadoFiltro != null ? requestScope.errores.estadoFiltro : ""}
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Ver turnos</button>
            </form>            

            <br>
            <!-- Resultados en tabla TURNOS -->
            <div class="results-table">
                <% if (request.getAttribute("turnos") != null) { %>
                <h3>Turnos registrados:</h3>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Numero</th>
                            <th>Descripcion</th>
                            <th>Estado</th>
                            <th>Nombre Ciudadano</th> 
                            <th>Apellido Ciudadano</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Turno turno : (List<Turno>) request.getAttribute("turnos")) {%>
                        <tr>
                            <td><%= turno.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) %></td>
                            <td><%= turno.getNumero()%></td>
                            <td><%= turno.getDescripcion()%></td>   
                            <td><%= turno.getEstado()%></td>
                            <td><%= turno.getCiudadano().getNombre()%></td>
                            <td><%= turno.getCiudadano().getApellido()%></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <% }%>
            </div>                

        </div>        
    </body>
</html>