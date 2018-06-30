package com.vauban.vaubancommerce.controller;

import static com.vauban.vaubancommerce.utils.StaticStrings.ID;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vauban.vaubancommerce.exception.MandatoryField;
import com.vauban.vaubancommerce.exception.NotFoundEntity;
import com.vauban.vaubancommerce.model.Product;
import com.vauban.vaubancommerce.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping( "/product/{productId}" )
	public Product getProduct( @PathVariable Long productId ) throws NotFoundEntity {
		Optional<Product> response = productRepository.findById( productId );
		if ( response.isPresent() ) {
			return response.get();
		} else {
			throw new NotFoundEntity( ID, String.valueOf( productId ) );
		}
	}

	@PostMapping( "/product" )
	public ResponseEntity<Object> postProduct( @Validated @RequestBody Product product ) throws MandatoryField {
		return new ResponseEntity<Object>( productRepository.save( product ), HttpStatus.OK );
	}

	@PutMapping( "/product" )
	public Product putProduct( @Validated @RequestBody Product product ) throws NotFoundEntity {
		if ( product.getId() == null ) {
			throw new NotFoundEntity( ID, String.valueOf( product.getId() ) );
		}
		return productRepository.save( product );
	}

	@PutMapping( "/product/add/{productId}" )
	public Product addProductStock( @PathVariable Long productId, @RequestParam int amount ) throws NotFoundEntity {
		Optional<Product> response = productRepository.findById( productId );
		if ( !response.isPresent() ) {
			throw new NotFoundEntity( ID, String.valueOf( productId ) );
		}
		Product product = response.get();
		product.setAmount( product.getAmount() + amount );
		return productRepository.save( product );
	}

	@PutMapping( "/product/remove/{productId}" )
	public Product removeProductStock( @PathVariable Long productId, @RequestParam int amount ) throws NotFoundEntity {
		Optional<Product> response = productRepository.findById( productId );
		if ( !response.isPresent() ) {
			throw new NotFoundEntity( ID, String.valueOf( productId ) );
		}
		Product product = response.get();
		product.setAmount( product.getAmount() - amount );
		return productRepository.save( product );
	}

	@DeleteMapping( "/product/{productId}" )
	public void deleteProduct( @PathVariable Long productId ) throws NotFoundEntity {
		Optional<Product> response = productRepository.findById( productId );
		if ( !response.isPresent() ) {
			throw new NotFoundEntity( ID, String.valueOf( productId ) );
		} else {
			Product product = response.get();
			product.setActive( false );
			productRepository.save( product );
		}
	}

}
