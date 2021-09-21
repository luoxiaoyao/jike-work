package com.example.shop.util;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

/**
 * @author hyk
 */
public class IdUtil {
    private static int sequence = 0;
    private final static long START_TIME = new GregorianCalendar(2020, 1, 1).getTimeInMillis();
    private final static int WORKER_ID_BITS = 5;
    private final static int MAX_WORKER_ID = -1 ^ -1 << WORKER_ID_BITS;
    private final static int MACHINE_ID_BITS = 7;
    private final static int MAX_MACHINE_ID = -1 ^ -1 << MACHINE_ID_BITS;
    private final static int SEQUENCE_BITS = 10;
    private final static int MACHINE_ID_SHIFT = SEQUENCE_BITS;
    private final static int WORKER_ID_SHIFT = MACHINE_ID_SHIFT + WORKER_ID_BITS;
    private final static int TIMESTAMP_LEFT_SHIFT = WORKER_ID_SHIFT + WORKER_ID_BITS;
    private final static int SEQUENCE_MASK = -1 ^ -1 << SEQUENCE_BITS;
    private static long lastTimestamp = 0;

    public synchronized long getId() {
        long timestamp = timeGenerate();
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        return (timestamp << TIMESTAMP_LEFT_SHIFT) | (workerId << WORKER_ID_SHIFT) | (machineId << MACHINE_ID_SHIFT) | (sequence);
    }


    private int workerId;
    private int machineId;

    public void setWorkerId(int workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(MessageFormat.format("workerId 超出{0}", String.valueOf(MAX_WORKER_ID)));
        }
        this.workerId = workerId;
    }

    public void setMachineId(int machineId) {
        if (machineId > MAX_MACHINE_ID || machineId < 0) {
            throw new IllegalArgumentException(MessageFormat.format("machineId 超出{0}", String.valueOf(MAX_MACHINE_ID)));
        }
        this.machineId = machineId;
    }

    private long timeGenerate() {
        return System.currentTimeMillis() - START_TIME;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGenerate();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGenerate();
        }
        return timestamp;
    }

    public long getIdByTime(LocalDateTime localDateTime) {
        long timestamp = DateUtil.parseLong(localDateTime) - START_TIME;
        sequence = 0;
        lastTimestamp = timestamp;
        return (timestamp << TIMESTAMP_LEFT_SHIFT) | (sequence);
    }
}
