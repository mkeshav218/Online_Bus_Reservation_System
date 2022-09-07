package com.microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.pojo.Login;
import com.microservice.pojo.Registration;

//@FeignClient(name="search-service", url = "localhost:8200") 
@FeignClient(name="netflix-zuul-api-gateway-server")
public interface ServiceProxy {
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
//	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
//	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

	@PostMapping("/registration-service/getRegisteredUser")
	public ResponseEntity<Registration> getRegisteredUser(@RequestBody Login login);
	

}