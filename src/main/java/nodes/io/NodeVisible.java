package nodes.io;

import compiler.Compiler;
import nodes.Node;
import nodes.expression.NodeExpression;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import parser.PatternConstants;

import java.io.PrintStream;

public class NodeVisible extends Node {
    private NodeExpression expression;

    public NodeVisible(NodeExpression expression) {
        this.expression = expression;
        addChild(expression);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.VISIBLE);
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(Opcodes.ALOAD, Compiler.ID_OUTPUT_STREAM);
        expression.compile(classWriter, methodVisitor);
        methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                PrintStream.class.getName().replace('.', '/'),
                "println",
                "(F)V",
                false
        );
    }

    public NodeExpression getExpression() {
        return expression;
    }

    public void setExpression(NodeExpression expression) {
        this.expression = expression;
        getChildes().set(0, expression);
    }

    @Override
    public void checkAndFixSemantic() {
        expression.setScope(getScope());
        expression.checkAndFixSemantic();
    }
}
