package aemet.usuarios;

import aemet.eventos.EventoMeteorologico;
import aemet.filtros.FiltroPreferencias;
import java.util.List;

/**
 *
 * @author Sergio
 */
public interface Suscrito {

    void actualizar(MensajeAlerta mensaje, EventoMeteorologico evento);
    List<FiltroPreferencias> getFiltros();
}
