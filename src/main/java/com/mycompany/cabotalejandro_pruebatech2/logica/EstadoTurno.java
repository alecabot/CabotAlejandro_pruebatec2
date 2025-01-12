/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cabotalejandro_pruebatech2.logica;

/**
 *
 * @author aleja
 */
public enum EstadoTurno {
    EN_ESPERA,
    YA_ATENDIDO;

    //comprueba si el parametro que se le pasa, esta en el Enum
    public static boolean contiene(String valor) {
        for (EstadoTurno estado : EstadoTurno.values()) {
            if (estado.name().equalsIgnoreCase(valor)) {
                return true;
            }
        }
        return false;
    }
}
