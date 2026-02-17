package br.com.celsinhovp.ApiDscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.celsinhovp.ApiDscommerce.entities.OrderItem;
import br.com.celsinhovp.ApiDscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
