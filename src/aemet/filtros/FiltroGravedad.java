package aemet.filtros;

import aemet.eventos.EventoMeteorologico;
import aemet.estados.EstadoGravedad;

public class FiltroGravedad implements FiltroPreferencias {

    // Guardamos la clase del estado que queremos filtrar
    private Class<? extends EstadoGravedad> gravedadDeseada;

    public FiltroGravedad(Class<? extends EstadoGravedad> gravedadDeseada) {
        this.gravedadDeseada = gravedadDeseada;
    }

    @Override
    public boolean cumple(EventoMeteorologico evento) {
        // Obtenemos el estado del evento y comprobamos si es del tipo deseado
        return gravedadDeseada.isInstance(evento.getEstado());
    }
}