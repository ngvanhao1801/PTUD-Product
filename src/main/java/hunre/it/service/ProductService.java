package hunre.it.service;

import java.util.List;
import java.util.Optional;

import hunre.it.exception.DuplicateException;
import hunre.it.model.Product;
import hunre.it.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hunre.it.exception.NotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> listAll() {
		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			throw new NotFoundException("Product not found");
		}
		return products;
	}

	public Optional<?> findById(Integer id) {
		Optional<Product> product = productRepository.findById(id);
		if (!product.isPresent()) {
			throw new NotFoundException("Không tìm thấy product với id: " + id);
		}
		return product;
	}

	public Product save(Product product) {
		if (productRepository.existsByName(product.getName())) {
			throw new NotFoundException("Tên product đã tồn tại");
		}
		return productRepository.save(product);
	}

	public Product updateProduct(Integer id, Product product) {
		Optional<Product> existingProduct = productRepository.findById(id);
		if (!existingProduct.isPresent()) {
			throw new NotFoundException("Không tìm thấy product với id: " + id);
		}
		if (productRepository.existsByNameAndIdNot(product.getName(), id)) {
			throw new DuplicateException("Tên xe đã tồn tại");
		}
		return productRepository.save(product);
	}

	public void delete(Integer id) {
		if (!productRepository.existsById(id)) {
			throw new NotFoundException("Không tìm thấy product với id: " + id);
		}
		productRepository.deleteById(id);
	}
}
