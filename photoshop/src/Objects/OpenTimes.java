package Objects;

import java.time.Duration;
import java.time.LocalTime;

public class OpenTimes {
    public String day;
    public LocalTime open;
    public LocalTime close;
    public long hoursOpen;


    
    public OpenTimes(String day, String open, String close) {
        this.day = day;
        this.open =  LocalTime.of(Integer.valueOf(open.substring(0, 2)),Integer.valueOf(open.substring(3, 5)));
        this.close = LocalTime.of(Integer.valueOf(close.substring(0, 2)),Integer.valueOf(close.substring(3, 5)));
        this.hoursOpen = Duration.between(this.open, this.close).toHours();
    }
}
