package sn.bacomputer.service.Impl;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import sn.bacomputer.DTO.ProductDTO;
import sn.bacomputer.entity.Category;
import sn.bacomputer.entity.Product;
import sn.bacomputer.mapper.ProductMapper;
import sn.bacomputer.repository.CategoryRepository;
import sn.bacomputer.repository.ProductRepository;
import sn.bacomputer.service.ProductService;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;


    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ProductDTO create(ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        if (dto.getCategoryId() != null) {
            Category c = categoryRepository.findById(dto.getCategoryId()).orElse(null);
            product.setCategory(c);
        }
        return productMapper.toDTO(productRepository.save(product));
    }


    @Override
    public List<ProductDTO> listAll() {
        return productRepository.findAll().stream().map(productMapper::toDTO).toList();
    }


    @Override
    public ProductDTO get(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toDTO(product);
    }
    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));


        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());


        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }


        return productMapper.toDTO(productRepository.save(product));
    }


    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}


