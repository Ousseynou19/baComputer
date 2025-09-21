package sn.bacomputer.controller;

import sn.bacomputer.dto.ProductDTO;
import sn.bacomputer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Product entities.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET /api/products : Get all Products.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> list = productService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * GET /api/products/{id} : Get the product by id.
     *
     * @param id the id of the product to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        ProductDTO productDTO = productService.findOne(id);
        return ResponseEntity.ok(productDTO);
    }

    /**
     * POST /api/products : Create a new product.
     *
     * @param productDTO the productDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productDTO
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO result = productService.save(productDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * PUT /api/products : Updates an existing product.
     *
     * @param productDTO the productDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productDTO.setId(id);
        ProductDTO result = productService.update(productDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * DELETE /api/products/{id} : delete the "id" product.
     *
     * @param id the id of the product to delete
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
