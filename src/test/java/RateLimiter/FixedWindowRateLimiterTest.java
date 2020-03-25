package RateLimiter;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FixedWindowRateLimiterTest {

   public static void main(String[] args) throws InterruptedException{

       IRateLimiter rateLimiter = new FixedWindowRateLimiter();
       boolean result1 = rateLimiter.allowRequest(5);
       System.out.println("Allowed Result First Time is: " + result1);
       boolean result2 = rateLimiter.allowRequest(5);
       System.out.println("Allowed Result First Time is: " + result2);
       boolean result3 = rateLimiter.allowRequest(5);
       System.out.println("Allowed Result First Time is: " + result3);
       Thread.sleep(1002 * 60);
       boolean result4 = rateLimiter.allowRequest(5);
       System.out.println("Allowed Result First Time is: " + result4);
   }
}