package aemet.filtros;

import aemet.eventos.EventoMeteorologico;

public class FiltroTipoEvento implements FiltroPreferencias {

    // Guardamos la clase del evento que queremos filtrar
    private Class<? extends EventoMeteorologico> tipoDeseado;

    public FiltroTipoEvento(Class<? extends EventoMeteorologico> tipoDeseado) {
        this.tipoDeseado = tipoDeseado;
    }

    @Override
    public boolean cumple(EventoMeteorologico evento) {
        // Comprueba si el evento que llega es una instancia de la clase deseada
        return tipoDeseado.isInstance(evento);
    }
}