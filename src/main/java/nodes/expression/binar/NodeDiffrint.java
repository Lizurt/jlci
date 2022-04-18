package nodes.expression.binar;

import nodes.expression.NodeExpression;

import nodes.Node;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import parser.PatternConstants;

public class NodeDiffrint extends NodeBinaryExpression {
    public NodeDiffrint(NodeExpression leftOperand, NodeExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.DIFFRINT);

    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        getLeftOperand().compile(classWriter, methodVisitor);
        getRightOperand().compile(classWriter, methodVisitor);
        Label lElse = new Label();
        Label lEnd = new Label();
        methodVisitor.visitJumpInsn(Opcodes.IFNE, lElse);
        methodVisitor.visitInsn(Opcodes.DCONST_0);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, lEnd);
        methodVisitor.visitLabel(lElse);
        methodVisitor.visitInsn(Opcodes.DCONST_1);
        methodVisitor.visitLabel(lEnd);
    }
}
