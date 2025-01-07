/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.cabotalejandro_pruebatech2.servlets;

import com.mycompany.cabotalejandro_pruebatech2.logica.Ciudadano;
import com.mycompany.cabotalejandro_pruebatech2.logica.EstadoTurno;
import com.mycompany.cabotalejandro_pruebatech2.logica.Turno;
import com.mycompany.cabotalejandro_pruebatech2.persistencia.ControladoraPersistencia;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet que maneja las solicitudes relacionadas con los turnos.
 * Se encarga de obtener, crear y validar turnos.
 */
@WebServlet(name = "SvTurnos", urlPatterns = {"/SvTurnos"})
public class SvTurnos extends HttpServlet {
    // Formateador de fechas
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // Controladora de persistencia para manejar la lógica de negocio
    ControladoraPersistencia controlPersi = new ControladoraPersistencia();
    
    /**
     * Maneja las solicitudes GET.
     * Filtra los turnos por fecha y estado y los establece en la solicitud para mostrar en el JSP.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros de fecha y estado desde la solicitud
        String fecha = request.getParameter("fecha_filtro").trim();
        String estado = request.getParameter("estado_filtro");
        
        // Validar los campos de filtro
        
        Map<String, String> errores = comprobarCamposFiltros(fecha, estado);
        
        // Si hay errores, establecerlos en la solicitud y redirigir al JSP
        if (!errores.isEmpty()) {
            request.setAttribute("errores", errores);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        
        // Filtrar los turnos por fecha y estado
        List<Turno> listTurnos = controlPersi.FiltrarPorfechaEstado(
                LocalDate.parse(fecha, formatter),
                EstadoTurno.valueOf(estado)
        );
        
        // Establecer los resultados en la solicitud para que se muestren en el JSP
        request.setAttribute("turnos", listTurnos);
        
        // Redirigir de vuelta al formulario
        request.getRequestDispatcher("index.jsp").forward(request, response); 
    }

    /**
     * Maneja las solicitudes POST.
     * Crea un nuevo turno después de validar los campos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del turno desde la solicitud
        String numero = request.getParameter("numero_turno").trim();
        String fecha = request.getParameter("fecha_turno").trim();
        String descripcion = request.getParameter("descripcion_turno").trim();
        String estado = request.getParameter("estado_turno");
        String idCiudadano = request.getParameter("ciudadano_turno");
        
        // Validar los campos del formulario
        Map<String, String> errores = comprobarCampos(numero, fecha, descripcion, estado, idCiudadano);
        
        // Si hay errores, establecerlos en la solicitud y redirigir al JSP
        if (!errores.isEmpty()) {
            request.setAttribute("errores", errores);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        
        // Crear un nuevo turno y establecer sus atributos
        Turno turno = new Turno();
        turno.setNumero(Integer.valueOf(numero));
        turno.setFecha(LocalDate.parse(fecha, formatter));
        turno.setDescripcion(descripcion);
        turno.setEstado(EstadoTurno.valueOf(estado));
        
        // Crear una nueva sesión y establecer un atributo de éxito
        HttpSession session = request.getSession();
        session.setAttribute("crear_turno", "turno creado correctamente");
        
        // Crear el turno en la base de datos
        controlPersi.crearTurno(turno, Long.valueOf(idCiudadano));
        
        // Redirigir al JSP
        response.sendRedirect("index.jsp");
    }

    /**
     * Comprueba los campos del formulario.
     * @param numero El número del turno.
     * @param fecha La fecha del turno.
     * @param descripcion La descripción del turno.
     * @param estado El estado del turno.
     * @param idCiudadano El ID del ciudadano.
     * @return Un mapa con los errores encontrados.
     */
    public Map<String, String> comprobarCampos(String numero, String fecha, String descripcion, String estado, String idCiudadano) {
        Map<String, String> errores = new HashMap<>();
        // Validar que el número no sea nulo y contenga solo números
        if (!numero.matches("^[0-9]+$")) {
            errores.put("numero", "El numero no debe ser nulo y debe contener solo numeros");
        }
        // Validar que la fecha sea válida
        if (!esFechaValida(fecha)) {
            errores.put("fecha", "La fecha no es valida");
        }
        // Validar que el estado sea válido
        if (!EstadoTurno.contiene(estado)) {
            errores.put("estado", "estado no valido");
        }
        // Validar que la descripción no sea nula
        if (descripcion.isEmpty()) {
            errores.put("descripcion", "La descripcion no debe ser nulo");
        }
        // Validar que el ID del ciudadano sea válido
        if (!comprobarId(idCiudadano) || idCiudadano.isEmpty()) {
            errores.put("ciudadano", "ciudadano no valido");
        }
        return errores;
    }

    /**
     * Comprueba los campos de los filtros.
     * @param fecha La fecha del filtro.
     * @param estado El estado del filtro.
     * @return Un mapa con los errores encontrados.
     */
    public Map<String, String> comprobarCamposFiltros(String fecha, String estado) {
        Map<String, String> errores = new HashMap<>();
        // Validar que la fecha sea válida
        if (!esFechaValida(fecha)) {
            errores.put("fechaFiltro", "La fecha no es valida");
        }
        System.out.println(estado);
        // Validar que el estado sea válido
        if (!estado.isEmpty() && !EstadoTurno.contiene(estado)) {
            errores.put("estadoFiltro", "estado no valido");
        }
        return errores;
    }

    /**
     * Comprueba si el ID del ciudadano es válido.
     * @param idCiudadano El ID del ciudadano.
     * @return true si el ID es válido, false en caso contrario.
     */
    public Boolean comprobarId(String idCiudadano){
        return controlPersi.traerCiudadanos().stream()
                .anyMatch(ciudadano -> ciudadano.getId().toString().equals(idCiudadano));
    }

    /**
     * Comprueba si la fecha es válida.
     * @param fecha La fecha a validar.
     * @return true si la fecha es válida, false en caso contrario.
     */
    public boolean esFechaValida(String fecha) {
        try {
            LocalDate.parse(fecha, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}