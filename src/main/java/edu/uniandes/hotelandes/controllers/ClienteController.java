package edu.uniandes.hotelandes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uniandes.hotelandes.repositories.ClienteRepository;
import edu.uniandes.hotelandes.services.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }

    @GetMapping(value = "/{id}/delete")
    public String delete( @PathVariable String id){
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }

    @GetMapping(value = "/delete")
    public String deleteAll(){
        clienteRepository.deleteAll();
        return "redirect:/clientes";
    }

    @GetMapping(value = "/populate")
    public String populate(){
        this.clienteService.insertDummies();
        return "redirect:/clientes";
    } 
}
