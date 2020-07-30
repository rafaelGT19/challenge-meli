package com.meli.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Coupon process request data
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponRequestDto implements Serializable {

	private static final long serialVersionUID = 198673341731329908L;

	@JsonProperty("item_ids")
	private List<String> items;

	private Float amount;

}
