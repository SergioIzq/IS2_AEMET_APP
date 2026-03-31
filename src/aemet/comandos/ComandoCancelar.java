/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aemet.comandos;

/**
 *
 * @author fecre
 */
import aemet.eventos.EventoMeteorologico;

public class ComandoCancelar implements ComandoAccion {

    private EventoMeteorologico evento;

    public ComandoCancelar(EventoMeteorologico evento) {
        this.evento = evento;
    }

    @Override
    public void ejecutar() {
        System.out.println("Alerta cancelada para evento: " + evento.getDatos());
    }
}