package com.example.foobar.api.feign.factory;

import feign.hystrix.FallbackFactory;
import com.example.foobar.api.feign.FooBarClient;
import com.example.foobar.api.feign.fallback.FooBarClientFallback;
import org.springframework.stereotype.Component;

/**
 * FooBar Feign Fallback Factory
 *
 * @author geekidea
 * @since 2020-04-30
 */
@Component
public class FooBarClientFallbackFactory implements FallbackFactory<FooBarClient> {
    @Override
    public FooBarClient create(Throwable throwable) {
        FooBarClientFallback fooBarClientFallback = new FooBarClientFallback();
        fooBarClientFallback.setThrowable(throwable);
        return fooBarClientFallback;
    }
}
