package aemet.estados;

public class EstadoBajo implements EstadoGravedad {

    @Override
    public int determinarPrioridad() {
        return 1; // Prioridad mínima
    }

    @Override
    public String formatearEncabezado() {
        return "[ALERTA AMARILLA] - Situación de bajo riesgo: ";
    }
    
}
