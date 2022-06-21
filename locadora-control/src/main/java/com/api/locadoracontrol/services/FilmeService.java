package com.api.locadoracontrol.services;


import com.api.locadoracontrol.models.FilmeModel;
import com.api.locadoracontrol.repositories.FilmeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Status;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    final FilmeRepository filmesRepository;

    public FilmeService(FilmeRepository filmesRepository) {
        this.filmesRepository = filmesRepository;
    }

    @Transactional
    public FilmeModel save(FilmeModel filmesModel) {
        return filmesRepository.save(filmesModel);
    }

    public Page<FilmeModel> findAll(Pageable pageable) {
        return filmesRepository.findAll(pageable);
    }

    public List<FilmeModel> disponiveis() {
        return filmesRepository.findAllByStatus("Disponivel");
    }

    public Optional<FilmeModel> findById(Long id) {
        return  filmesRepository.findById(id);
    }

    @Transactional
    public void delete(FilmeModel filmesModel) {
        filmesRepository.delete(filmesModel);
    }

}
