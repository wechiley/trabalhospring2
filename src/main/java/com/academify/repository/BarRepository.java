package com.academify.repository;

import com.academify.model.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarRepository extends JpaRepository<Bar, Long> {
    boolean existsByCnpj(String cnpj);
}
