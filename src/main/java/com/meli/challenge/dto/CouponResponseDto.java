
package com.meli.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Coupon controller response class
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponResponseDto implements Serializable {

	private static final long serialVersionUID = 58647814310844464L;

	@JsonProperty("item_ids")
	private List<String> itemId;

	private Float total;
}
