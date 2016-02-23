package com.dbsys.rs.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbsys.rs.patient.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {

}
