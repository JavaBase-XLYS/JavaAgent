package org.xlys.agent.bytecode;

import java.lang.instrument.Instrumentation;

/**
 * Need to specify the Pre-main Class
 * */
public class Agent {

    public static void premain(String agentArgs) {

    }

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        instrumentation.addTransformer(new CustomizedTransformer(agentArgs));
    }
}
