package com.CajeroAutomatico.repository;

import com.CajeroAutomatico.model.Dinero;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DineroRepository extends CrudRepository<Dinero, Integer> {
        Dinero getByDenominacion(Integer denominacion);
}
