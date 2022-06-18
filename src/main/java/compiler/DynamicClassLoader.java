package compiler;

public class DynamicClassLoader extends ClassLoader {
    public Class<?> loadClass(String className, byte[] bytecode) {
        return super.defineClass(className, bytecode, 0, bytecode.length);
    }
}
