package RateLimiter;

public class TokenBucketRateLimiter implements IRateLimiter {

    private long capacity;

    public long getCurrentTokens() {
        return currentTokens;
    }

    private long currentTokens;
    private long refillRateInSeconds;
    private long lastUpdatedTimeStampInNanos;

    public TokenBucketRateLimiter(long capacity, long refillRateInSeconds) {
        this.capacity = capacity;
        this.refillRateInSeconds = refillRateInSeconds;
        this.currentTokens = this.capacity;
        this.lastUpdatedTimeStampInNanos = System.nanoTime();
    }

    @Override
    public boolean allowRequest(int requests) {
        refillBucket();
        if (currentTokens > 0 && requests < currentTokens) {
            currentTokens = currentTokens - requests;
            return true;
        }
        return false;
    }

    private void refillBucket() {
        long currentTimeInNanos = System.nanoTime();
        long timeElapsedInNanos = currentTimeInNanos - lastUpdatedTimeStampInNanos;
        long tokensToRefill = (timeElapsedInNanos * refillRateInSeconds)/1000000000;
        currentTokens  = Math.min(tokensToRefill, capacity);
    }
}
