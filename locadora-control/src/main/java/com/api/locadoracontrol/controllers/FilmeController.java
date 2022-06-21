package com.api.locadoracontrol.controllers;

import com.api.locadoracontrol.models.FilmeModel;
import com.api.locadoracontrol.services.FilmeService;
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
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    @PostMapping
    public ResponseEntity<Object> saveFilmes(@RequestBody @Valid FilmeModel filmeModel){

        filmeModel.setDataDeRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        filmeModel.setStatus("Disponivel");
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.save(filmeModel));
    }

    @GetMapping
    public ResponseEntity<Page<FilmeModel>> getAllFilmes(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneFilme(@PathVariable(value = "id") Long id){
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(id);
        if (!filmeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não Encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmeModelOptional.get());
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<FilmeModel>> getDisponiveis(){
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.disponiveis());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilme(@PathVariable(value = "id") Long id){
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(id);
        if (!filmeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não Encontrado.");
        }
        filmeService.delete(filmeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFilme(@PathVariable(value = "id") Long id,
                                              @RequestBody @Valid FilmeModel filmeModel) {
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(id);
        if (!filmeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não Encontrado.");
        }

        filmeModel.setId(filmeModelOptional.get().getId());
        filmeModel.setDataDeRegistro(filmeModelOptional.get().getDataDeRegistro());
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.save(filmeModel));
    }

}
