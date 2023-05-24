package com.felipe.helpdesk.controller;

import com.felipe.helpdesk.domain.dto.TecnicoDto;
import com.felipe.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(new TecnicoDto(tecnicoService.findById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDto>> findAll() {
        return new ResponseEntity<>(tecnicoService.findAll().stream().map(TecnicoDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TecnicoDto> create(@RequestBody TecnicoDto tecnicoDto){
        TecnicoDto obj = new TecnicoDto(tecnicoService.save(tecnicoDto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}