package sn.bacomputer.service;

import sn.bacomputer.DTO.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO create(ProductDTO dto);

    List<ProductDTO> listAll();

    ProductDTO get(Long id);

    ProductDTO update(Long id, ProductDTO dto);

    void delete(Long id);
}
