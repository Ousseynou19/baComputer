package sn.bacomputer.service;

import sn.bacomputer.entity.Product;
import sn.bacomputer.dto.ProductDTO;
import sn.bacomputer.mapper.ProductMapper;
import sn.bacomputer.repository.ProductRepository;
import sn.bacomputer.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;
    private List<Product> productList;
    private Long testId;

    @BeforeEach
    void setUp() {
        // Setup test data
        testId = 1L;

        product = new Product();
        // Set id directly on the entity using reflection or direct field access if needed
        // For test purposes, we'll mock the responses instead of relying on actual entity getter methods

        productDTO = new ProductDTO();
        productDTO.setId(testId);

        productList = new ArrayList<>();
        productList.add(product);
    }

    @Test
    void testSave() {
        // Arrange
        when(productMapper.toEntity(any(ProductDTO.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        // Act
        ProductDTO result = productService.save(productDTO);

        // Assert
        assertThat(result).isNotNull();
        verify(productRepository).save(any(Product.class));
        verify(productMapper).toDto(any(Product.class));
    }

    @Test
    void testFindAll() {
        // Arrange
        List<ProductDTO> dtoList = new ArrayList<>();
        dtoList.add(productDTO);

        when(productRepository.findAll()).thenReturn(productList);

        // Stub the individual entity mapping instead of the list mapping
        // This correctly handles the stream().map() call in the service
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        // Act
        List<ProductDTO> result = productService.findAll();

        // Assert
        assertThat(result).isNotNull().hasSize(1);
        verify(productRepository).findAll();
    }

    @Test
    void testFindOne() {
        // Arrange
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        // Act
        ProductDTO result = productService.findOne(testId);

        // Assert
        assertThat(result).isNotNull();
        verify(productRepository).findById(any());
    }

    @Test
    void testDelete() {
        // Arrange
        doNothing().when(productRepository).deleteById(any());

        // Act
        productService.delete(testId);

        // Assert
        verify(productRepository).deleteById(any());
    }
}
