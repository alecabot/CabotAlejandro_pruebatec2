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
    NINGUNO,
    EN_ESPERA,
    YA_ATENDIDO;

    public static boolean contiene(String valor) {
        for (EstadoTurno estado : EstadoTurno.values()) {
            if (estado.name().equalsIgnoreCase(valor)) {
                return true;
            }
        }
        return false;
    }
}
