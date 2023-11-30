package edu.uniandes.hotelandes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import edu.uniandes.hotelandes.repositories.ClienteRepository;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping
    public String index(Model model, String id) {
        model.addAttribute("cuentas", clienteRepository.findById(id).get().getCuentas());
        return "cuentas";
    }

}
