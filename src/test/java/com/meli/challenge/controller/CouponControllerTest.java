package com.meli.challenge.controller;

import static com.meli.challenge.util.Sample.getControllerRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.meli.challenge.dto.CouponRequestDto;
import com.meli.challenge.dto.CouponResponseDto;
import com.meli.challenge.service.impl.CouponService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test class {@link CouponController}
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class CouponControllerTest {

	@Mock
	private CouponService service;

	@InjectMocks
	private CouponController controller;

	@Test
	void givenCouponRequest_whenProcessCoupon_thenReturnsSuccessResponds() {
		CouponRequestDto requestDto = getControllerRequest();
		CouponResponseDto responseDto = CouponResponseDto.builder().build();

		when(service.processCoupon(requestDto)).thenReturn(responseDto);

		ResponseEntity<CouponResponseDto> result = controller.processCoupon(requestDto);

		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

	}
}
