package edu.uniandes.hotelandes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uniandes.hotelandes.entities.ServicioEntity;
import edu.uniandes.hotelandes.services.ServicioService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/servicios")
@Slf4j
public class ServiciosController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public String index(Model model) {
        List<ServicioEntity> servicios = this.servicioService.getAll();
        model.addAttribute("servicios", servicios);
        return "servicios";
    }

    @GetMapping("/delete")
    public String deleteAll(){
        this.servicioService.deleteAll();
        return "redirect:/servicios";
    }
    @GetMapping("/{id}/delete")
    public String deleteOne(@PathVariable String id){
        this.servicioService.deleteOne(id);;
        return "redirect:/servicios";
    }

    @GetMapping("/populate")
    public String populate(){
        try {
            this.servicioService.populate();    
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/servicios";
    }


    @Data
    class HasProduct{
        boolean hasProducts;
    }

    @GetMapping("/new")
    public String create(Model model){
        HasProduct hasProducts = new HasProduct();
        model.addAttribute("servicio", new ServicioEntity());
        model.addAttribute("hasProducts", hasProducts);
        return "newServicio";
    }

    @PostMapping("/new/save")
    public String createSave(@ModelAttribute ServicioEntity servicio, @ModelAttribute HasProduct hasProducts){
        System.out.println(hasProducts);
        if (hasProducts.hasProducts){
            servicio.setProductos(new ArrayList<>());
            servicio.setCosto(null);
        }
        ServicioEntity result = this.servicioService.create(servicio);
        System.out.println(result);
        return "redirect:/servicios";
    }

    @GetMapping("/{id}/productos")
    public String indexProducts(Model model, @PathVariable String id){
        ServicioEntity servicio = this.servicioService.getOne(id);
        model.addAttribute("servicioId", id);
        model.addAttribute("servicio", servicio);
        return "productos";
    }

    
    
}
