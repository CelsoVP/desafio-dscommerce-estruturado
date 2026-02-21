package br.com.celsinhovp.ApiDscommerce.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.celsinhovp.ApiDscommerce.dto.OrderDTO;
import br.com.celsinhovp.ApiDscommerce.dto.OrderItemDTO;
import br.com.celsinhovp.ApiDscommerce.entities.Order;
import br.com.celsinhovp.ApiDscommerce.entities.OrderItem;
import br.com.celsinhovp.ApiDscommerce.entities.OrderStatus;
import br.com.celsinhovp.ApiDscommerce.entities.Product;
import br.com.celsinhovp.ApiDscommerce.entities.User;
import br.com.celsinhovp.ApiDscommerce.repositories.OrderItemRepository;
import br.com.celsinhovp.ApiDscommerce.repositories.OrderRepository;
import br.com.celsinhovp.ApiDscommerce.repositories.ProductRepository;
import br.com.celsinhovp.ApiDscommerce.service.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto){

        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for(OrderItemDTO itemDto : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order,product,itemDto.getQuantity(),product.getPrice());
            order.getItems().add(item);
        }
        repository.save(order);
        orderItemRepository.saveAll(order.getItems());
        return new OrderDTO(order);
    }
}
