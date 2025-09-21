package sn.bacomputer.service;

import sn.bacomputer.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO create(CategoryDTO dto);
    List<CategoryDTO> listAll();
    CategoryDTO get(Long id);
    void delete(Long id);
}
