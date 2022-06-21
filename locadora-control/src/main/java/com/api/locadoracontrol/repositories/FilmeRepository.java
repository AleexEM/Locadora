package com.api.locadoracontrol.repositories;

import com.api.locadoracontrol.models.FilmeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FilmeRepository extends JpaRepository<FilmeModel, Long> {


    List<FilmeModel> findAllByStatus(String status);
}
