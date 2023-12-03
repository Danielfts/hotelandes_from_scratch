package edu.uniandes.hotelandes.errors;

public enum ErrorMessages {
    DUPLICATEKEY("Ya existe un registro con ese atributo unico"), 
    EMPTYFIELD("Campo en blanco"),
    INVALIDFIELD("Campo con valor invalido"),
    NEGATIVECOST("El costo no puede ser negativo");
    

    public String message;
    private ErrorMessages(String m) {
        this.message = m;
    }
}
