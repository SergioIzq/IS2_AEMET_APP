package aemet.usuarios;

import aemet.eventos.EventoMeteorologico;
import aemet.filtros.FiltroPreferencias;
//import aemet.mensajes.MensajeAlerta;
import aemet.notificacion.EstrategiaNotificacion;
import java.util.ArrayList;
import java.util.List;

/**
 * Hoja del patrón Composite y Observador concreto.
 * @author Sergio
 */
public class UsuarioIndividual implements Suscrito {

    private String nombre;
    // Patrón Strategy: Cada usuario tiene un medio de notificación 
    private EstrategiaNotificacion estrategia; 
    // Lista para almacenar los filtros/preferencias del usuario
    private List<FiltroPreferencias> filtros;

    public UsuarioIndividual(String nombre, EstrategiaNotificacion estrategia) {
        this.nombre = nombre;
        this.estrategia = estrategia;
        this.filtros = new ArrayList<>();
    }

    // Métodos del observer

    @Override
    public void actualizar(MensajeAlerta mensaje, EventoMeteorologico evento) {
        String textoAlerta = mensaje.getContenido();
        
        // El usuario no sabe CÓMO se envía, simplemente le dice a su estrategia que lo envíe
        System.out.println("Notificando a " + nombre + "...");
        estrategia.enviar(textoAlerta);
    }

    @Override
    public List<FiltroPreferencias> getFiltros() {
        return this.filtros;
    }

    // Métodos para gestionar preferencias
    public void agregarFiltro(FiltroPreferencias filtro) {
        this.filtros.add(filtro);
    }

    // Permite cambiar la estrategia de notificación en tiempo de ejecución
    public void setEstrategia(EstrategiaNotificacion estrategia) {
        this.estrategia = estrategia;
    }

    public String getNombre() {
        return nombre;
    }
}