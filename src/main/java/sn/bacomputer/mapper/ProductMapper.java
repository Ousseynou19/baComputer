package sn.bacomputer.mapper;

import sn.bacomputer.entity.Product;
import sn.bacomputer.dto.ProductDTO;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDto(Product entity);

    List<ProductDTO> toDto(List<Product> entityList);

    Set<ProductDTO> toDto(Set<Product> entityList);

    Product toEntity(ProductDTO entityDTO);

    List<Product> toEntity(List<ProductDTO> dtoList);

    Set<Product> toEntity(Set<ProductDTO> dtoList);

    /**
     * Partial update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Product entity, ProductDTO dto);
}
