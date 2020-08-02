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
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to process coupon request
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
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
	private final ExecutorService executorService;

	@Autowired
	public CouponService(ItemDetailService itemDetailService, ExecutorService executorService) {
		this.itemDetailService = itemDetailService;
		this.executorService = executorService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CouponResponseDto processCoupon(CouponRequestDto coupon) {

		Map<String, Float> items = this.getItemsToProcess(coupon.getItems());

		List<String> results = this.calculate(items, coupon.getAmount());

		if (Objects.isNull(results)) {
			throw new NotFoundResultsException(coupon.getAmount());
		}

		Float totalSpend = results.stream()
			.map(items::get).reduce(0F, Float::sum);

		log.info("Success coupon processed with items: {} and total amount: {}",
			results, totalSpend);

		return CouponResponseDto.builder()
			.itemId(results)
			.total(totalSpend)
			.build();
	}

	/**
	 * Returns Map with item id and price retrieved from external api
	 * @param itemIds list of item to get information
	 * @return
	 */
	private Map<String, Float> getItemsToProcess(List<String> itemIds) {
		List<CompletableFuture<ItemDetailsResponseDto>> futureItemList = itemIds.stream()
			.map(item -> CompletableFuture
				.supplyAsync(() -> this.itemDetailService.getItemDetail(item), executorService))
			.collect(Collectors.toList());

		return futureItemList.stream()
			.map(CompletableFuture::join).collect(Collectors
				.toMap(ItemDetailsResponseDto::getId, ItemDetailsResponseDto::getPrice,
					(oldValue, newValue) -> newValue));
	}

	/**
	 * Retrieve the subset of items id whose sum maximize the coupon spend amount
	 * @param items list of items id to process
	 * @param amount max spend amount
	 * @return subset of item from original list whose maximize the spend amount
	 */
	private List<String> calculate(Map<String, Float> items, Float amount) {

		List<ItemDto> itemsToProcess = new ArrayList<>();

		items.forEach((key, price) -> itemsToProcess.add(new ItemDto(key, price)));

		List<ItemDto> filteredItems = itemsToProcess.parallelStream()
			.filter(itemDto -> itemDto.getPrice() <= amount)
			.collect(Collectors.toList());

		CouponLogicProcessor processor = new CouponLogicProcessor();
		List<ItemDto> results = processor.getSubsetWithMaxSum(filteredItems, amount);

		if (Objects.nonNull(results)) {
			return results.stream().map(ItemDto::getId).collect(Collectors.toList());
		}

		return null;
	}

}
