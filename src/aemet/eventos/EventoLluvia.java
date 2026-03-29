package aemet.eventos;

import aemet.estados.*;

public class EventoLluvia extends EventoMeteorologico {
    
    public EventoLluvia(String datosCrudos) {
        super(datosCrudos);
    }

    @Override
    public void procesarEvento() {
        System.out.println("Procesando datos de LLUVIA: " + datosCrudos + " mm/h");
        
        try {
            double milimetros = Double.parseDouble(this.datosCrudos);
            
            // Lógica de asignación de Estado (Patrón State)
            if (milimetros < 15) {
                this.setEstado(new EstadoBajo());
            } else if (milimetros >= 15 && milimetros <= 30) {
                this.setEstado(new EstadoMedio());
            } else {
                this.setEstado(new EstadoCritico());
            }
            
            System.out.println("-> Estado asignado: " + this.getEstado().getClass().getSimpleName());
            
        } catch (NumberFormatException e) {
            System.out.println("Error al leer los datos de lluvia.");
        }
    }
    
}
