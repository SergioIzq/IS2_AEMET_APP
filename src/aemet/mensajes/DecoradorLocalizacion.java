/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aemet.mensajes;

/**
 *
 * @author luque
 */
//Tanto la localización como el timestamp son los decoradores concretos
public class DecoradorLocalizacion extends DecoradorAlerta {
    private String ciudad;

    public DecoradorLocalizacion(MensajeAlerta alerta, String ciudad) {
        super(alerta);
        this.ciudad = ciudad;
    }

    @Override
    public String getContenido() {
        return super.getContenido()+ "Ubicación: " + this.ciudad;
    }
}
