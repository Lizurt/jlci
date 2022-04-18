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
        // stack: ...

        getLeftOperand().compile(classWriter, methodVisitor);
        methodVisitor.visitInsn(Opcodes.DCONST_0);
        methodVisitor.visitInsn(Opcodes.DCMPG);
        methodVisitor.visitInsn(Opcodes.DCONST_1);
        Label lElse1 = new Label();
        Label lEnd1 = new Label();
        methodVisitor.visitJumpInsn(Opcodes.IFNE, lElse1);
        methodVisitor.visitInsn(Opcodes.DCONST_1);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, lEnd1);
        methodVisitor.visitLabel(lElse1);
        methodVisitor.visitInsn(Opcodes.DCONST_0);
        methodVisitor.visitLabel(lEnd1);

        // stack: ..., {leftOperand > 0 ? 1 : 0}

        getRightOperand().compile(classWriter, methodVisitor);
        methodVisitor.visitInsn(Opcodes.DCONST_0);
        methodVisitor.visitInsn(Opcodes.DCMPG);
        methodVisitor.visitInsn(Opcodes.DCONST_1);
        Label lElse2 = new Label();
        Label lEnd2 = new Label();
        methodVisitor.visitJumpInsn(Opcodes.IFNE, lElse2);
        methodVisitor.visitInsn(Opcodes.DCONST_1);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, lEnd2);
        methodVisitor.visitLabel(lElse2);
        methodVisitor.visitInsn(Opcodes.DCONST_0);
        methodVisitor.visitLabel(lEnd2);

        // stack: ..., {leftOperand > 0 ? 1 : 0}, {rightOperand > 0 ? 1 : 0}

        methodVisitor.visitInsn(Opcodes.DADD);
    }
}
