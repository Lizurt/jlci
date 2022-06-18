package nodes.expression.unar;

import nodes.Node;
import nodes.expression.NodeExpression;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NodeNot extends NodeUnaryExpression {
    public NodeNot(NodeExpression operand) {
        super(operand);
    }

    @Override
    public String toString() {
        return "NOT";
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        methodVisitor.visitInsn(Opcodes.FCONST_0);
        methodVisitor.visitInsn(Opcodes.FCMPG);
    }
}
