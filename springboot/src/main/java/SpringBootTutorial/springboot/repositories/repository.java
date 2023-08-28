package SpringBootTutorial.springboot.repositories;

import SpringBootTutorial.springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface repository extends JpaRepository<Product,Long> {
    List<Product> findByname(String name);
}
