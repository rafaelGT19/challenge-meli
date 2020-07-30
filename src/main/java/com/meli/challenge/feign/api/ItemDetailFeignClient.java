package com.meli.challenge.feign.api;

import com.meli.challenge.feign.response.ItemDetailsResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://api.mercadolibre.com", name = "item-details")
public interface ItemDetailFeignClient {

	@GetMapping("/items/{itemId}")
	ItemDetailsResponseDto getItemDetails(@PathVariable("itemId") String itemId);

}
