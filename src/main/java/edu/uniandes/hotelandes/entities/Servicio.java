package edu.uniandes.hotelandes.entities;

import java.util.ArrayList;
import java.util.Arrays;

public enum Servicio {
    PISCINA("Piscina", 10000,
            "Piscina de 25 metros de largo, con 8 carriles, con una profundidad de 1.5 metros, con una temperatura de 25 grados centigrados, con un horario de 6:00 am a 10:00 pm"),
    GIMNASIO("Gimnasio", 10000, "Gimnasio con maquinas de ultima tecnologia, con un horario de 6:00 am a 10:00 pm"),
    INTERNET("Internet", 10000, "Internet de alta velocidad, con un horario de 6:00 am a 10:00 pm"),
    BAR("Bar", "Bar con una gran variedad de bebidas, con un horario de 6:00 am a 10:00 pm", new ArrayList<Producto>(Arrays.asList(new Producto("Cerveza", 10000), new Producto("Vino", 10000), new Producto("Whisky", 10000)))),
    RESTAURANTE("Restaurante",
            "Restaurante con una gran variedad de platos, con un horario de 6:00 am a 10:00 pm", new ArrayList<Producto>(Arrays.asList(new Producto("Desayuno", 10000), new Producto("Almuerzo", 10000), new Producto("Cena", 10000)))),
    SUPERMERCADO("Supermercado",
            "Supermercado con una gran variedad de productos, con un horario de 6:00 am a 10:00 pm", new ArrayList<Producto>(Arrays.asList(new Producto("Frutas", 10000), new Producto("Verduras", 10000), new Producto("Carnes", 10000)))),
    BOUTIQUE("Tienda boutique", "Tienda con una gran variedad de productos, con un horario de 6:00 am a 10:00 pm", new ArrayList<Producto>(Arrays.asList(new Producto("Camisa", 10000), new Producto("Pantalon", 10000), new Producto("Zapatos", 10000)))),
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
    public ArrayList<Producto> productos;

    private Servicio(String nombre, double costo, String descripcion) {
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
    }
    private Servicio(String nombre, double costo, String descripcion, ArrayList<Producto> productos) {
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
        this.productos = productos;
    }
    private Servicio(String nombre, String descripcion, ArrayList<Producto> productos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = productos;
    }
}
