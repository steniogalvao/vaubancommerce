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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vauban.vaubancommerce.exception.MandatoryField;
import com.vauban.vaubancommerce.exception.NotFoundEntity;
import com.vauban.vaubancommerce.model.Purchase;
import com.vauban.vaubancommerce.repository.PurchaseRepository;

@RestController
public class PurchaseController {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@GetMapping( "/purchase/{purchaseId}" )
	public Purchase getPurchase( @PathVariable Long purchaseId ) throws NotFoundEntity {
		Optional<Purchase> response = purchaseRepository.findById( purchaseId );
		if ( response.isPresent() ) {
			return response.get();
		} else {
			throw new NotFoundEntity( ID, String.valueOf( purchaseId ) );
		}
	}

	@PostMapping( "/purchase" )
	public ResponseEntity<Object> postPurchase( @Validated @RequestBody Purchase purchase ) throws MandatoryField {
		return new ResponseEntity<Object>( purchaseRepository.save( purchase ), HttpStatus.OK );
	}

	@DeleteMapping( "/purchase/{purchaseId}" )
	public void deletePurchase( @PathVariable Long purchaseId ) throws NotFoundEntity {
		Optional<Purchase> response = purchaseRepository.findById( purchaseId );
		if ( !response.isPresent() ) {
			throw new NotFoundEntity( ID, String.valueOf( purchaseId ) );
		} else {
			Purchase purchase = response.get();
			purchase.setCanceled( true );
			purchaseRepository.save( purchase );
		}
	}

}
