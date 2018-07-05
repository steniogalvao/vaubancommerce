package com.vauban.vaubancommerce.controller;

import static com.vauban.vaubancommerce.utils.StaticStrings.ID;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vauban.vaubancommerce.exception.InvalidAmountException;
import com.vauban.vaubancommerce.exception.MandatoryField;
import com.vauban.vaubancommerce.exception.NotFoundEntity;
import com.vauban.vaubancommerce.model.Product;
import com.vauban.vaubancommerce.model.Purchase;
import com.vauban.vaubancommerce.repository.ProductRepository;
import com.vauban.vaubancommerce.repository.PurchaseRepository;
import com.vauban.vaubancommerce.repository.UserRepository;

@RestController
public class PurchaseController {

	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;

	@GetMapping( "/purchase/{purchaseId}" )
	public Purchase getPurchase( @PathVariable Long purchaseId ) throws NotFoundEntity {
		Optional<Purchase> response = purchaseRepository.findById( purchaseId );
		if ( response.isPresent() ) {
			return response.get();
		} else {
			throw new NotFoundEntity( ID, String.valueOf( purchaseId ) );
		}
	}

	@GetMapping( "/purchases/{userId}" )
	public List<Purchase> getPurchasesByUsr( @PathVariable Long userId ) throws NotFoundEntity {
		return purchaseRepository.findAllByBuyer( userRepository.findById( userId ).get() );
	}

	@PostMapping( "/purchase" )
	public Purchase postPurchase( @Validated @RequestBody Purchase purchase ) throws MandatoryField, InvalidAmountException {
		purchase.getItens().stream().forEach( item -> {
			Product product = productRepository.findById( item.getProductId() ).get();
			if ( item.getAmount() > product.getAmount() ) {
				item.setAmount( 0 );
			} else {
				product.setAmount( product.getAmount() - item.getAmount() );
				productRepository.save( product );
			}
		} );
		if ( purchase.getItens().stream().filter( item -> item.getAmount() == 0 ).findAny().isPresent() ) {
			throw new InvalidAmountException();
		}
		purchase.setPurchaseDate( LocalDateTime.now() );
		purchase.setTotalPrice( purchase.getItens().stream().mapToDouble( item -> {
			Product product = productRepository.findById( item.getProductId() ).get();
			return product.getPrice() * item.getAmount();
		} ).sum() );
		return purchaseRepository.save( purchase );
	}

	@DeleteMapping( "/purchase/{purchaseId}" )
	public void deletePurchase( @PathVariable Long purchaseId ) throws NotFoundEntity {
		Optional<Purchase> response = purchaseRepository.findById( purchaseId );
		if ( !response.isPresent() ) {
			throw new NotFoundEntity( ID, String.valueOf( purchaseId ) );
		} else {
			Purchase purchase = response.get();
			purchase.setCanceled( true );
			purchase.getItens().stream().forEach( item -> {
				Product product = productRepository.findById( item.getProductId() ).get();
				product.setAmount( product.getAmount() + item.getAmount() );
				productRepository.save( product );
			} );
			purchaseRepository.save( purchase );
		}
	}

}
