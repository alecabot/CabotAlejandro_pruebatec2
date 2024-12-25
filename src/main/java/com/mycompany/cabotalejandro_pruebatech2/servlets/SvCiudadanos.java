package com.mycompany.cabotalejandro_pruebatech2.servlets;

import com.mycompany.cabotalejandro_pruebatech2.logica.Ciudadano;
import com.mycompany.cabotalejandro_pruebatech2.persistencia.ControladoraPersistencia;
import java.io.IOException;
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
 * Servlet que maneja las solicitudes relacionadas con los ciudadanos.
 * Se encarga de obtener, crear y validar ciudadanos.
 */
@WebServlet(name = "SvCiudadanos", urlPatterns = {"/SvCiudadanos"})
public class SvCiudadanos extends HttpServlet {

    // Controladora de persistencia para manejar la lógica de negocio
    ControladoraPersistencia controlPersi = new ControladoraPersistencia();

    /**
     * Maneja las solicitudes GET.
     * Obtiene la lista de ciudadanos y la establece en la solicitud para mostrarla en el JSP.
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Se obtienen los ciudadanos desde la BD
        List<Ciudadano> listCiudadanos = controlPersi.traerCiudadanos();
        
        // Establecer los resultados en la solicitud para que se muestren en el JSP
        request.setAttribute("ciudadanos", listCiudadanos);
        
        // Redirigir de vuelta al formulario
        request.getRequestDispatcher("index.jsp").forward(request, response); 
    }

    /**
     * Maneja las solicitudes POST.
     * Crea un nuevo ciudadano después de validar los campos.
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el nombre del ciudadano desde la solicitud
        String nombre = request.getParameter("nombre_ciudadano").trim();
        Ciudadano ciudadano = new Ciudadano(nombre);
        
        // Validar los campos del formulario
        Map<String, String> errores = comprobarCampos(nombre);
        
        // Si hay errores, establecerlos en la solicitud y redirigir al JSP
        if (!errores.isEmpty()) {
            request.setAttribute("errores", errores);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        
        // Crear una nueva sesión y establecer un atributo de éxito
        HttpSession session = request.getSession();
        session.setAttribute("crear_ciudadano", "ciudadano creado correctamente");
        
        // Crear el ciudadano en la base de datos
        controlPersi.crearCiudadano(ciudadano);
        
        // Redirigir al JSP
        response.sendRedirect("index.jsp");
    }
    
    /**
     * Comprueba los campos del formulario.
     * @param nombre El nombre del ciudadano.
     * @return Un mapa con los errores encontrados.
     */
    public Map<String, String> comprobarCampos(String nombre) {
        Map<String, String> errores = new HashMap<>();
        // Validar que el nombre no sea nulo y contenga solo caracteres
        if (!nombre.matches("^[A-Za-z\\s]+$")) {
            errores.put("nombre", "El nombre no debe ser nulo y debe contener solo caracteres");
        }
        // Comprobar si el nombre ya existe
        if (comprobarNombreDuplicado(nombre)) {
            errores.put("nombre", "Ese nombre ya existe");
        }
        return errores;
    }
    
    /**
     * Comprueba si el nombre del ciudadano ya existe en la base de datos.
     * @param nombre El nombre del ciudadano.
     * @return true si el nombre ya existe, false en caso contrario.
     */
    public Boolean comprobarNombreDuplicado(String nombre){
        return controlPersi.traerCiudadanos().stream()
                .anyMatch(persona ->
                        persona.getNombre().equalsIgnoreCase(nombre)
                );
    }
    
    /**
     * Actualiza la sesión con la lista de ciudadanos.
     * @param request La solicitud HTTP.
     */
    public void actualizarSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("ciudadanos", controlPersi.traerCiudadanos());
    }

}