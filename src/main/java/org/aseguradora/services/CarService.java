package org.aseguradora.services;

import java.util.List;

public interface CarService {

    List<String> findDistinctName();

    List<String> findDistinctModelByName(String name);

    List<Integer> findDistinctByNameAndModel(String name , String model);

    Double findPrice(String name , String model , Integer year);

    Double applyQuote(Double price, Integer type);
}
