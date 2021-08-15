package com.durandsuppicich.danmsmateriales.controller;

import java.net.URI;
import java.util.List;

import com.durandsuppicich.danmsmateriales.domain.Product;
import com.durandsuppicich.danmsmateriales.dto.product.ProductDto;
import com.durandsuppicich.danmsmateriales.dto.product.ProductPostDto;
import com.durandsuppicich.danmsmateriales.dto.product.ProductPutDto;
import com.durandsuppicich.danmsmateriales.mapper.IProductMapper;
import com.durandsuppicich.danmsmateriales.service.IProductService;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@RestController
@Validated
@RequestMapping("/api/products")
@Api(value = "ProductController")
@CrossOrigin(origins = "*", methods=
        {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductController {

    private final IProductService productService;
    private final IProductMapper productMapper;

    public ProductController(
            IProductService productService,
            IProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    @ApiOperation(value = "Creates a new product")
    @Timed(description = "time to post a product")
    public ResponseEntity<ProductDto> post(@RequestBody @Valid ProductPostDto productDto) {

        Product product = productService.post(productMapper.map(productDto));
        ProductDto body = productMapper.mapToDto(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.getId())
                .toUri();

        return ResponseEntity.created(location).body(body);
    }

    @GetMapping
    @ApiOperation(value = "Retrieves all products")
    @Timed(description = "time to get all products")
    public ResponseEntity<List<ProductDto>> getAll() {

        List<Product> products = productService.getAll();
        List<ProductDto> body = productMapper.mapToDto(products);

        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retrieves a product based on the given id")
    public ResponseEntity<ProductDto> getById(@PathVariable @Positive Integer id) {

        Product product = productService.getById(id);
        ProductDto body = productMapper.mapToDto(product);

        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/all-in")
    @ApiOperation(value = "Retrieves all the products included in the ids list")
    public ResponseEntity<List<ProductDto>> getAllByIds(@RequestBody List<@Positive Integer> ids) {

        List<Product> products = productService.getAllByIds(ids);
        List<ProductDto> body = productMapper.mapToDto(products);

        return ResponseEntity.ok(body);
    }

    @GetMapping(params = "name")
    @ApiOperation(value = "Retrieves a product based on the given name")
    public ResponseEntity<ProductDto> getByName(
            @RequestParam(name = "name") @NotBlank @Size(max = 32) String name) {

        Product product = productService.getByName(name);
        ProductDto body = productMapper.mapToDto(product);

        return ResponseEntity.ok(body);
    }

    @GetMapping(params = {"minimumStock", "maximumStock"})
    @ApiOperation(value = "Retrieves a product list based on the given stock range")
    public ResponseEntity<List<ProductDto>> getByStockRange(
            @RequestParam(name = "minimumStock") @PositiveOrZero Integer minimumStock,
            @RequestParam(name = "maximumStock") @PositiveOrZero Integer maximumStock) {
        
        List<Product> products = productService.getByStockRange(minimumStock, maximumStock);
        List<ProductDto> body = productMapper.mapToDto(products);

        return ResponseEntity.ok(body);
    }

    @GetMapping(params = {"minimumPrice", "maximumPrice"})
    @ApiOperation(value = "Retrieves a product list based on the given price range")
    public ResponseEntity<List<ProductDto>> getByPriceRange(
            @RequestParam(name = "minimumPrice") @PositiveOrZero Double minimumPrice,
            @RequestParam(name = "maximumPrice") @PositiveOrZero Double maximumPrice) {
        
        List<Product> products = productService.getByPriceRange(minimumPrice, maximumPrice);
        List<ProductDto> body = productMapper.mapToDto(products);

        return ResponseEntity.ok(body);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Updates a product based on the given id")
    public ResponseEntity<?> put(
            @RequestBody @Valid ProductPutDto productDto,
            @PathVariable @Positive Integer id) {

        Product product = productMapper.map(productDto);

        productService.put(product, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletes a product based on the given id")
    public ResponseEntity<?> delete(@PathVariable @Positive Integer id) {

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
