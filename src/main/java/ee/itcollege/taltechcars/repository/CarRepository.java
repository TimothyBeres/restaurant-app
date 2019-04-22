package ee.itcollege.taltechcars.repository;

import ee.itcollege.taltechcars.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByModelNrLike(String modelNr);
}
