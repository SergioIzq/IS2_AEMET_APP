/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aemet.comandos;

/**
 *
 * @author fecre
 */
import aemet.aplicacion.GestorAemet;
import aemet.eventos.EventoMeteorologico;

public class ComandoEnviar implements ComandoAccion {

    private GestorAemet gestor;
    private EventoMeteorologico evento;

    public ComandoEnviar(GestorAemet gestor, EventoMeteorologico evento) {
        this.gestor = gestor;
        this.evento = evento;
    }

    @Override
    public void ejecutar() {
        System.out.println("Enviando alerta...");
        gestor.notificar(evento);
    }
}