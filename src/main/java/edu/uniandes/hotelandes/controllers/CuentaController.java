package edu.uniandes.hotelandes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import edu.uniandes.hotelandes.entities.CuentaEntity;
import edu.uniandes.hotelandes.repositories.ClienteRepository;
import edu.uniandes.hotelandes.repositories.CuentaRepository;
import edu.uniandes.hotelandes.services.CuentaService;

@Controller
@RequestMapping("/cuentas/{id}")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaService cuentaService;
    
    @GetMapping
    public String index(Model model, @PathVariable String id) {
        List<String> ids = clienteRepository.findById(id).get().getCuentas();
        
        List<CuentaEntity> cuentas = new ArrayList<CuentaEntity>();

        for (String cuentaId : ids) {
            cuentas.add(cuentaRepository.findById(cuentaId).get());
        }

        model.addAttribute("id", id);
        model.addAttribute("cuentas", cuentas);
        return "cuentas";
    }

    @GetMapping(value = "/populate")
    public String populate() {
        this.cuentaService.insertDummies();
        return "redirect:/cuentas";
    }

    @GetMapping(value = "/edit/{id2}")
    public String edit(Model model, @PathVariable String id) {
        CuentaEntity cuenta = cuentaRepository.findById(id).get();
        model.addAttribute("cuenta", cuenta);
        model.addAttribute("title", "Editar cuenta");
        model.addAttribute("checkIn", cuenta.getCheckIn());
        model.addAttribute("checkOut", cuenta.getCheckOut());
        return "editCuenta";
    }

}
