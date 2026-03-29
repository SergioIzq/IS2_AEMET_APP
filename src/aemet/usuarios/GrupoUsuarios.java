package aemet.usuarios;

import aemet.eventos.EventoMeteorologico;
import aemet.filtros.FiltroPreferencias;
import aemet.mensajes.MensajeAlerta;
import java.util.ArrayList;
import java.util.List;

/**
 * Nodo del patrón Composite. Representa una agrupación de usuarios.
 * @author Sergio
 */
public class GrupoUsuarios implements Suscrito {

    private String nombreGrupo;
    // Lista de miembros que componen este grupo
    private List<Suscrito> miembros;
    // Filtros aplicables a todo el grupo
    private List<FiltroPreferencias> filtros;

    // Constructor
    public GrupoUsuarios(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
        this.miembros = new ArrayList<>();
        this.filtros = new ArrayList<>();
    }

    // Métodos del patrón composite
    public void agregarMiembro(Suscrito s) {
        if (!miembros.contains(s)) {
            miembros.add(s);
        }
    }

    // Aunque no salga explícitamente en el diagrama, es buena práctica poder sacar gente del grupo
    public void eliminarMiembro(Suscrito s) {
        miembros.remove(s);
    }

    // Métodos del observador
    @Override
    public void actualizar(MensajeAlerta mensaje, EventoMeteorologico evento) {
        System.out.println(">>> Procesando alerta para el grupo: " + nombreGrupo);
        
        // El grupo no hace nada con el mensaje por sí mismo, simplemente 
        // propaga la alerta a todos los miembros que contiene.
        for (Suscrito miembro : miembros) {
            miembro.actualizar(mensaje, evento);
        }
    }

    @Override
    public List<FiltroPreferencias> getFiltros() {
        return this.filtros;
    }

    // Métodos extra para gestionar preferencias
    public void agregarFiltro(FiltroPreferencias filtro) {
        this.filtros.add(filtro);
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }
}