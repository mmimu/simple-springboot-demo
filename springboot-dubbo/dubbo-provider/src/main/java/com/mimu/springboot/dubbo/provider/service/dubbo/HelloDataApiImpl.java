package com.mimu.springboot.dubbo.provider.service.dubbo;

import com.mimu.springboot.dubbo.api.api.HelloDataApi;
import com.mimu.springboot.dubbo.api.model.HelloData;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * author: mimu
 * date: 2019/9/18
 */
@Component
@DubboService(interfaceClass = HelloDataApi.class, timeout = 5000)
public class HelloDataApiImpl implements HelloDataApi {
    private static final Logger logger = LoggerFactory.getLogger(HelloDataApiImpl.class);

    @Override
    public HelloData hello(String name) throws Exception {
        HelloData build = new HelloData(name);
        logger.info("data={}", build);
        return build;
    }

    @Override
    public CompletableFuture<HelloData> asyncHello(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return new HelloData(name);
            } catch (InterruptedException e) {
                return null;
            }
        });
    }
}
