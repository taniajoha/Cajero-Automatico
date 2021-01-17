package com.CajeroAutomatico.service.impl;

import com.CajeroAutomatico.model.Dinero;
import com.CajeroAutomatico.repository.DineroRepository;
import com.CajeroAutomatico.service.DineroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DineroServiceImpl implements DineroService {

    @Autowired
    private DineroRepository dineroRepository;

    @Override
    public Dinero ingresarDinero(Dinero dinero) {
        Dinero nuevodinero = this.obtenerDinero(dinero.getDenominacion());
        nuevodinero.setCantidad(nuevodinero.getCantidad() + dinero.getCantidad());
        return dineroRepository.save(nuevodinero);
    }

    @Override
    public Dinero obtenerDinero(Integer denominacion) {
        return dineroRepository.getByDenominacion(denominacion);
    }

    @Override
    public Iterable<Dinero> consultarDinero() {
        return dineroRepository.findAll();
    }

    @Override
    public List<Dinero> retiroDinero(Integer valorTotal) {
        return this.retiro(valorTotal);
    }

    @Override
    public void actualizarDinero(Dinero dinero) {
        Dinero nuevodinero = this.obtenerDinero(dinero.getDenominacion());
        nuevodinero.setCantidad(nuevodinero.getCantidad() - dinero.getCantidad());
        dineroRepository.save(nuevodinero);
    }

    private List<Dinero> retiro(Integer valorTotal){
        Iterable<Dinero> denominaciones = this.consultarDinero();
        List<Integer> respuesta = new ArrayList<Integer>();
        Integer denominacion = 0;
        Integer cantidad = 0;
        Integer diferencia = valorTotal;
        Integer totalBanco = 0;

        for(Dinero dinero : denominaciones){
            totalBanco += (dinero.getDenominacion()*dinero.getCantidad());
        }

        if(valorTotal<totalBanco) throw new RuntimeException("No hay suficiente dinero para retirar");

        while(cantidad < valorTotal){
            diferencia -= denominacion;
            denominacion = eleccionDenominacion(denominaciones, diferencia);
            cantidad += denominacion;
            respuesta.add(denominacion);
        }

        return sintetizarRespuesta(respuesta);
    }

    private List<Dinero> sintetizarRespuesta(List<Integer> respuesta){
        List<Dinero> respuestaCajero = new ArrayList<Dinero>();
        respuestaCajero.add(new Dinero(1,50000,0));
        respuestaCajero.add(new Dinero(2,20000,0));
        respuestaCajero.add(new Dinero(3,10000,0));
        respuestaCajero.add(new Dinero(4,5000,0));
        respuestaCajero.add(new Dinero(5,2000,0));
        respuestaCajero.add(new Dinero(6,1000,0));

        for(Integer denominacion : respuesta){
            for(Dinero dinero : respuestaCajero){
                if(denominacion.equals(dinero.getDenominacion())){
                    dinero.setCantidad(dinero.getCantidad() + 1);
                }
            }
        }
        for(Dinero dinero : respuestaCajero){
            actualizarDinero(dinero);
        }
        return respuestaCajero;
    }

    private Integer eleccionDenominacion(Iterable<Dinero> denominaciones, Integer diferencia){
        Integer aux = 0;
        Iterator<Dinero> it = denominaciones.iterator();
        while(it.hasNext())
        {
            Dinero denominacion = it.next();
            if((denominacion.getDenominacion() <= diferencia) && denominacion.getCantidad() > 0){
                denominacion.setCantidad(denominacion.getCantidad()-1);
                aux = denominacion.getDenominacion();
                break;
            }
        }
        return aux;
    }


}
