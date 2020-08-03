
package com.meli.challenge.feign.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class ItemDetailsResponseDto implements Serializable {

	private static final long serialVersionUID = 4412282835060415937L;
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
