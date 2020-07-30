
package com.meli.challenge.feign.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailsResponseDto {

	private String id;

	private String title;

	private Float price;

}
