package com.meli.challenge.service.impl;

import com.meli.challenge.dto.ItemDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CouponLogicProcessor {

	private Map<Float, List<ItemDto>> sums = new HashMap<>();
	private Float max = 0F;
	private List<ItemDto> input;
	private Float limit;

	public List<ItemDto> maxSum(List<ItemDto> input, float limit) {
		this.input = input;
		this.limit = limit;

		this.collectSums(0F, 0, new ArrayList<>());

		return this.sums.getOrDefault(this.max, null);
	}

	private void collectSums(Float n, int i, List<ItemDto> values) {
		for(; i < input.size(); i++) {
			final Float sum = n + this.input.get(i).getPrice();
			if(sum <= limit) {
				values.add(input.get(i));
				if(sum >= this.max && values.size() >= 1) {
					this.max = sum;
					sums.put(this.max, new ArrayList<>(values));
				}
				collectSums(sum, i+1, values);
			}
		}
		if(values.size() > 0) {
			values.remove(values.get(values.size() - 1));
		}
	}

}
