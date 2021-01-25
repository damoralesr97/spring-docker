package com.dmorales.practicaSD.ApiEmpleados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dmorales.practicaSD.ApiEmpleados.entities.TEmpleado;

@Repository
public interface TEmpleadoRepository extends JpaRepository<TEmpleado,Integer> {

}
