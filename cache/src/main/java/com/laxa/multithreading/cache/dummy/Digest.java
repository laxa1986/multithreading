package com.laxa.multithreading.cache.dummy;

import java.util.HashMap;
import java.util.Map;

public abstract class Digest {
    // 1. bad key: new byte[]{1,2} !.equals(  new byte[]{1,2}  ) two logically equal arrays are not java equal
    //    key is mutable, but funny that .hashCode and .equals will not impacted by object modification
    private Map<byte[], byte[]> cache = new HashMap<byte[], byte[]>();

    public byte[] digest(byte[] input) {
        // 2. cache - not threadsafe. There can be race condition when one read and other write below
        byte[] result = cache.get(input);
        if (result == null) {
            // 3. cache should be final, otherwise different threads could see different objects
            synchronized (cache) {
                result = cache.get(input);
                if (result == null) {
                    // 4. calculation could be quite heavy and all this time cache is blocked
                    result = doDigest(input);
                    cache.put(input, result);

                    // 5. unclear when to purge this cache?
                }
            }
        }
        return result;
    }

    protected abstract byte[] doDigest(byte[] input);
}