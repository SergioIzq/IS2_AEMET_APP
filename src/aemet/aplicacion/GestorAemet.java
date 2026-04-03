package aemet.aplicacion;

import aemet.eventos.EventoMeteorologico;
import aemet.usuarios.Suscrito;
import aemet.comandos.ComandoAccion;
import aemet.filtros.FiltroPreferencias;
import aemet.mensajes.AlertaBase;
import aemet.mensajes.DecoradorTimestamp;
import aemet.mensajes.MensajeAlerta;
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

        // Construimos el mensaje usando el Patrón State (encabezado) + Decorator (timestamp)
        String encabezado = (e.getEstado() != null) ? e.getEstado().formatearEncabezado() : "[ALERTA] ";
        String texto = encabezado + e.getClass().getSimpleName() + " - Datos: " + e.getDatos() + "\n";
        MensajeAlerta mensaje = new DecoradorTimestamp(new AlertaBase(texto));

        // Recorremos la lista y avisamos a cada suscriptor que cumpla sus filtros
        for (Suscrito s : suscriptores) {
            List<FiltroPreferencias> filtros = s.getFiltros();
            boolean pasa = filtros.isEmpty() || filtros.stream().allMatch(f -> f.cumple(e));
            if (pasa) {
                s.actualizar(mensaje, e);
            }
        }
    }

    // Métodos del patrón Command
    public void ejecutarComando(ComandoAccion c) {
        c.ejecutar();
    }
}