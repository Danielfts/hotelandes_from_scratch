package edu.uniandes.hotelandes.errors;

public enum ErrorMessages {
    DUPLICATEKEY("Ya existe un registro con ese atributo unico");

    public String message;
    private ErrorMessages(String m) {
        this.message = m;
    }
}
