package org.xlys.agent.bytecode;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class CustomizedTransformer implements ClassFileTransformer {

    private String packageName;

    public CustomizedTransformer() {
    }

    public CustomizedTransformer(String packageName) {
        System.out.println("Agent loaded successfully from args:"+packageName);
        this.packageName = packageName;
    }

    @Override
    public byte[] transform(
            ClassLoader loader,
            String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");

        // monitor works on specific package
        if (!className.startsWith(packageName)) {
            return classfileBuffer;
        }

        CtClass ctClass = null;
        try {
            ClassPool classPool = ClassPool.getDefault();
            ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));

            for (CtMethod method : ctClass.getDeclaredMethods()) {
                // bytecode injection
                method.addLocalVariable("start", CtClass.longType);
                method.insertBefore("start = System.currentTimeMillis();");
                String methodName = method.getLongName();
                method.insertAfter("System.out.println(\"" + methodName + " cost: \" + (System.currentTimeMillis()-start));");
            }
            byte[] byteCode = ctClass.toBytecode();
            return byteCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}
