/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aemet.mensajes;

/**
 *
 * @author luque
 */

//Decorador base en el que apoyamos la estrategia del patrón
public abstract class DecoradorAlerta implements MensajeAlerta {
    
    protected MensajeAlerta alertaEnvuelta;
    
    public DecoradorAlerta(MensajeAlerta alerta){
        this.alertaEnvuelta = alerta;
    }

    public String getConenido(){
        return alertaEnvuelta.getContenido();
    }
    
}
