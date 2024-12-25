/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cabotalejandro_pruebatech2.persistencia;

import com.mycompany.cabotalejandro_pruebatech2.logica.Ciudadano;
import com.mycompany.cabotalejandro_pruebatech2.logica.EstadoTurno;
import com.mycompany.cabotalejandro_pruebatech2.logica.Turno;
import com.mycompany.cabotalejandro_pruebatech2.persistencia.exceptions.NonexistentEntityException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleja
 */
public class ControladoraPersistencia {
    CiudadanoJpaController ciudadanoJPA = new CiudadanoJpaController();
    TurnoJpaController turnoJPA = new TurnoJpaController();
    
    /* PARA CIUDADANO */
    public void crearCiudadano(Ciudadano ciudadano) {
        ciudadanoJPA.create(ciudadano);
    }
    
    public void eliminarCiudadano(Long id) {
        try {
            ciudadanoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Ciudadano> traerCiudadanos() {
        return ciudadanoJPA.findCiudadanoEntities();
    }
    
    public void editarCiudadano(Ciudadano ciudadano) {
        try {
            ciudadanoJPA.edit(ciudadano);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public Ciudadano obtenerCiudadano(Long id) {
        return ciudadanoJPA.findCiudadano(id);
    }    
    
    /* PARA TURNO */
    public void crearTurno(Turno turno, long idCiudadano) {
        
        Ciudadano ciudadano = obtenerCiudadano(idCiudadano);
        turno.setCiudadano(ciudadano);
        
        turnoJPA.create(turno);
    }
    
    public void eliminarTurno(Long id) {
        try {
            turnoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Turno> traerTurnos() {
        return turnoJPA.findTurnoEntities();
    }
    
    public void editarTurno(Turno turno) {
        try {
            turnoJPA.edit(turno);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public List<Turno> FiltrarPorfechaEstado(LocalDate fecha, EstadoTurno estado) {
       return traerTurnos().stream()
               .filter(turno -> turno.getFecha().equals(fecha) )
               .filter(turno -> turno.getEstado() == estado)
               .toList();
    }
}
