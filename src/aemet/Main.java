package aemet;

import aemet.aplicacion.GestorAemet;
import aemet.comandos.ComandoCancelar;
import aemet.comandos.ComandoEnviar;
import aemet.comandos.ComandoReenviar;
import aemet.estados.EstadoCritico;
import aemet.eventos.EventoMeteorologico;
import aemet.eventos.EventoTormenta;
import aemet.filtros.FiltroGravedad;
import aemet.filtros.FiltroTipoEvento;
import aemet.mensajes.AlertaBase;
import aemet.mensajes.DecoradorLocalizacion;
import aemet.mensajes.DecoradorTimestamp;
import aemet.mensajes.FactoryEventos;
import aemet.mensajes.MensajeAlerta;
import aemet.notificacion.NotificacionApp;
import aemet.notificacion.NotificacionSMS;
import aemet.usuarios.GrupoUsuarios;
import aemet.usuarios.UsuarioIndividual;

public class Main {

    private static void seccion(String titulo) {
        System.out.println("\n==========================================");
        System.out.println("  " + titulo);
        System.out.println("==========================================");
    }

    private static void sub(String texto) {
        System.out.println("\n  -- " + texto);
    }

    public static void main(String[] args) {

        // ══════════════════════════════════════════
        // 1. SINGLETON
        // ══════════════════════════════════════════
        seccion("1. SINGLETON");

        GestorAemet gestor  = GestorAemet.getInstancia();
        GestorAemet gestor2 = GestorAemet.getInstancia();
        System.out.println("  Misma instancia: " + (gestor == gestor2));

        // ══════════════════════════════════════════
        // 2. FACTORY + STATE
        // ══════════════════════════════════════════
        seccion("2. FACTORY + STATE");

        sub("Lluvia: 10 mm/h (bajo) y 50 mm/h (critico)");
        FactoryEventos.crearEvento("Lluvia", "10").procesarEvento();
        FactoryEventos.crearEvento("Lluvia", "50").procesarEvento();

        sub("Tormenta: 60 km/h (bajo) y 120 km/h (critico)");
        FactoryEventos.crearEvento("Tormenta", "60").procesarEvento();
        FactoryEventos.crearEvento("Tormenta", "120").procesarEvento();

        sub("Ola de calor: 37 C (medio) y 43 C (critico)");
        FactoryEventos.crearEvento("OlaCalor", "37").procesarEvento();
        FactoryEventos.crearEvento("OlaCalor", "43").procesarEvento();

        sub("Tipo desconocido -> excepcion esperada");
        try {
            FactoryEventos.crearEvento("Nieve", "5");
        } catch (IllegalArgumentException ex) {
            System.out.println("  Excepcion: " + ex.getMessage());
        }

        // ══════════════════════════════════════════
        // 3. DECORATOR
        // ══════════════════════════════════════════
        seccion("3. DECORATOR");

        MensajeAlerta base   = new AlertaBase("[EMERGENCIA ROJA] Tormenta severa. ");
        MensajeAlerta conLoc = new DecoradorLocalizacion(base, "Madrid. ");
        MensajeAlerta conTs  = new DecoradorTimestamp(conLoc);

        System.out.println("  Base    : " + base.getContenido());
        System.out.println("  + Lugar : " + conLoc.getContenido());
        System.out.println("  + Fecha : " + conTs.getContenido());

        // ══════════════════════════════════════════
        // 4. OBSERVER + STRATEGY
        // ══════════════════════════════════════════
        seccion("4. OBSERVER + STRATEGY");

        UsuarioIndividual ana   = new UsuarioIndividual("Ana",   new NotificacionApp());
        UsuarioIndividual luis  = new UsuarioIndividual("Luis",  new NotificacionSMS());
        UsuarioIndividual marta = new UsuarioIndividual("Marta", new NotificacionApp());

        gestor.suscribir(ana);
        gestor.suscribir(luis);
        gestor.suscribir(marta);

        sub("Tormenta 130 km/h -> Ana(App) Luis(SMS) Marta(App)");
        EventoMeteorologico t1 = FactoryEventos.crearEvento("Tormenta", "130");
        t1.procesarEvento();
        gestor.notificar(t1);

        // ══════════════════════════════════════════
        // 5. STRATEGY en runtime
        // ══════════════════════════════════════════
        seccion("5. STRATEGY - cambio de canal en runtime");

        ana.setEstrategia(new NotificacionSMS());
        sub("Ana cambia App -> SMS. Lluvia 25 mm/h");
        EventoMeteorologico ll1 = FactoryEventos.crearEvento("Lluvia", "25");
        ll1.procesarEvento();
        gestor.notificar(ll1);

        // ══════════════════════════════════════════
        // 6. OBSERVER - desuscripcion
        // ══════════════════════════════════════════
        seccion("6. OBSERVER - desuscripcion");

        gestor.desuscribir(marta);
        sub("Marta se da de baja. Ola de calor 42 C -> solo Ana y Luis");
        EventoMeteorologico oc1 = FactoryEventos.crearEvento("OlaCalor", "42");
        oc1.procesarEvento();
        gestor.notificar(oc1);

        gestor.desuscribir(ana);
        gestor.desuscribir(luis);

        // ══════════════════════════════════════════
        // 7. COMPOSITE
        // ══════════════════════════════════════════
        seccion("7. COMPOSITE - grupos anidados");

        UsuarioIndividual carlos = new UsuarioIndividual("Carlos", new NotificacionApp());
        UsuarioIndividual elena  = new UsuarioIndividual("Elena",  new NotificacionSMS());
        UsuarioIndividual pedro  = new UsuarioIndividual("Pedro",  new NotificacionApp());
        UsuarioIndividual rosa   = new UsuarioIndividual("Rosa",   new NotificacionSMS());

        GrupoUsuarios grupoMet  = new GrupoUsuarios("Meteorologos");
        grupoMet.agregarMiembro(carlos);
        grupoMet.agregarMiembro(elena);

        GrupoUsuarios grupoCiud = new GrupoUsuarios("Ciudadanos");
        grupoCiud.agregarMiembro(pedro);
        grupoCiud.agregarMiembro(rosa);
        grupoCiud.agregarMiembro(grupoMet);   // grupo dentro de grupo

        gestor.suscribir(grupoCiud);

        sub("Tormenta 85 km/h -> Ciudadanos (Pedro, Rosa, Meteorologos)");
        EventoMeteorologico t2 = FactoryEventos.crearEvento("Tormenta", "85");
        t2.procesarEvento();
        gestor.notificar(t2);

        sub("Elena sale del subgrupo -> lluvia 35 mm/h (Pedro, Rosa, Carlos)");
        grupoMet.eliminarMiembro(elena);
        EventoMeteorologico ll2 = FactoryEventos.crearEvento("Lluvia", "35");
        ll2.procesarEvento();
        gestor.notificar(ll2);

        gestor.desuscribir(grupoCiud);

        // ══════════════════════════════════════════
        // 8. FILTER
        // ══════════════════════════════════════════
        seccion("8. FILTER - preferencias de usuario");

        UsuarioIndividual soloCritico  = new UsuarioIndividual("SoloCritico",  new NotificacionApp());
        UsuarioIndividual soloTormenta = new UsuarioIndividual("SoloTormenta", new NotificacionSMS());
        UsuarioIndividual sinFiltro    = new UsuarioIndividual("SinFiltro",    new NotificacionApp());

        soloCritico.agregarFiltro(new FiltroGravedad(EstadoCritico.class));
        soloTormenta.agregarFiltro(new FiltroTipoEvento(EventoTormenta.class));

        gestor.suscribir(soloCritico);
        gestor.suscribir(soloTormenta);
        gestor.suscribir(sinFiltro);

        sub("Lluvia media (20 mm/h) -> solo SinFiltro recibe");
        EventoMeteorologico llF = FactoryEventos.crearEvento("Lluvia", "20");
        llF.procesarEvento();
        gestor.notificar(llF);

        sub("Tormenta critica (110 km/h) -> los tres reciben");
        EventoMeteorologico tF = FactoryEventos.crearEvento("Tormenta", "110");
        tF.procesarEvento();
        gestor.notificar(tF);

        gestor.desuscribir(soloCritico);
        gestor.desuscribir(soloTormenta);
        gestor.desuscribir(sinFiltro);

        // ══════════════════════════════════════════
        // 9. COMMAND
        // ══════════════════════════════════════════
        seccion("9. COMMAND - Enviar / Cancelar / Reenviar");

        UsuarioIndividual receptor = new UsuarioIndividual("Receptor", new NotificacionApp());
        gestor.suscribir(receptor);

        EventoMeteorologico evCmd = FactoryEventos.crearEvento("Lluvia", "40");
        evCmd.procesarEvento();

        sub("ComandoEnviar");
        gestor.ejecutarComando(new ComandoEnviar(gestor, evCmd));

        sub("ComandoCancelar");
        gestor.ejecutarComando(new ComandoCancelar(evCmd));

        sub("ComandoReenviar");
        gestor.ejecutarComando(new ComandoReenviar(gestor, evCmd));

        gestor.desuscribir(receptor);

        seccion("FIN - 9 patrones demostrados");
    }
}
