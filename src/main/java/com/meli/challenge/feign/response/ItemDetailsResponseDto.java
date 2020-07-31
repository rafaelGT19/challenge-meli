
package com.meli.challenge.feign.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ML item detail response model
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailsResponseDto {

	/**
	 * Item id
	 */
	private String id;

	/**
	 * Item title
	 */
	private String title;

	/**
	 * Item price
	 */
	private Float price;

}
