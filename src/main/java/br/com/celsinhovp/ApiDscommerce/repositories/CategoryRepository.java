package br.com.celsinhovp.ApiDscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.celsinhovp.ApiDscommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
