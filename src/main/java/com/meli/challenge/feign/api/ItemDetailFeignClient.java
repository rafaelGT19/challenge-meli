package com.meli.challenge.feign.api;

import com.meli.challenge.feign.response.ItemDetailsResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client to consume ML api to retrieve item details
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@FeignClient(url = "https://api.mercadolibre.com", name = "item-details")
public interface ItemDetailFeignClient {

	/**
	 * Retrieve details for specified item
	 * @param itemId item id to retrieve details
	 * @return product detail information
	 */
	@GetMapping("/items/{itemId}")
	ItemDetailsResponseDto getItemDetails(@PathVariable("itemId") String itemId);

}
