
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
@ApiModel
public class CouponResponseDto implements Serializable {

	private static final long serialVersionUID = 58647814310844464L;

	/**
	 * List of favorites customer items ids to redeem coupon
	 */
	@ApiModelProperty(
		value = "List of items ids to match coupon criteria",
		example = "[\"MCO465334695\",\"MCO540428115\"]"
	)
	@JsonProperty("item_ids")
	private List<String> itemId;

	/**
	 * Total spend amount
	 */
	@ApiModelProperty(
		value = "Total spend amount",
		example = "1882.80"
	)
	private Float total;
}
