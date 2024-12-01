package com.basicsourcecode.controller;

import com.basicsourcecode.entity.BasicEntity;
import com.basicsourcecode.service.BasicService;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class BasicController<E extends BasicEntity<P>, P extends Serializable, S extends BasicService<E, P>> {

    protected final String SAVE = "/save";
    protected final String UPDATE = "/update";
    protected final String UPDATE_BY_ID = "/update/{ID}/";
    protected final String GET_BY_ID = "/get-by-id/{ID}";
    protected final String FIND_BY_ID = "/find-by-id/";
    protected final String FIND_ALL = "/find-all";

    @Autowired
    protected S service;

    @PostMapping(SAVE)
    public ResponseEntity<?> save(@Valid @RequestBody E entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @PutMapping(UPDATE_BY_ID)
    public ResponseEntity<?> update(@PathVariable(name = "ID") P id, @Valid @RequestBody E entity) {
        return new ResponseEntity<>(service.saveOrUpdate(id, entity), HttpStatus.OK);
    }

    @GetMapping(GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable(name = "ID") P id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping(FIND_BY_ID)
    public ResponseEntity<?> findById(@RequestParam(name = "id") P id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }


}
