/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aemet.notificacion;

/**
 *
 * @author fecre
 */
public class NotificacionApp implements EstrategiaNotificacion {

    @Override
    public void enviar(String texto) {
        System.out.println("[APP] " + texto);
    }
}