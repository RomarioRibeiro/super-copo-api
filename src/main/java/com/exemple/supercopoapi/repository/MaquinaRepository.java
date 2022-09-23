package com.exemple.supercopoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.supercopoapi.model.Maquina;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long>{

}
