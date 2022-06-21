package com.api.locadoracontrol.services;

import com.api.locadoracontrol.models.ReservaModel;
import com.api.locadoracontrol.repositories.ReservaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository){
        this.reservaRepository = reservaRepository;
    }



    @Transactional
    public ReservaModel save(ReservaModel reservaModel) {
        return reservaRepository.save(reservaModel);
    }

    public Page<ReservaModel> findAll(Pageable pageable) {
        return reservaRepository.findAll(pageable);
    }

    public List<ReservaModel> reservados() {
        return reservaRepository.findAllByStatus("Reservado");
    }

    public Optional<ReservaModel> findById(Long id) {
        return  reservaRepository.findById(id);
    }

    @Transactional
    public void delete(ReservaModel reservaModel) {
        reservaRepository.delete(reservaModel);
    }
}
