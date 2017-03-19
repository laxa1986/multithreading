package com.laxa.multithreading.cache.dummy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Digest2 {
    private final Map<byte[], byte[]> cache = new ConcurrentHashMap<>();

    public byte[] digest(byte[] inputs) {
        // but in this case two processes could calculate the same value in parallel - possible overhead
        // and again unclear when to purge the cache
        return cache.computeIfAbsent(inputs, this::doDigest);
    }

    protected abstract byte[] doDigest(byte[] input);
}