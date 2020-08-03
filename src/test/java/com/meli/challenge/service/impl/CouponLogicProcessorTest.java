package com.meli.challenge.service.impl;

import static com.meli.challenge.util.Sample.getItem;
import static com.meli.challenge.util.Sample.getItems;
import static org.assertj.core.api.Assertions.assertThat;

import com.meli.challenge.dto.ItemDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test class {@link CouponLogicProcessor}
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class CouponLogicProcessorTest {

	private CouponLogicProcessor processor;

	@BeforeEach
	void setUp() {
		processor = new CouponLogicProcessor();
	}

	@Test
	void givenEmptyInputList_whenGetSubsetWithMaxSum_thenReturnsNull() {
		List<ItemDto> inputList = new ArrayList<>();
		List<ItemDto> results = processor.getSubsetWithMaxSum(inputList, 1000F);

		assertThat(results).isNull();
	}

	@Test
	void givenValidInputListAndValidLimit_whenGetSubsetWithMaxSum_thenReturnsItemMatchCriteria() {
		List<ItemDto> inputList = getItems();
		List<ItemDto> results = processor.getSubsetWithMaxSum(inputList, 170F);

		assertThat(results).isNotNull();
		assertThat(results.size()).isEqualTo(2);
		assertThat(results).contains(getItem("MCO1"), getItem("MCO2"));
	}

	@Test
	void givenValidInputListAndValidLimitToBuyOneItem_whenGetSubsetWithMaxSum_thenReturnsItemMatchCriteria() {
		List<ItemDto> inputList = getItems();
		List<ItemDto> results = processor.getSubsetWithMaxSum(inputList, 60F);

		assertThat(results).isNotNull();
		assertThat(results.size()).isEqualTo(1);
		assertThat(results).contains(getItem("MCO2"));
	}

	@Test
	void givenValidInputListAndValidLimitToUnableBuySomeItem_whenGetSubsetWithMaxSum_thenReturnsNull() {
		List<ItemDto> inputList = getItems();
		List<ItemDto> results = processor.getSubsetWithMaxSum(inputList, 10F);

		assertThat(results).isNull();
	}


}
