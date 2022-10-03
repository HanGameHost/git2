package com.spring.test.repository;

import com.spring.test.models.Computer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompRepository extends CrudRepository<Computer, Long> {
    List<Computer> findByChipContains(String chip);
    List<Computer> findByDrip(String drip);
}
