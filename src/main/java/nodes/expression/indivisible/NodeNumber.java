package nodes.expression.indivisible;

import nodes.expression.NodeExpression;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NodeNumber extends NodeExpression {
    private float value;

    public NodeNumber(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        methodVisitor.visitLdcInsn(getValue());
    }

    @Override
    public void checkAndFixSemantic() {

    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
