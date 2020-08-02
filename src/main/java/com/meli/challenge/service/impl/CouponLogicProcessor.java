package com.meli.challenge.service.impl;

import com.meli.challenge.dto.ItemDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Performs combination optimization process to compute result
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
final class CouponLogicProcessor {

	/**
	 * Table to collect temporary results
	 */
	private Map<Float, List<ItemDto>> collectedResults = new HashMap<>();

	/**
	 * Max sum found
	 */
	private Float maximumSum = 0F;

	/**
	 * Array of items to process
	 */
	private ItemDto[] inputItems;

	/**
	 * Max allowed spend amount
	 */
	private Float limit;

	/**
	 * Returns the subset of elements whose sum is the maximum possible less or equals than limit
	 * @param input array of elements to perform compute
	 * @param limit maximumSum allowed amount
	 * @return
	 */
	final List<ItemDto> getSubsetWithMaxSum(List<ItemDto> input, float limit) {
		this.inputItems = new ItemDto[input.size()];
		this.inputItems = input.toArray(this.inputItems);
		this.limit = limit;

		this.collectSums(0F, 0, new ArrayList<>());

		return this.collectedResults.getOrDefault(this.maximumSum, null);
	}

	/**
	 * Recursive method to compute the best subset of elements to maximize spend
	 * @param n temporary sum value
	 * @param i index o element to process
	 * @param values temporary collected elements
	 */
	private void collectSums(Float n, int i, List<ItemDto> values) {
		for(; i < inputItems.length; i++) {
			final Float sum = n + this.inputItems[i].getPrice();
			if(sum <= limit) {
				values.add(inputItems[i]);
				if(sum >= this.maximumSum && values.size() >= 1) {
					this.maximumSum = sum;
					collectedResults.put(this.maximumSum, new ArrayList<>(values));
				}
				collectSums(sum, i+1, values);
			}
		}
		if(values.size() > 0) {
			values.remove(values.get(values.size() - 1));
		}
	}

}
