package com.microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.dto1.BusRoute;
import com.microservice.dto1.Deposit;
import com.microservice.dto1.GetBusRoute;
import com.microservice.dto1.GetDeleteBusRoute;
import com.microservice.dto1.Login;
import com.microservice.dto1.Registration;

//@FeignClient(name="search-service", url = "localhost:8200") 
@FeignClient(name="netflix-zuul-api-gateway-server")
public interface ServiceProxy {
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
//	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
//	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

	@PostMapping("/search-service/getBusroute")
	public ResponseEntity<BusRoute> getBus(@RequestBody GetDeleteBusRoute getBusRoute);
	

	@PostMapping("/search-service/get/BusRoute")
	public ResponseEntity<BusRoute> getBus(@RequestBody GetBusRoute getBusRoute);
	
	@GetMapping("/search")
	public String searchS();
	
	@PostMapping("/registration-service/getRegisteredUser")
	public ResponseEntity<Registration> getRegisteredUser(@RequestBody Login login);
	
	@PostMapping("/registration-service/add")
	public ResponseEntity<String> depositMoney(@RequestBody Deposit deposit);

}