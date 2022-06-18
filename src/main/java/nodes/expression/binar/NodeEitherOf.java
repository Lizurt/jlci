package nodes.expression.binar;

import nodes.expression.NodeExpression;

import nodes.Node;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import parser.PatternConstants;

public class NodeEitherOf extends NodeBinaryExpression {
    public NodeEitherOf(NodeExpression leftOperand, NodeExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.EITHER_OF);
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        Label lTrue = new Label();
        Label lEnd = new Label();
        getLeftOperand().compile(classWriter, methodVisitor);
        methodVisitor.visitInsn(Opcodes.FCONST_0);
        methodVisitor.visitInsn(Opcodes.FCMPG);
        methodVisitor.visitJumpInsn(Opcodes.IFGT, lTrue);
        getRightOperand().compile(classWriter, methodVisitor);
        methodVisitor.visitInsn(Opcodes.FCONST_0);
        methodVisitor.visitInsn(Opcodes.FCMPG);
        methodVisitor.visitJumpInsn(Opcodes.IFGT, lTrue);
        methodVisitor.visitInsn(Opcodes.FCONST_0);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, lEnd);
        methodVisitor.visitLabel(lTrue);
        methodVisitor.visitInsn(Opcodes.FCONST_1);
        methodVisitor.visitLabel(lEnd);
    }
}
