package sn.bacomputer.controller;

import org.springframework.web.bind.annotation.*;
import sn.bacomputer.DTO.CategoryDTO;
import sn.bacomputer.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public CategoryDTO create(@RequestBody CategoryDTO dto) {
        return categoryService.create(dto);
    }


    @GetMapping
    public List<CategoryDTO> listAll() {
        return categoryService.listAll();
    }


    @GetMapping("/{id}")
    public CategoryDTO get(@PathVariable Long id) {
        return categoryService.get(id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
