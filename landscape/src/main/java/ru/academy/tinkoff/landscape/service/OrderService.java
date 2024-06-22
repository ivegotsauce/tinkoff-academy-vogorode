package ru.academy.tinkoff.landscape.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.academy.tinkoff.landscape.domain.Order;
import ru.academy.tinkoff.landscape.domain.WorkStatus;
import ru.academy.tinkoff.landscape.domain.WorkType;
import ru.academy.tinkoff.landscape.dto.OrderDTO;
import ru.academy.tinkoff.landscape.handyman.HandymanUserService;
import ru.academy.tinkoff.landscape.rancher.FieldService;
import ru.academy.tinkoff.landscape.repository.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private HandymanUserService handymanUserService;
    private FieldService fieldService;

    public Order createOrder(OrderDTO dto) {
        Order order = new Order();
        dtoToOrder(dto, order);
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, OrderDTO dto) {
        Order order = findById(id);
        dtoToOrder(dto, order);
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No order with id " + id)
        );
    }

    public Page<Order> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return orderRepository.findAll(pageRequest);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    private void dtoToOrder(OrderDTO dto, Order order) {
        try {
            order.setWorkType(WorkType.valueOf(dto.workType()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid work type provided " + dto.workType());
        }
        try {
            order.setWorkStatus(WorkStatus.valueOf(dto.workStatus()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid work status provided " + dto.workStatus());
        }
        order.setUser(handymanUserService.findById(dto.userId()));
        order.setField(fieldService.findById(dto.gardenId()));
    }
}
