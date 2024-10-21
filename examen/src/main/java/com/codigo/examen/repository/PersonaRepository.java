package com.codigo.examen.repository;

import com.codigo.examen.entity.PersonaEntity;
import com.codigo.examen.enums.EstadoPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Long> {

    List<PersonaEntity> findByEstado(EstadoPersona estado);

    @Query("SELECT P FROM PersonaEntity P WHERE P.dni=:datoDni")
    PersonaEntity findByEstadoQuery(@Param("datoDni") String datoDni);
}
