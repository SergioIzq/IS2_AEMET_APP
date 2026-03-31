/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aemet.mensajes;

/**
 *
 * @author luque
 */
public class AlertaBase implements MensajeAlerta{
    
    private String textoOriginal;

    @Override
    public String getContenido() {
        return textoOriginal;
    }
    
}
