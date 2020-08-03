package com.meli.challenge.util;

import com.meli.challenge.dto.CouponRequestDto;
import com.meli.challenge.dto.ItemDto;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to provide mocks objects for test
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
public class Sample {

	private static Map<String, ItemDto> itemsMap = new HashMap<>();

	static {
		ItemDto item1 = ItemDto.builder().id("MCO1").price(100F).build();
		ItemDto item2 = ItemDto.builder().id("MCO2").price(50F).build();
		itemsMap.put(item1.getId(), item1);
		itemsMap.put(item2.getId(), item2);
	}

	public static CouponRequestDto getControllerRequest() {

		return CouponRequestDto.builder()
			.items(Arrays.asList("MCO1","MCO2"))
			.amount(100F)
			.build();
	}

	public static ItemDto getItem(String id) {
		return itemsMap.getOrDefault(id, null);
	}

	public static List<ItemDto> getItems() {
		return Arrays.asList(itemsMap.get("MCO1"), itemsMap.get("MCO2"));
	}

}
