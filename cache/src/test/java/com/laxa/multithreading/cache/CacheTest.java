package com.laxa.multithreading.cache;

import com.laxa.multithreading.cache.config.CacheConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by alex4 on 2/22/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CacheConfig.class})
public class CacheTest {
    @Autowired
    @Qualifier("computationService")
    private ComputationService sut;

    @Test
    public void testCache() {
        System.out.println("About to call computation");
        sut.compute(2);
        System.out.println("About to call computation second time");
        sut.compute(2);
        System.out.println("end");
    }
}