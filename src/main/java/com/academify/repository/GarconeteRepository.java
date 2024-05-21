package com.academify.repository;

import com.academify.model.Garconete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarconeteRepository extends JpaRepository<Garconete, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByMatricula(String matricula);
}
