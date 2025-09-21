package sn.bacomputer.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sn.bacomputer.DTO.CategoryDTO;
import sn.bacomputer.entity.Category;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T18:46:02+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );

        return categoryDTO;
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( dto.getId() );
        category.setName( dto.getName() );

        return category;
    }
}
