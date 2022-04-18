package nodes.expression.binar;

import nodes.expression.NodeExpression;

import nodes.Node;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import parser.PatternConstants;

public class NodeBothOf extends NodeBinaryExpression {
    public NodeBothOf(NodeExpression leftOperand, NodeExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.BOTH_OF);
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        getLeftOperand().compile(classWriter, methodVisitor);
        methodVisitor.visitInsn(Opcodes.DCONST_0);
        methodVisitor.visitInsn(Opcodes.DCMPG);
        getRightOperand().compile(classWriter, methodVisitor);
        methodVisitor.visitInsn(Opcodes.DCONST_0);
        methodVisitor.visitInsn(Opcodes.DCMPG);
        methodVisitor.visitInsn(Opcodes.DCMPG);
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
