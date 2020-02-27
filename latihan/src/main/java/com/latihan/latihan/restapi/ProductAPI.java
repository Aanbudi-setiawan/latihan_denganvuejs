package com.latihan.latihan.restapi;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(name = "/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductAPI {
	private ProductService productService;
	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProductAPI.class);
	
	@GetMapping
	public ResponseEntity<List<Product>> temukanSemua(){
		return ResponseEntity.ok(productService.temukanSemua());
	}
	
	@PostMapping
	public ResponseEntity create(@Valid @RequestBody Product product) {
		return ResponseEntity.ok(productService.simpan(product));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> temukanDenganId(@PathVariable Long id)
	{
		Optional<Product> stock = productService.temukanSesuaiId(id);
		if (!stock.isPresent()) {
			log.error("Id "+id +" is not existed");
			ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(stock.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product){
		if (!productService.temukanSesuaiId(id).isPresent()) {
			log.error("id "+id +" is not existed");
			ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(productService.simpan(product));
	}
	
	public ResponseEntity delete(@PathVariable Long id) {
		if (!productService.temukanSesuaiId(id).isPresent()) {
			log.error("Id "+id +" is not existed");
			ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
}
