package com.meli.challenge.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.meli.challenge.dto.CouponRequestDto;
import com.meli.challenge.dto.CouponResponseDto;
import com.meli.challenge.exception.NotFoundResultsException;
import com.meli.challenge.feign.response.ItemDetailsResponseDto;
import com.meli.challenge.util.Sample;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test class {@link CouponService}
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

	@Mock
	private ItemDetailService itemDetailService;

	@Mock
	private ExecutorService executorService;

	@InjectMocks
	private CouponService service;

	@BeforeEach
	void setUp() {
		executorService = Executors.newFixedThreadPool(1);
		service = new CouponService(itemDetailService, executorService);
	}

	@Test
	void givenCouponRequestWithInsufficientAmountToBuyAnItem_whenProcessCoupon_thenExceptionIsRaised() {
		CouponRequestDto requestDto = Sample.getControllerRequest();
		requestDto.setAmount(1F);

		when(itemDetailService.getItemDetail(eq("MCO1")))
			.thenReturn(ItemDetailsResponseDto.builder().id("MCO1").price(50F).build());
		when(itemDetailService.getItemDetail(eq("MCO2")))
			.thenReturn(ItemDetailsResponseDto.builder().id("MCO2").price(40F).build());

		assertThatThrownBy(() -> service.processCoupon(requestDto))
			.isInstanceOf(NotFoundResultsException.class)
			.hasFieldOrPropertyWithValue("amount", 1F);
	}

	@Test
	void givenCouponRequestWithAmountToBuyAllItems_whenProcessCoupon_thenReturnsResponseWithAllItems() {
		CouponRequestDto requestDto = Sample.getControllerRequest();
		requestDto.setAmount(200F);

		when(itemDetailService.getItemDetail(eq("MCO1")))
			.thenReturn(ItemDetailsResponseDto.builder().id("MCO1").price(50F).build());
		when(itemDetailService.getItemDetail(eq("MCO2")))
			.thenReturn(ItemDetailsResponseDto.builder().id("MCO2").price(40F).build());

		CouponResponseDto responseDto = service.processCoupon(requestDto);

		assertThat(responseDto).isNotNull();
		assertThat(responseDto.getTotal()).isEqualTo(90F);
		assertThat(responseDto.getItemId()).containsSequence("MCO1","MCO2");
	}

	@Test
	void givenCouponRequestWithAmountToBuyOneItems_whenProcessCoupon_thenReturnsResponseWithItemId() {
		CouponRequestDto requestDto = Sample.getControllerRequest();
		requestDto.setAmount(60F);

		when(itemDetailService.getItemDetail(eq("MCO1")))
			.thenReturn(ItemDetailsResponseDto.builder().id("MCO1").price(50F).build());
		when(itemDetailService.getItemDetail(eq("MCO2")))
			.thenReturn(ItemDetailsResponseDto.builder().id("MCO2").price(400F).build());

		CouponResponseDto responseDto = service.processCoupon(requestDto);

		assertThat(responseDto).isNotNull();
		assertThat(responseDto.getTotal()).isEqualTo(50F);
		assertThat(responseDto.getItemId()).containsSequence("MCO1");
	}

}
