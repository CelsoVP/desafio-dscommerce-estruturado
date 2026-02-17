package br.com.celsinhovp.ApiDscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.celsinhovp.ApiDscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
