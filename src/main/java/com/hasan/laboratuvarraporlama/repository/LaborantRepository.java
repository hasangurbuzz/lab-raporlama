package com.hasan.laboratuvarraporlama.repository;

import com.hasan.laboratuvarraporlama.model.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaborantRepository extends JpaRepository<Laborant, Integer> {


}
