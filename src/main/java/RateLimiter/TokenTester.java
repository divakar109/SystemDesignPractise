package RateLimiter;

public class TokenTester {

    public static void main(String[] args) throws  InterruptedException{
        System.out.println("Token Tester");
        TokenBucketRateLimiter tokenBucketRateLimiter = new TokenBucketRateLimiter(10, 10);
        System.out.println("Allowed or not:" + tokenBucketRateLimiter.allowRequest(6));
        System.out.println("Tokens available: " + tokenBucketRateLimiter.getCurrentTokens());
        Thread.sleep(1000);
        System.out.println("Allowed or not:" + tokenBucketRateLimiter.allowRequest(0));
        System.out.println("Tokens available: " + tokenBucketRateLimiter.getCurrentTokens());
    }
}
