package edu.uniandes.hotelandes.entities;

public enum TipoHabitacion {
    SUITE("Suite"), 
    EJECUTIVA("Ejecutiva"), 
    SEMISUITE("Semi-Suite"), 
    SENCILLA("Sencilla"),
    DOBLE("Doble"), 
    TRIPLE("Triple") , 
    MULTIPLE("Multiple");

    String text;
    private TipoHabitacion(String text){
        this.text = text;
    }
}
