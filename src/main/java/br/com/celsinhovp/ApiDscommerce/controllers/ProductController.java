package br.com.celsinhovp.ApiDscommerce.controllers;


import br.com.celsinhovp.ApiDscommerce.dto.ProductDTO;
import br.com.celsinhovp.ApiDscommerce.dto.ProductMinDTO;
import br.com.celsinhovp.ApiDscommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductMinDTO>> findAll(
            @RequestParam(name = "name", defaultValue = "") String name,
            Pageable pageable) {
        Page<ProductMinDTO> dto = service.findAll(name, pageable);
        return ResponseEntity.ok(dto);
    }

    /*
    @GetMapping
    public ResponseEntity<Page<ProductMinDTO>>  findAll(
            Pageable pageable) {
        Page<ProductMinDTO> dto = service.ListarTodos(pageable);
        return ResponseEntity.ok(dto);
    }
    */

}
