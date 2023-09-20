package hunre.it.controller;

import hunre.it.model.Product;
import hunre.it.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
  public List<?> list() {
    return productService.listAll();
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
    Optional<?> product = productService.findById(id);
    return ResponseEntity.ok(product);
  }

  @PostMapping("/products")
  public ResponseEntity<?> createNewProduct(@RequestBody Product product) {

    return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
    Product updatedProduct = productService.updateProduct(id, product);
    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    productService.delete(id);
    return new ResponseEntity<>("Đã xóa thành công", HttpStatus.OK);
  }
}
