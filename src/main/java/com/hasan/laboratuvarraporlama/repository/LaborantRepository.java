package com.hasan.laboratuvarraporlama.repository;

import com.hasan.laboratuvarraporlama.model.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaborantRepository extends JpaRepository<Laborant, Integer> {
}
