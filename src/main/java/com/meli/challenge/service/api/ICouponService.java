package com.meli.challenge.service.api;

import com.meli.challenge.dto.CouponRequestDto;
import com.meli.challenge.dto.CouponResponseDto;

public interface ICouponService {

	CouponResponseDto processCoupon(CouponRequestDto coupon);

}
