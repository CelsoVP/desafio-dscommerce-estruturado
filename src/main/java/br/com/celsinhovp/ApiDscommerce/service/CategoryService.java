package br.com.celsinhovp.ApiDscommerce.service;

import br.com.celsinhovp.ApiDscommerce.dto.CategoryDTO;
import br.com.celsinhovp.ApiDscommerce.entities.Category;
import br.com.celsinhovp.ApiDscommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryDTO> findAll() {
        List<Category> result = repository.findAll();
        return result.stream().map(x -> new CategoryDTO(x)).toList();
    }
}
