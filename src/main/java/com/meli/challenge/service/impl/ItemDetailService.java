package com.meli.challenge.service.impl;

import com.meli.challenge.feign.api.ItemDetailFeignClient;
import com.meli.challenge.feign.response.ItemDetailsResponseDto;
import com.meli.challenge.service.api.IItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ItemDetailService implements IItemDetailService {

	/**
	 * Feign client to perform call to external api
	 */
	private final ItemDetailFeignClient feignClient;

	@Autowired
	public ItemDetailService(ItemDetailFeignClient feignClient) {
		this.feignClient = feignClient;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable(
		value = "itemsCache",
		key = "{#itemId}",
		unless = "#result == null"
	)
	public ItemDetailsResponseDto getItemDetail(String itemId) {
		return feignClient.getItemDetails(itemId);
	}
}
