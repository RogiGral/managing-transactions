package com.example.managingtransactions.repository;

import com.example.managingtransactions.model.DepartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentTypeRepository extends JpaRepository<DepartmentType,Long> {

}
