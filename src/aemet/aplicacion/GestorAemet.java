package aemet.aplicacion;

import aemet.eventos.EventoMeteorologico;
import aemet.usuarios.Suscrito;
import aemet.comandos.ComandoAccion;
// import aemet.mensajes.MensajeAlerta;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase central del sistema (Patrón Singleton) y 
 * Sujeto (Subject) del Patrón Observer.
 * * @author Sergio
 */
public class GestorAemet {

    // Instancia única (Singleton)
    private static GestorAemet gestor = null;
    
    private List<Suscrito> suscriptores = new ArrayList<>();
    private List<EventoMeteorologico> historicoEventos = new ArrayList<>();

    private GestorAemet() {
    }

    // Método para obtener la instancia única
    public static GestorAemet getInstancia() {
        if (gestor == null) {
            gestor = new GestorAemet();
        }
        return gestor;
    }

    // Métodos del patrón observer
    public void suscribir(Suscrito s) {
        if (!suscriptores.contains(s)) {
            suscriptores.add(s);
        }
    }

    // Método para eliminar la suscripción
    public void desuscribir(Suscrito s) {
        suscriptores.remove(s);
    }

    // Patrón Observer: avisar a todos
    public void notificar(EventoMeteorologico e) {
        historicoEventos.add(e);

        // Aquí se generaría el mensaje de alerta (Patrón Decorator/State)
        // MensajeAlerta mensaje = ...

        // Recorremos la lista y avisamos a cada usuario suscrito
        for (Suscrito s : suscriptores) {
            // Pasamos null temporalmente hasta que se implemente la creación del mensaje
            s.actualizar(null, e); 
        }
    }

    // Métodos del patrón Command
    public void ejecutarComando(ComandoAccion c) {
        // c.ejecutar();
    }
}