package hac.ex4;

import hac.ex4.model.Product;
import hac.ex4.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
public class Ex4Application {

    private static final Logger log = LoggerFactory.getLogger(Ex4Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Ex4Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductRepository repository) {
        return (args) -> {
            Product product = new Product();
            product.setDiscount(12);
            product.setDiscount(12);
            product.setPrice(12);
            product.setName("Bread");
            product.setQuantity(12);
            log.error(repository.save(product).toString());
        };
    }
}
