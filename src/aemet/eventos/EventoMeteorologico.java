package aemet.eventos;

import aemet.estados.EstadoGravedad;

public abstract class EventoMeteorologico {
    
    // Protected para que las clases hijas puedan acceder a ellos
    protected String datosCrudos;
    protected EstadoGravedad estado;
    
    // Constructor
    public EventoMeteorologico(String datosCrudos) {
        this.datosCrudos = datosCrudos;
    }

    // Método para cambiar el Estado (Patrón State)
    public void setEstado(EstadoGravedad e) {
        this.estado = e;
    }
    
    public EstadoGravedad getEstado() {
        return this.estado;
    }

    public String getDatos() {
        return datosCrudos;
    }

    // Método que cada hijo implementará a su manera
    public abstract void procesarEvento();
    
}
