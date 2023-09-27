package hunre.it.repository;

import hunre.it.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  boolean existsByName(String name);

  boolean existsById(Integer id);

  boolean existsByNameAndIdNot(String name, Integer id);
}
