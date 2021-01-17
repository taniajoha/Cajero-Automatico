package com.CajeroAutomatico.service;

import com.CajeroAutomatico.model.Dinero;

import java.util.List;

public interface DineroService {
    Dinero ingresarDinero (Dinero dinero);
    Dinero obtenerDinero (Integer denominacion);
    Iterable<Dinero> consultarDinero();
    List<Dinero> retiroDinero(Integer valorTotal);
    void actualizarDinero(Dinero dinero);
}
