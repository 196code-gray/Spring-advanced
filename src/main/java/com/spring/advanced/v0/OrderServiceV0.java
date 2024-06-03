package com.spring.advanced.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepositoryV0;

    // 아이템을 주문하는 로직
    public void orderItem(String itemId){
        orderRepositoryV0.save(itemId);
    }
}
