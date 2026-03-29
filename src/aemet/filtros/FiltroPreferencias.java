package aemet.filtros;

import aemet.eventos.EventoMeteorologico;

/**
 * Interfaz para el patrón Filter / Specification.
 * Permite evaluar si un evento cumple con los requisitos del usuario.
 */
public interface FiltroPreferencias {
    
    // Evalúa si el evento pasado por parámetro debe ser notificado
    boolean cumple(EventoMeteorologico evento);
}