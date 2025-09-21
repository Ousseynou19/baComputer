package sn.bacomputer.mapper;

import org.mapstruct.Mapper;
import sn.bacomputer.DTO.CategoryDTO;
import sn.bacomputer.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO dto);
}
