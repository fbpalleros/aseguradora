package org.aseguradora.repositories;

import org.aseguradora.config.HibernateConfig;
import org.aseguradora.entity.Car;
import org.aseguradora.repositories.impl.CarRepositoryImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, CarRepositoryImpl.class})
public class CarRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private CarRepository carRepository;

    @BeforeEach
    public void init() {
        this.carRepository = new CarRepositoryImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObteganTodosLosVehiculos() {
        List<Car> carsMock = getCompleteCars();

        List<Car> cars = this.carRepository.findAll();

        assertThat(cars, equalTo(carsMock));
        assertThat(cars.size(), equalTo(3));
        assertThat(cars.size(), equalTo(carsMock.size()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObteganSinRepetirLasMarcasDeTodosLosVehiculos() {
        List<String> namesMock = getNameCars();

        List<String> names = this.carRepository.findDistinctName();

        assertThat(names.size(), equalTo(2));
        assertThat(names, equalTo(namesMock));
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObtenganLosModelos() {
        List<String> modelsMock = getModels();
        List<String> models = this.carRepository.findByModel();

        assertThat(models.size(), equalTo(3));
        assertThat(models.size(), equalTo(modelsMock.size()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObtenganLosModelosSegunSuMarca() {
        List<String> modelsByNameMock = getModelsByName();
        List<String> modelsByName = this.carRepository.findByModelWithParameter("Fiat");

        assertThat(modelsByName.size(), equalTo(1));
        assertThat(modelsByName.size(), equalTo(modelsByNameMock.size()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSeObtengaElAnioDelVehiculo(){
        List<Integer> yearsMock = getYears();
        List<Integer> years = this.carRepository.findDistinctByNameAndModel("Fiat", "Argo");

        assertThat(years.size(), equalTo(1));
        assertThat(years.size(), equalTo(yearsMock.size()));
        assertThat(years.get(0), equalTo(2017));
    }

    private List<Car> getCompleteCars() {
        List<Car> carsMock = new ArrayList<>();
        carsMock.add(new Car(1L, "Ford", "Focus", 2005, 200000.00));
        carsMock.add(new Car(2L, "Ford", "Fiesta", 2006, 240000.00));
        carsMock.add(new Car(3L, "Fiat", "Argo", 2017, 780000.00));

        this.sessionFactory.getCurrentSession().save(carsMock.get(0));
        this.sessionFactory.getCurrentSession().save(carsMock.get(1));
        this.sessionFactory.getCurrentSession().save(carsMock.get(2));

        return carsMock;
    }

    private List<String> getNameCars() {
        getCompleteCars();

        return this.sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT c.name FROM Car c").getResultList();
    }


    private List<String> getModels() {
        getCompleteCars();
        return this.sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT c.model FROM Car c").getResultList();
    }

    private List<String> getModelsByName() {
        getCompleteCars();

        return this.sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT c.model FROM Car c where c.name=?1")
                .setParameter(1, "Fiat").getResultList();
    }

    private List<Integer> getYears(){
        getCompleteCars();

        return this.sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT c.anio FROM Car c WHERE c.name=?1 and c.model=?2")
                .setParameter(1, "Fiat")
                .setParameter(2, "Argo")
                .getResultList();
    }

}
