package RateLimiter;


public interface IRateLimiter {

    /**
     *
     * @param requests no of requests requested by the Rate Limiter
     * @return true or false whether the request can be satisfied or not.
     */
    boolean allowRequest(int requests);
}
