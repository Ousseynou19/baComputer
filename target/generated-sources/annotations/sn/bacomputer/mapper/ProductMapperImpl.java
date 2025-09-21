package sn.bacomputer.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sn.bacomputer.DTO.ProductDTO;
import sn.bacomputer.entity.Product;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T18:46:02+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setStock( product.getStock() );

        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( dto.getId() );
        product.setName( dto.getName() );
        product.setDescription( dto.getDescription() );
        product.setPrice( dto.getPrice() );
        product.setStock( dto.getStock() );

        return product;
    }
}
