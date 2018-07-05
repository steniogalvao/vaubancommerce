package com.vauban.vaubancommerce.controller;

import static com.vauban.vaubancommerce.utils.StaticStrings.ID;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

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

import com.vauban.vaubancommerce.exception.InvalidAmountException;
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

	@GetMapping( "/products" )
	public List<Product> getAllProducts() throws NotFoundEntity {
		List<Product> products = (List<Product>) productRepository.findAll();
		return products;
	}

	@GetMapping( "/products/active" )
	public List<Product> getAllProductsActive() throws NotFoundEntity {
		List<Product> products = (List<Product>) productRepository.findAll();
		products = products.stream().filter( Product::isActive ).collect( Collectors.toList() );
		return products;
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
	public Product removeProductStock( @PathVariable Long productId, @RequestParam int amount ) throws NotFoundEntity,InvalidAmountException {
		Optional<Product> response = productRepository.findById( productId );
		if ( !response.isPresent() ) {
			throw new NotFoundEntity( ID, String.valueOf( productId ) );
		}
		Product product = response.get();
		if(product.getAmount()<amount) {
			throw new InvalidAmountException();
		}
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

	@PostConstruct
	private void startProductPack() {
		Product p1 = new Product( "TV", "A really nice TV, big screen full of collors and so bright", Double.valueOf( 999 ), 15, true );
		Product p2 = new Product( "Trash", "Some trashcan", Double.valueOf( 60 ), 1, true );
		Product p3 = new Product( "Monitor", "For games of job is always good have a big monitor", Double.valueOf( 500 ), 5, true );
		Product p4 = new Product( "Notebook", "fast, durable, portable, just buy and start to have fun!", Double.valueOf( 1200 ), 2, true );
		Product p5 = new Product( "in-ear headset", "Stay out of the world, or inside your world! this headset will isolate you to stay focus", Double.valueOf( 20 ), 99, true );
		Product p6 = new Product( "GoPro", "register all your moments with this awesome camera!", Double.valueOf( 499 ), 3, false );
		productRepository.save( p1 );
		productRepository.save( p2 );
		productRepository.save( p3 );
		productRepository.save( p4 );
		productRepository.save( p5 );
		productRepository.save( p6 );
	}

}
