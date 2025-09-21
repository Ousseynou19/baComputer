package sn.bacomputer.mapper;

import org.mapstruct.Mapper;
import sn.bacomputer.DTO.ProductDTO;
import sn.bacomputer.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO dto);
}

