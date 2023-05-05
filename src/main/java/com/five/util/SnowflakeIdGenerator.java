package com.five.util;

import java.util.Random;

/**
 * @author MrMa
 * @version 1.0
 * @description 雪花算法生成长度为10的ID
 */
public class SnowflakeIdGenerator {
    private static final int NUM_BITS_DATACENTER_ID = 5;
    private static final int NUM_BITS_WORKER_ID = 5;
    private static final int NUM_BITS_SEQUENCE = 12;

    private static final long MAX_DATACENTER_ID = ~(-1L << NUM_BITS_DATACENTER_ID);
    private static final long MAX_WORKER_ID = ~(-1L << NUM_BITS_WORKER_ID);
    private static final long MAX_SEQUENCE = ~(-1L << NUM_BITS_SEQUENCE);

    private static final long TIMESTAMP_LEFT_SHIFT = NUM_BITS_DATACENTER_ID + NUM_BITS_WORKER_ID + NUM_BITS_SEQUENCE;
    private static final long DATACENTER_ID_LEFT_SHIFT = NUM_BITS_WORKER_ID + NUM_BITS_SEQUENCE;
    private static final long WORKER_ID_LEFT_SHIFT = NUM_BITS_SEQUENCE;

    private final Random random = new Random();
    private final long datacenterId;
    private final long workerId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowflakeIdGenerator(long datacenterId, long workerId) {
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("Datacenter id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
        }
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        this.datacenterId = datacenterId;
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = random.nextInt((int) MAX_SEQUENCE);
        }

        lastTimestamp = timestamp;

        return ((timestamp << TIMESTAMP_LEFT_SHIFT)
                | (datacenterId << DATACENTER_ID_LEFT_SHIFT)
                | (workerId << WORKER_ID_LEFT_SHIFT)
                | sequence) % (long) Math.pow(10, 10);
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 静态方法生成雪花ID
     *
     * @return 返回长度为10的数字ID
     */
    public static String generate() {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        long id = idGenerator.nextId();
        return String.format("%010d", id);
    }
}