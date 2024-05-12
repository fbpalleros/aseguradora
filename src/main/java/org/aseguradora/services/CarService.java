package org.aseguradora.services;

import java.util.List;

public interface CarService {

    List<String> findDistinctName();

    List<String> findDistinctModelByName(String name);

    List<Integer> findDistinctByYear();
}
