package aemet.estados;

public class EstadoCritico implements EstadoGravedad {
    
    @Override
    public int determinarPrioridad() {
        return 3; // Prioridad máxima 
    }

    @Override
    public String formatearEncabezado() {
        return "[EMERGENCIA ROJA] - PELIGRO EXTREMO: ";
    }
}
