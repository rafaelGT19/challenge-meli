package com.meli.challenge.service.impl;

import com.meli.challenge.dto.CouponRequestDto;
import com.meli.challenge.dto.CouponResponseDto;
import com.meli.challenge.feign.response.ItemDetailsResponseDto;
import com.meli.challenge.service.api.ICouponService;
import java.util.List;
import java.util.Map;
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

	private final ItemDetailService itemDetailService;
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

		this.calculate(items, coupon.getAmount());

		return null;
	}

	private List<String> calculate(Map<String, Float> items, Float amount) {

		return null;
	}

}
