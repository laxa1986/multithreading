package com.laxa.multithreading.cache.impl;

import com.laxa.multithreading.cache.ComputationService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by alex4 on 2/22/17.
 */
@Service("computationService")
public class ComputationServiceImpl implements ComputationService {
    @Override
    @Cacheable(value = "computationCache", key = "#arg")
    public int compute(int arg) {
        System.out.println("multiplying arg * arg...");
        return arg * arg;
    }
}