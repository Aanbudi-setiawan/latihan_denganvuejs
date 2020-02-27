package com.latihan.latihan.restapi;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor
public class ProductService {
	private ProductRepository productRepository;
	
	// menampilkan semua data
	public List<Product> temukanSemua(){
		return productRepository.findAll();
	}
	
	//temukan berdasarkan Id
	
	public Optional<Product> temukanSesuaiId(Long id){
		return productRepository.findById(id);
	}
	
	//menyimpan data
	public Product simpan(Product product) {
		return productRepository.save(product);
	}
	
	public void hapusDenganId(Long id) {
		productRepository.deleteById(id);
	}
	
}
