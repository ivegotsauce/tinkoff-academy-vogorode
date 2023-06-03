package ru.academy.tinkoff.landscape.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.academy.tinkoff.landscape.domain.Order;
import ru.academy.tinkoff.landscape.dto.OrderDTO;
import ru.academy.tinkoff.landscape.dto.OrderOutput;
import ru.academy.tinkoff.landscape.rancher.Util;
import ru.academy.tinkoff.landscape.service.OrderService;

@RestController
@AllArgsConstructor
@RequestMapping("order")
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public OrderOutput createOrder(@RequestBody OrderDTO dto) {
        return orderToOutput(orderService.createOrder(dto));
    }

    @PutMapping("/{id}")
    public OrderOutput updateOrder(@PathVariable Long id, @RequestBody OrderDTO dto) {
        return orderToOutput(orderService.updateOrder(id, dto));
    }

    @GetMapping("/{id}")
    public OrderOutput getOrder(@PathVariable Long id) {
        return orderToOutput(orderService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @GetMapping
    public Page<OrderOutput> getPaginated(@RequestParam Integer page, @RequestParam Integer size) {
        if (page < 0 || size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid page number or size");
        }
        return orderService.findAll(page, size).map(this::orderToOutput);
    }

    private OrderOutput orderToOutput(Order order) {
        return new OrderOutput(
                order.getId(),
                Util.fieldToProjection(order.getField()),
                order.getUser(),
                order.getWorkType().name(),
                order.getWorkStatus().name(),
                order.getCreationDate()
        );
    }
}
