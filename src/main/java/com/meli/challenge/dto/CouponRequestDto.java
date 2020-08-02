package com.meli.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Coupon process request model
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class CouponRequestDto implements Serializable {

	private static final long serialVersionUID = 198673341731329908L;

	/**
	 * Customer favorite items ids
	 */
	@ApiModelProperty(
		value = "List of items ids in coupon",
		example = "[\"MCO465334695\",\"MCO540428115\"]"
	)
	@JsonProperty("item_ids")
	private List<String> items;

	/**
	 * Available amount to spend
	 */
	@ApiModelProperty(
		value = "Available amount to spend",
		example = "1000.22"
	)
	private Float amount;

}
