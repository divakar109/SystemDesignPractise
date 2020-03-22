package RateLimiter;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenBucketRateLimiterTest {

    @Test
    public void getCurrentTokens() {
        TokenBucketRateLimiter tokenBucketRateLimiter = new TokenBucketRateLimiter(10, 10);
        tokenBucketRateLimiter.allowRequest(6);
        Assert.assertEquals(4, tokenBucketRateLimiter.getCurrentTokens());
    }
}