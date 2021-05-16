package com.durandsuppicich.danmsmateriales.rest;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsmateriales.domain.Material;
import com.durandsuppicich.danmsmateriales.exception.NotFoundException;
import com.durandsuppicich.danmsmateriales.service.IServicioMaterial;

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

    private final IServicioMaterial servicioMaterial;

    public MaterialRest(IServicioMaterial servicioMaterial) {
        this.servicioMaterial = servicioMaterial;
    }

    @PostMapping
    @ApiOperation(value = "Crea un nuevo material")
    public ResponseEntity<Material> crear(@RequestBody Material material) {

        Material body = this.servicioMaterial.crear(material);

        return ResponseEntity.ok(body);
    }

    @GetMapping
    @ApiOperation(value = "Lista todos los materiales")
    public ResponseEntity<List<Material>> todos() {

        List<Material> body = this.servicioMaterial.todos();

        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un material por id")
    public ResponseEntity<Material> materialPorId(@PathVariable Integer id) {

        Optional<Material> body = this.servicioMaterial.materialPorId(id);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        }
        else {
            throw new NotFoundException("Material no encontrado. Id: " + id);
        }
    }

    @GetMapping(params = "nombre")
    @ApiOperation(value = "Busca un material por nombre")
    public ResponseEntity<Material> materialPorNombre(@RequestParam(name = "nombre") String nombre) {

        Optional<Material> body = this.servicioMaterial.materialPorNombre(nombre);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        }
        else {
            throw new NotFoundException("Material no encontrado. Nombre: " + nombre);
        }
    }

    @GetMapping(params = {"stockMinimo", "stockMaximo"})
    @ApiOperation(value = "Busca materiales por rango de stock")
    public ResponseEntity<List<Material>> materialesPorRangoStock(
            @RequestParam(name = "stockMinimo") Integer stockMinimo,
            @RequestParam(name = "stockMaximo") Integer stockMaximo) {
        
        List<Material> body = this.servicioMaterial.materialesPorRangoStock(stockMinimo, stockMaximo);

        if (!body.isEmpty()) {
            return ResponseEntity.ok(body);
        }
        else {
            throw new NotFoundException("Materiales no encontrados. Stock Mínimo: " + stockMinimo +
                " Stock Máximo: " + stockMaximo);
        }
    }

    @GetMapping(params = {"precioMinimo", "precioMaximo"})
    @ApiOperation(value = "Busca materiales por rango de precios")
    public ResponseEntity<List<Material>> materialesPorRangoPrecio(
            @RequestParam(name = "precioMinimo") Double precioMinimo,
            @RequestParam(name = "precioMaximo") Double precioMaximo) {
        
        List<Material> body = this.servicioMaterial.materialesPorRangoPrecio(precioMinimo, precioMaximo);

        if (!body.isEmpty()) {
            return ResponseEntity.ok(body);
        }
        else {
            throw new NotFoundException("Materiales no encontrados. Precio Mínimo: " + precioMinimo +
                " Precio Máximo: " + precioMaximo);
        }
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un material en base al id")
    public ResponseEntity<Material> actualizar(@RequestBody Material material, @PathVariable Integer id) {

        this.servicioMaterial.actualizar(id, material);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina un material en base al id")
    public ResponseEntity<Material> eliminar(@PathVariable Integer id) {

        this.servicioMaterial.eliminar(id);
        
        return ResponseEntity.ok().build();
    }
}