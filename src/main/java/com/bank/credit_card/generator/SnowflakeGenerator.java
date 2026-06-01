package com.bank.credit_card.generator;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class SnowflakeGenerator implements IdGenerate {

    private final long machineId = 1L;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private synchronized Long nextId() {

        long timestamp = System.currentTimeMillis();

        if (timestamp == lastTimestamp) {
            sequence++;
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        return (timestamp << 22)
                | (machineId << 12)
                | sequence;
    }

    @Override
    public Optional<Long> load() {
        return Optional.of(nextId());
    }
}
