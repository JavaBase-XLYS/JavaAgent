package org.xlys.ja.config;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@WebEndpoint(id = "ssm")
public class SelfSimpleMonitorConfig {

    @ReadOperation
    public Map<String,Object> getSSMInfo() {
        int processors = Runtime.getRuntime().availableProcessors();
        long freeMemory = Runtime.getRuntime().freeMemory();
        return new HashMap<>() {{
            put("Cores Number", processors);
            put("Available memory", freeMemory);
        }};

    }
}
