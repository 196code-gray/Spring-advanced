package com.spring.advanced;

import com.spring.advanced.logtrace.FieldLogTrace;
import com.spring.advanced.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        return new FieldLogTrace();
    }
}
