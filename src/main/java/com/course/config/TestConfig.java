package com.course.config;

import com.course.model.Category;
import com.course.model.Order;
import com.course.model.User;
import com.course.model.enums.OrderStatus;
import com.course.repository.CategoryRepository;
import com.course.repository.OrderRepository;
import com.course.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;

    public TestConfig(UserRepository userRepository, OrderRepository orderRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Category category1 = new Category("Electronics");
        Category category2 = new Category("Books");
        Category category3 = new Category("Computers");
        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));

        User u1 = new User("Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User("Alex Green", "alex@gmail.com", "977777777", "123456");
        userRepository.saveAll(Arrays.asList(u1, u2));

        Order o1 = new Order(Instant.parse("2025-06-20T19:53:07Z"), OrderStatus.PAID,u1);
        Order o2 = new Order(Instant.parse("2025-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(Instant.parse("2025-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
        orderRepository.saveAll(Arrays.asList(o1,o2, o3));
    }
}
