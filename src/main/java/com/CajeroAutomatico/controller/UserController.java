package com.CajeroAutomatico.controller;

import com.CajeroAutomatico.model.Dinero;
import com.CajeroAutomatico.service.DineroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    DineroService dineroService;

    @GetMapping("/retiro")
    public List<Dinero> retirarDinero(@RequestParam Integer valor)
    {
        return dineroService.retiroDinero(valor);
    }

}
