package com.meli.challenge.service.impl;

import com.meli.challenge.dto.CouponRequestDto;
import com.meli.challenge.dto.CouponResponseDto;
import com.meli.challenge.dto.ItemDto;
import com.meli.challenge.exception.NotFoundResultsException;
import com.meli.challenge.feign.response.ItemDetailsResponseDto;
import com.meli.challenge.service.api.ICouponService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CouponService implements ICouponService {

	/**
	 * Service to retrieve item price and details from ML api
	 */
	private final ItemDetailService itemDetailService;

	/**
	 * Executor service to process parallel request
	 */
	private final ExecutorService executorService = Executors.newFixedThreadPool(100);

	@Autowired
	public CouponService(ItemDetailService itemDetailService) {
		this.itemDetailService = itemDetailService;
	}

	@Override
	public CouponResponseDto processCoupon(CouponRequestDto coupon) {

		List<CompletableFuture<ItemDetailsResponseDto>> futureItemList = coupon.getItems().stream()
			.map(item -> CompletableFuture.supplyAsync(() -> this.itemDetailService.getItemDetail(item), executorService))
			.collect(Collectors.toList());

		Map<String, Float> items = futureItemList.stream()
			.map(CompletableFuture::join).collect(Collectors
				.toMap(ItemDetailsResponseDto::getId, ItemDetailsResponseDto::getPrice,
					(oldValue, newValue) -> newValue));

		log.info("Results: {}", items);

		List<String> results = this.calculate(items, coupon.getAmount());

		if(Objects.isNull(results)) {
			throw new NotFoundResultsException(coupon.getAmount());
		}

		Float totalAmount = 0F;
		for( String itemId : results) {
			totalAmount += items.get(itemId);
		}

		return CouponResponseDto.builder()
			.itemId(results)
			.total(totalAmount)
			.build();
	}

	private List<String> calculate(Map<String, Float> items, Float amount) {

		List<ItemDto> itemsToProcess = new ArrayList<>();

		items.forEach( (key, price) -> itemsToProcess.add(new ItemDto(key, price)));

		List<ItemDto> filteredItems = itemsToProcess.parallelStream()
			.filter(itemDto -> itemDto.getPrice() <= amount)
			.collect(Collectors.toList());

		CouponLogicProcessor processor = new CouponLogicProcessor();
		List<ItemDto> results = processor.maxSum(filteredItems, amount);

		if(Objects.nonNull(results)) {
			return results.stream().map(ItemDto::getId).collect(Collectors.toList());
		}

		return null;
	}

}
