package com.meli.challenge.service.api;

import com.meli.challenge.feign.response.ItemDetailsResponseDto;

public interface IItemDetailService {

	ItemDetailsResponseDto getItemDetail(String itemId);

}
