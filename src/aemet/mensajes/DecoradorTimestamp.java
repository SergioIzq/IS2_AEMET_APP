/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aemet.mensajes;
import java.time.LocalDateTime;

/**
 *
 * @author luque
 */
public class DecoradorTimestamp extends DecoradorAlerta {

    public DecoradorTimestamp(MensajeAlerta alerta) {
        super(alerta);
    }

    @Override
    public String getContenido() {
        return super.getContenido() + "Fecha: " + LocalDateTime.now();
    }
}
