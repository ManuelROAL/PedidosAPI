package com.pedidosapi.runner;

import com.pedidosapi.entity.Order;
import com.pedidosapi.entity.OrderStatus;
import com.pedidosapi.entity.OrderTracking;
import com.pedidosapi.entity.User;
import com.pedidosapi.repository.OrderRepository;
import com.pedidosapi.repository.OrderStatusRepository;
import com.pedidosapi.repository.OrderTrackingRepository;
import com.pedidosapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.datafaker.Faker;
import net.datafaker.Options;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
@Log4j2
public class DatabaseSeeder {

    private final String MESSAGE = "El pedido se encuentra en ";

    private final Faker faker;

    private final OrderRepository orderRepository;

    private final OrderStatusRepository orderStatusRepository;

    private final OrderTrackingRepository orderTrackingRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void seed() {
        log.info("Insertando datos de prueba...");
        userFactory(10);
        orderFactory(50);
        orderTrackingFactory(100);
        log.info("Proceso finalizado.");
    }

    public void fresh() {
        log.info("Eliminando todos los datos...");
        this.orderRepository.deleteAll();
        this.orderTrackingRepository.deleteAll();
        this.userRepository.deleteAll();
        log.info("Proceso finalizado.");
    }

    private void userFactory(int count) {
        log.info("Insertando registros User...");
        run(count, () -> {
            User user = new User();
            String username = this.faker.name()
                    .username();

            user.setEmail(this.faker.internet()
                    .emailAddress());
            user.setUsername(username);
            user.setPassword(this.passwordEncoder.encode(username));
            user.setName(this.faker.name().name());
            user.setLastname(this.faker.name().lastName() + " " +
                    this.faker.name().lastName());

            this.userRepository.save(user);
        });
    }

    private void orderFactory(int count) {
        log.info("Insertando registros Order...");
        Iterable<User> userIterable = this.userRepository.findAll();
        List<User> userList = StreamSupport.stream(userIterable.spliterator(), false)
                .toList();
        Iterable<OrderStatus> orderStatusIterable = this.orderStatusRepository.findAll();
        List<OrderStatus> orderStatusList = StreamSupport.stream(orderStatusIterable.spliterator(), false)
                .toList();
        Options options = this.faker.options();
        run(count, () -> {
            Order order = new Order();
            order.setUser(options.nextElement(userList));
            order.setOrderStatus(options.nextElement(orderStatusList));

            this.orderRepository.save(order);
        });
    }

    private void orderTrackingFactory(int count) {
        log.info("Insertando registros OrderTracking...");
        Iterable<Order> orderIterable = this.orderRepository.findAll();
        List<Order> orderList = StreamSupport.stream(orderIterable.spliterator(), false)
                .toList();
        Iterable<OrderStatus> orderStatusIterable = this.orderStatusRepository.findAll();
        List<OrderStatus> orderStatusList = StreamSupport.stream(orderStatusIterable.spliterator(), false)
                .toList();
        Options options = this.faker.options();
        run(count, () -> {
            OrderTracking orderTracking = new OrderTracking();

            orderTracking.setDescription(MESSAGE + this.faker.address().fullAddress());
            orderTracking.setDate(this.faker.date().birthday().toInstant());
            orderTracking.setOrder(options.nextElement(orderList));
            orderTracking.setOrderStatus(options.nextElement(orderStatusList));

            this.orderTrackingRepository.save(orderTracking);
        });
    }

    private void run(int count, Runnable runnable) {
        for (int i = 0; i < count; i++) {
            runnable.run();
        }
    }

}
