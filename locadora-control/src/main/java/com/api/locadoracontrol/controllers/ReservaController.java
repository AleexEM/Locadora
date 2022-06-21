package com.api.locadoracontrol.controllers;

import com.api.locadoracontrol.models.FilmeModel;
import com.api.locadoracontrol.models.ReservaModel;
import com.api.locadoracontrol.services.FilmeService;
import com.api.locadoracontrol.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/reservas")

public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @Autowired
    FilmeService filmeService;

    @PostMapping
    public ResponseEntity<Object> saveReserva(@RequestBody @Valid ReservaModel reservaModel){
        reservaModel.setDataDeRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        reservaModel.setStatus("Reservado");

        List<FilmeModel> filmes = reservaModel.getFilmes();

        for (FilmeModel filme : filmes){
            filme.setStatus("Alugado");

            filmeService.save(filme);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.save(reservaModel));
    }

    @GetMapping
    public ResponseEntity<Page<ReservaModel>> getAllReservas(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.findAll(pageable));
    }

    @GetMapping("/reservados")
    public ResponseEntity<List<ReservaModel>> getReservados(){
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.reservados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneReserva(@PathVariable(value = "id") Long id){
        Optional<ReservaModel> reservaModelOptional = reservaService.findById(id);
        if (!reservaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não Encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReserva(@PathVariable(value = "id") Long id){
        Optional<ReservaModel> reservaModelOptional = reservaService.findById(id);
        if (!reservaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não Encontrada.");
        }
        reservaService.delete(reservaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReserva(@PathVariable(value = "id") Long id,
                                              @RequestBody @Valid ReservaModel reservaModel) {
        Optional<ReservaModel> reservaModelOptional = reservaService.findById(id);
        if (!reservaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não Encontrada.");
        }

        reservaModel.setId(reservaModelOptional.get().getId());
        reservaModel.setDataDeRegistro(reservaModelOptional.get().getDataDeRegistro());
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.save(reservaModel));
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Object> devolverReserva(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid ReservaModel reservaModel) {
        Optional<ReservaModel> reservaModelOptional = reservaService.findById(id);
        if (!reservaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não Encontrada.");
        }

        ReservaModel reserva = reservaModelOptional.get();
        reserva.setStatus("Devolvido");

        List<FilmeModel> filmes = reserva.getFilmes();

        for (FilmeModel filme : filmes){
            filme.setStatus("Disponivel");

            filmeService.save(filme);
        }



        reserva.setDataDevolucao(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.ok().body(reservaService.save(reserva));
    }

}