package nodes.expression.binar;

import nodes.expression.NodeExpression;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import parser.PatternConstants;

public class NodeBothSaem extends NodeBinaryExpression {
    public NodeBothSaem(NodeExpression leftOperand, NodeExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.BOTH_SAEM);
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        getLeftOperand().compile(classWriter, methodVisitor);
        getRightOperand().compile(classWriter, methodVisitor);
        Label lElse = new Label();
        Label lEnd = new Label();
        methodVisitor.visitJumpInsn(Opcodes.IFNE, lElse);
        methodVisitor.visitInsn(Opcodes.DCONST_1);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, lEnd);
        methodVisitor.visitLabel(lElse);
        methodVisitor.visitInsn(Opcodes.DCONST_0);
        methodVisitor.visitLabel(lEnd);
    }
}
