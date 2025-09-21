package sn.bacomputer.service.Impl;

import org.springframework.stereotype.Service;
import sn.bacomputer.DTO.CategoryDTO;
import sn.bacomputer.entity.Category;
import sn.bacomputer.mapper.CategoryMapper;
import sn.bacomputer.repository.CategoryRepository;
import sn.bacomputer.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }


    @Override
    public List<CategoryDTO> listAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDTO).toList();
    }


    @Override
    public CategoryDTO get(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toDTO).orElseThrow(() -> new RuntimeException("Category not found"));
    }


    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
