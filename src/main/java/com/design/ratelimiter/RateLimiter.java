package com.design.ratelimiter;

import org.springframework.stereotype.Component;

@Component
public class RateLimiter {
    Integer bucketCapacity = 5;
    Long tokens = 5L;
    Long lastFilledTime = System.currentTimeMillis();
    Long refillTime = 60000L;

    public Boolean canServe() {
        Long now = System.currentTimeMillis();
        long refillTokens = (now-lastFilledTime)/refillTime;
        this.tokens+=  refillTokens;
        if(tokens > bucketCapacity) {
            this.tokens = Long.valueOf(this.bucketCapacity);
        }
        if(tokens > 0) {
            tokens--;
            lastFilledTime = now;
            return true;
        } else return false;

    }
}
