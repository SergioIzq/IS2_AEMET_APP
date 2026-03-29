package aemet.eventos;

import aemet.estados.*;

public class EventoOlaCalor extends EventoMeteorologico {
    
    public EventoOlaCalor(String datosCrudos) {
        super(datosCrudos);
    }

    @Override
    public void procesarEvento() {
        System.out.println("Procesando temperatura de OLA DE CALOR: " + datosCrudos + " °C");
        
        try {
            double temperatura = Double.parseDouble(this.datosCrudos);
            
            if (temperatura < 35) {
                this.setEstado(new EstadoBajo());
            } else if (temperatura >= 35 && temperatura <= 40) {
                this.setEstado(new EstadoMedio());
            } else {
                this.setEstado(new EstadoCritico()); // Más de 40 grados es crítico
            }
            
            System.out.println("-> Estado asignado: " + this.getEstado().getClass().getSimpleName());
            
        } catch (NumberFormatException e) {
            System.out.println("Error al leer los datos de temperatura.");
        }
    }
}
