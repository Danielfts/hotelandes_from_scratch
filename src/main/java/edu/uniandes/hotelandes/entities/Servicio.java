package edu.uniandes.hotelandes.entities;

public enum Servicio {
    PISCINA("Piscina", 10000,
            "Piscina de 25 metros de largo, con 8 carriles, con una profundidad de 1.5 metros, con una temperatura de 25 grados centigrados, con un horario de 6:00 am a 10:00 pm"),
    GIMNASIO("Gimnasio", 10000, "Gimnasio con maquinas de ultima tecnologia, con un horario de 6:00 am a 10:00 pm"),
    INTERNET("Internet", 10000, "Internet de alta velocidad, con un horario de 6:00 am a 10:00 pm"),
    BAR("Bar", 10000, "Bar con una gran variedad de bebidas, con un horario de 6:00 am a 10:00 pm"),
    RESTAURANTE("Restaurante", 10000,
            "Restaurante con una gran variedad de platos, con un horario de 6:00 am a 10:00 pm"),
    SUPERMERCADO("Supermercado", 10000,
            "Supermercado con una gran variedad de productos, con un horario de 6:00 am a 10:00 pm"),
    TIENDAS("Tiendas", 10000, "Tiendas con una gran variedad de productos, con un horario de 6:00 am a 10:00 pm"),
    SPA("Spa", 10000, "Spa con una gran variedad de servicios, con un horario de 6:00 am a 10:00 pm"),
    LAVANDERIA("Lavandería", 10000,
            "Lavandería con una gran variedad de servicios, con un horario de 6:00 am a 10:00 pm"),
    PRESTAMOUTENSILIOS("Prestamo de utensilios", 10000,
            "Prestamo de utensilios con una gran variedad de servicios, con un horario de 6:00 am a 10:00 pm"),
    SALONES("Salones", 10000, "Salones con una gran variedad de servicios, con un horario de 6:00 am a 10:00 pm"),
    SALONESGRANDES("Salones grandes", 10000,
            "Salones grandes con una gran variedad de servicios, con un horario de 6:00 am a 10:00 pm");

    public String nombre;
    public double costo;
    public String descripcion;

    private Servicio(String nombre, double costo, String descripcion) {
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
    }
}
