/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aemet.mensajes;

import aemet.eventos.EventoLluvia;
import aemet.eventos.EventoMeteorologico;
import aemet.eventos.EventoOlaCalor;
import aemet.eventos.EventoTormenta;

/**
 *
 * @author Sergio
 */

//Encapsulamos y separamos la creación de Eventos de la lógica del programa
public class FactoryEventos {
    
    //Estático para no tener que crear un objeto fábrica
    public static EventoMeteorologico crearEvento(String tipo, String datosCrudos){
        
        //En este caso estamos usando una factoría simple
        if(tipo.equalsIgnoreCase("Tormenta")){
            
            return new EventoTormenta(datosCrudos);
            
        } else if(tipo.equalsIgnoreCase("OlaCalor")){
            
            return new EventoOlaCalor(datosCrudos);
            
        } else if(tipo.equalsIgnoreCase("Lluvia")){
            
            return new EventoLluvia(datosCrudos);
            
        }else{ throw new IllegalArgumentException("Tipo de evento no soportado: " + tipo);
        }
    }
    }
    
