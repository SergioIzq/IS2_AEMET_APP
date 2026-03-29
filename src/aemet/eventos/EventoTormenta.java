package aemet.eventos;

import aemet.estados.*;

public class EventoTormenta extends EventoMeteorologico{
    
    public EventoTormenta(String datosCrudos) {
        super(datosCrudos);
    }

    @Override
    public void procesarEvento() {
        System.out.println("Procesando rachas de viento de TORMENTA: " + datosCrudos + " km/h");
        
        try {
            double viento = Double.parseDouble(this.datosCrudos);
            
            if (viento < 70) {
                this.setEstado(new EstadoBajo());
            } else if (viento >= 70 && viento <= 100) {
                this.setEstado(new EstadoMedio());
            } else {
                this.setEstado(new EstadoCritico());
            }
            
            System.out.println("-> Estado asignado: " + this.getEstado().getClass().getSimpleName());
            
        } catch (NumberFormatException e) {
            System.out.println("Error al leer los datos de tormenta.");
        }
    }
    
}
