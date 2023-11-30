package edu.uniandes.hotelandes.entities;

public enum TipoHabitacion {
    SUITE("Suite", 4), 
    EJECUTIVA("Ejecutiva", 6), 
    SEMISUITE("Semi-Suite", 4), 
    SENCILLA("Sencilla", 1),
    DOBLE("Doble", 2), 
    TRIPLE("Triple", 6) , 
    MULTIPLE("Multiple", 8);

    public String text;
    public Integer capacidad;
    private TipoHabitacion(String text, Integer capacidad){
        this.text = text;
        this.capacidad = capacidad;
    }
}
