package ch.heigvd.amt;

import java.time.Duration;
import java.time.Instant;

public class Status {
    private int id;
    private Probe probe;
    private boolean isUp;
    private Instant timestamp;
    private Duration duration;
}
