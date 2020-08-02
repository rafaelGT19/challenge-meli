package com.meli.challenge.service.api;

import com.meli.challenge.feign.response.ItemDetailsResponseDto;

/**
 * Service to retrieve item detail information
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
public interface IItemDetailService {

	/**
	 * Retrieve item detail from external MeLi api. If the item was previously processed take
	 * the information from redis cache to avoid call external api.
	 * @param itemId item id
	 * @return
	 */
	ItemDetailsResponseDto getItemDetail(String itemId);

}
