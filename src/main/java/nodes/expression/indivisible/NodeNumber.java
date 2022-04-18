package nodes.expression.indivisible;

import nodes.expression.NodeExpression;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NodeNumber extends NodeExpression {
    private double value;

    public NodeNumber(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        // todo: uhmmm... we can push only 0d or 1d or any other short int, but not other doubles
        methodVisitor.visitInsn(Opcodes.DCONST_1);
    }

    @Override
    public void checkAndFixSemantic() {

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
