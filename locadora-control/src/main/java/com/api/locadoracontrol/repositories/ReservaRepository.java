package com.api.locadoracontrol.repositories;

import com.api.locadoracontrol.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {

    List<ReservaModel> findAllByStatus(String status);
}
