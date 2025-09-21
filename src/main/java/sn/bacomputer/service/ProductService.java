package sn.bacomputer.service;

import sn.bacomputer.dto.ProductDTO;

import java.util.List;

/**
 * Service Interface for managing {@link sn.bacomputer.entity.Product}.
 */
public interface ProductService {

    /**
     * Save a product.
     *
     * @param productDTO the entity to save.
     * @return the persisted entity.
     */
    ProductDTO save(ProductDTO productDTO);

    /**
     * Updates a product.
     *
     * @param productDTO the entity to update.
     * @return the persisted entity.
     */
    ProductDTO update(ProductDTO productDTO);

    /**
     * Get all the products.
     *
     * @return the list of entities.
     */
    List<ProductDTO> findAll();

    /**
     * Get the "product" by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    ProductDTO findOne(Long id);

    /**
     * Delete the "product" by id.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
