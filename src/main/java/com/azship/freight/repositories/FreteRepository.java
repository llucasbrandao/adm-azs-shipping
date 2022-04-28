package com.azship.freight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azship.freight.models.FreteModel;

@Repository
public interface FreteRepository extends JpaRepository<FreteModel, Long> {

}
