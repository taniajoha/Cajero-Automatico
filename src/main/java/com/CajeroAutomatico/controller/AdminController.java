package com.CajeroAutomatico.controller;

import com.CajeroAutomatico.model.Dinero;
import com.CajeroAutomatico.service.DineroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Administrador")
public class AdminController {

    @Autowired
    DineroService dineroService;

    @PostMapping
    public Dinero ingresarDinero(@RequestBody Dinero dinero){
        return dineroService.ingresarDinero(dinero);
    }

    @GetMapping
    public Iterable<Dinero> consultarDinero(){
        return dineroService.consultarDinero();
    }

}
