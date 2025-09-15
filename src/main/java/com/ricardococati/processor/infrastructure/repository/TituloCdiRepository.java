package com.ricardococati.processor.infrastructure.repository;

import com.ricardococati.processor.infrastructure.model.TituloCdiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TituloCdiRepository extends JpaRepository<TituloCdiEntity, Long> {
    //Métodos de consulta personalizados podem ser adicionados aqui
}
