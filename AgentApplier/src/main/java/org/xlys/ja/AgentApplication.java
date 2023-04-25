package org.xlys.ja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class, args);
    }

    // java -javaagent:./AgentSupport-v1.jar=org.xlys.ja -jar AgentApplier-v1.jar
}
