package ee.itcollege.taltechcars.service;


import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.model.Lease;
import ee.itcollege.taltechcars.model.User;
import ee.itcollege.taltechcars.repository.CarRepository;
import ee.itcollege.taltechcars.repository.LeaseRepository;
import ee.itcollege.taltechcars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    public Lease save(LeaseDto lease) {
        Lease dbLease = new Lease();

        //todo validate lease.getUser is not null
        Optional<User> dbUserOp = userRepository.findById(lease.getUser().getId());
        User dbUser = dbUserOp.orElseThrow(RuntimeException::new);
        dbLease.setUser(dbUser);

        Optional<Car> dbCarOp = carRepository.findById(lease.getCar().getId());
        Car dbCar = dbCarOp.orElseThrow(RuntimeException::new); //todo return 400
        dbLease.setCar(dbCar);

        dbLease.setStartDate(LocalDate.now());
        dbLease.setEndDate(LocalDate.now().plusWeeks(1));

        return leaseRepository.save(dbLease);
    }

    public List<Lease> findAll() {
        return leaseRepository.findAll();
    }
}
