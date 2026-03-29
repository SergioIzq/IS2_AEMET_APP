package aemet.estados;

public class EstadoMedio implements EstadoGravedad {
    
    @Override
    public int determinarPrioridad() {
        return 2; // Prioridad media
    }

    @Override
    public String formatearEncabezado() {
        return "[ALERTA NARANJA] - Precaución, riesgo moderado: ";
    }
}
