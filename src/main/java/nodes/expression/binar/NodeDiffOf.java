package nodes.expression.binar;

import nodes.expression.NodeExpression;

import nodes.Node;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import parser.PatternConstants;

public class NodeDiffOf extends NodeBinaryExpression {
    public NodeDiffOf(NodeExpression leftOperand, NodeExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.DIFF_OF);

    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        getLeftOperand().compile(classWriter, methodVisitor);
        getRightOperand().compile(classWriter, methodVisitor);
        methodVisitor.visitInsn(Opcodes.DNEG);
        methodVisitor.visitInsn(Opcodes.DADD);
    }
}
