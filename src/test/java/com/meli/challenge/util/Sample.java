package com.meli.challenge.util;

import com.meli.challenge.dto.CouponRequestDto;
import java.util.Arrays;

/**
 * Class to provide mocks objects for test
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
public class Sample {

	public static CouponRequestDto getControllerRequest() {

		return CouponRequestDto.builder()
			.items(Arrays.asList("MCO1","MCO2"))
			.amount(100F)
			.build();
	}

}
