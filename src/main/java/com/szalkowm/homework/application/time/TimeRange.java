package com.szalkowm.homework.application.time;

import java.time.LocalTime;

public class TimeRange {

    private final LocalTime from;
    private final LocalTime to;

    public TimeRange(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    public boolean isInRange(LocalTime time) {
        int edgeComparision = this.from.compareTo(this.to);
        if (edgeComparision < 0) {
            return !time.isBefore(this.from) && !time.isAfter(this.to);
        } else if (edgeComparision > 0) {
            return !time.isAfter(this.to) || !time.isBefore(this.from);
        } else {
            return this.to.equals(time);
        }
    }
}
