package Compasso.Teste.Gabriel.Catalogo.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Compasso.Teste.Gabriel.Catalogo.Model.Product;
import Compasso.Teste.Gabriel.Catalogo.Repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductRepository repository;

	@PostMapping
	public ResponseEntity<Product> create(String name, String description, String price) {

		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(new BigDecimal(price));

		repository.save(product);
		return ResponseEntity.ok(product);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> edit(@PathVariable(name = "id") String id, String name, String description,
			String price) {

		Optional<Product> optional = repository.findById(Long.valueOf(id));
		Product product = optional.get();
		if (name != null)
			product.setName(name);
		if (description != null)
			product.setDescription(description);
		if (price != null)
			product.setPrice(new BigDecimal(price));

		repository.save(product);
		return ResponseEntity.ok(product);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable(name = "id") String id) {

		Optional<Product> optional = repository.findById(Long.valueOf(id));
		Product product = optional.get();
		return ResponseEntity.ok(product);
	}

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {

		List<Product> products = repository.findAll();
		return ResponseEntity.ok(products);

	}

	@GetMapping("/search")
	public ResponseEntity<List<Product>> search(
			@RequestParam(name = "min_price", required = false, defaultValue = "0.0") BigDecimal min_price,
			@RequestParam(name = "max_price", required = false, defaultValue = "1000000.0") BigDecimal max_price,
			@RequestParam(name = "q", required = false, defaultValue = "") String q) {

		List<Product> products = repository.search(min_price, max_price, q);
		return ResponseEntity.ok(products);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable(name = "id") String id) {

		Optional<Product> optional = repository.findById(Long.valueOf(id));
		repository.deleteById(optional.get().getId());
		return ResponseEntity.ok().build();

	}

}
