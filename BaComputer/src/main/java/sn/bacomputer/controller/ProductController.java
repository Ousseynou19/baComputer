package sn.bacomputer.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.bacomputer.DTO.ProductDTO;
import sn.bacomputer.service.ProductService;


import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO dto) {
        return productService.create(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")

    @GetMapping
    public List<ProductDTO> listAll() {
        return productService.listAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable Long id) {
        return productService.get(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        return productService.update(id, dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
