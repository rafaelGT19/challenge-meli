package com.meli.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class to represents item information to process coupon
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

	/**
	 * Item id
	 */
	private String id;

	/**
	 * Item price
	 */
	private Float price;

}
