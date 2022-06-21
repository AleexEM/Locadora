package com.api.locadoracontrol.repositories;

import com.api.locadoracontrol.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository <ClienteModel, Long> {

}
