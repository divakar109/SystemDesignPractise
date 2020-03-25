package RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class FixedWindowRateLimiter implements IRateLimiter {

    private static String USERID = "u1";
    private static final int TOTALCOUNT = 10;
    private class TimeStampWithCount {

        public int getCount() {
            return count;
        }

        public long getTimestamp() {
            return timestamp;
        }

        private int count;
        private long timestamp;

        public TimeStampWithCount(int count, long timestamp) {
            this.count = count;
            this.timestamp = timestamp;
        }
    }

    private Map<String, TimeStampWithCount> fixedWindowCount = new HashMap<>();

    @Override
    public boolean allowRequest(int requests) {

        long currentTime = System.nanoTime();
        TimeStampWithCount time = fixedWindowCount.get(USERID);

        if (time == null) {

            fixedWindowCount.put(USERID, new TimeStampWithCount(1, currentTime));
            return true;

        } else {

            long prevTime = time.getTimestamp();
            int prevCount = time.getCount();
            long timeDiff = currentTime - prevTime;
            long timeInMinutes = (timeDiff/(1000000000))/60;
            if (timeInMinutes <= 0.5) {
                if ((prevCount + requests) < TOTALCOUNT) {
                    fixedWindowCount.put(USERID, new TimeStampWithCount(prevCount + requests, prevTime));
                    return true;
                } else
                    return false;
            }else {
                  long currTime = System.nanoTime();
                  if (requests < TOTALCOUNT)
                    fixedWindowCount.put(USERID, new TimeStampWithCount(requests, currTime));
                  return true;
            }
        }
    }
}
