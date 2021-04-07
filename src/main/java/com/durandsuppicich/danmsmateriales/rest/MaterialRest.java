package com.durandsuppicich.danmsmateriales.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import com.durandsuppicich.danmsmateriales.domain.Material;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/material")
@Api(value = "MaterialRest", description = "Permite gestionar los materiales")
public class MaterialRest {
    
    private static Integer ID_GEN = 1;
    private List<Material> materiales = new ArrayList<Material>();


    @PostMapping
    @ApiOperation(value = "Crea un nuevo material")
    public ResponseEntity<Material> crear(@RequestBody Material material) {
        material.setId(ID_GEN++);
        materiales.add(material);
        return ResponseEntity.ok(material);
    }

    @GetMapping
    @ApiOperation(value = "Lista todos los materiales")
    public ResponseEntity<List<Material>> todos() {
        return ResponseEntity.ok(materiales);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un material por id")
    public ResponseEntity<Material> materialPorId(@PathVariable Integer id) {
        Optional<Material> material = materiales
            .stream()
            .filter(m -> m.getId().equals(id))
            .findFirst();
        return ResponseEntity.of(material);
    }

    @GetMapping(params = "nombre")
    @ApiOperation(value = "Busca un material por nombre")
    public ResponseEntity<Material> materialPorNombre(@RequestParam(name = "nombre") String nombre) {
        Optional<Material> material = materiales
            .stream()
            .filter(m -> m.getNombre().equals(nombre))
            .findFirst();
        return ResponseEntity.of(material);
    }

    @PutMapping
    @ApiOperation(value = "Actualiza un material en base al id")
    public ResponseEntity<Material> actualizar(@RequestBody Material material, @PathVariable Integer id) {
        OptionalInt indexOpt = IntStream.range(0, materiales.size())
            .filter(i -> materiales.get(i).getId().equals(id))
            .findFirst();

        if (indexOpt.isPresent()) {
            materiales.set(indexOpt.getAsInt(), material);
            return ResponseEntity.ok(material);
        } else { 
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina un material en base al id")
    public ResponseEntity<Material> eliminar(@PathVariable Integer id) {
        OptionalInt indexOpt = IntStream.range(0, materiales.size())
            .filter(i -> materiales.get(i).getId().equals(id))
            .findFirst();
        
        if (indexOpt.isPresent()) {
            materiales.remove(indexOpt.getAsInt());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
