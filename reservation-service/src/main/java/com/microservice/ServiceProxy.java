package com.microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.dto.BusRouteDto;
import com.microservice.pojo.BusRoute;
import com.microservice.pojo.Login;
import com.microservice.pojo.Registration;
import com.microservice.pojo.UpdateWallet;

//@FeignClient(name="search-service", url = "localhost:8200") 
@FeignClient(name="netflix-zuul-api-gateway-server")
public interface ServiceProxy {

	@PostMapping("/registration-service/getRegisteredUser")
	public ResponseEntity<Registration> getRegisteredUser(@RequestBody Login login);
	
	@PostMapping("/search-service/getBusroute")
	public ResponseEntity<BusRoute> getBus(@RequestBody BusRouteDto getBusRoute);

	@PostMapping("/registration-service/withdraw")
	public ResponseEntity<String> withdraw(@RequestBody UpdateWallet getBusRoute);
	
	
	@PostMapping("/registration-service/addtowallet")
	public ResponseEntity<String> addToWallet(@RequestBody UpdateWallet getBusRoute);
}