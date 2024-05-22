package org.aseguradora.repositories;

import org.aseguradora.entity.Car;


import java.util.List;

public interface CarRepository /*extendsCrudRepository <Car, Long>*/ {

    List<Car> findAll();

    List<String> findDistinctName();

    List<String> findByModel();

    List<String> findByModelWithParameter(String name);

    List<Integer> findDistinctByNameAndModel(String name , String model);

    Double findPrice(String name , String model , Integer year);
}
