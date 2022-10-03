package com.spring.test.repository;

import com.spring.test.models.Keyboard;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface KeyBoardRepository extends CrudRepository<Keyboard, Long> {

    List<Keyboard> findByFormPer(Integer formPer);

    List<Keyboard> findByNameContaining(String name);

}
