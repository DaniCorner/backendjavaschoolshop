package com.javaschoolshop.dao;

import com.javaschoolshop.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer> {
    // SELECT s.name FROM state s JOIN country c ON s.country_id = c.id WHERE c.code = 'SP';
    List<State> findByCountryCode(@Param("code") String code);
}
