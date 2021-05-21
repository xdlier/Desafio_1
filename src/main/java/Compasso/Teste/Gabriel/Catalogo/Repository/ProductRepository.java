package Compasso.Teste.Gabriel.Catalogo.Repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Compasso.Teste.Gabriel.Catalogo.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAll();

	@Query("SELECT p FROM Product p WHERE price <= :max AND price >= :min AND (name LIKE %:q% OR description LIKE %:q%)")
	List<Product> search(BigDecimal min, BigDecimal max, String q);
}
