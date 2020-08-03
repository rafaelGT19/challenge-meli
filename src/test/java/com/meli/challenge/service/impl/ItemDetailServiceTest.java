package com.meli.challenge.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.meli.challenge.feign.api.ItemDetailFeignClient;
import com.meli.challenge.feign.response.ItemDetailsResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test class {@link ItemDetailService}
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class ItemDetailServiceTest {

	@Mock
	private ItemDetailFeignClient feignClient;

	@InjectMocks
	private ItemDetailService service;

	@Test
	void givenItemId_whenGetItemDetail_thenReturnsDetailObject() {
		String itemId = "123";

		when(feignClient.getItemDetails(eq(itemId)))
			.thenReturn(ItemDetailsResponseDto.builder().build());

		ItemDetailsResponseDto responseDto = service.getItemDetail(itemId);

		assertThat(responseDto).isNotNull();
	}

}
