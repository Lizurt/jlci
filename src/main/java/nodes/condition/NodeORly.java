package nodes.condition;

import compiler.Scope;
import nodes.Node;
import nodes.expression.NodeExpression;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import parser.PatternConstants;

import java.util.InputMismatchException;

public class NodeORly extends Node {
    private NodeExpression condition;
    private NodeYaRly nodeYaRly;
    private NodeNoWai nodeNoWai;

    public NodeORly() {
        addChild(null);
        addChild(null);
        addChild(null);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.O_RLY);
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        condition.compile(classWriter, methodVisitor);
        methodVisitor.visitInsn(Opcodes.FCONST_0);
        methodVisitor.visitInsn(Opcodes.FCMPG);
        Label lEnd = new Label();
        Label lElse = new Label();
        methodVisitor.visitJumpInsn(Opcodes.IFLT, nodeNoWai == null ? lEnd : lElse);
        nodeYaRly.compile(classWriter, methodVisitor);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, lEnd);
        if (nodeNoWai != null) {
            methodVisitor.visitLabel(lElse);
            nodeNoWai.compile(classWriter, methodVisitor);
            methodVisitor.visitJumpInsn(Opcodes.GOTO, lEnd);
        }
        methodVisitor.visitLabel(lEnd);
    }

    @Override
    public void checkAndFixSemantic() {
        Scope scope = new Scope(getScope());
        condition.setScope(scope);
        condition.checkAndFixSemantic();
        if (nodeYaRly != null) {
            nodeYaRly.setScope(scope);
            nodeYaRly.checkAndFixSemantic();
        }
        if (nodeNoWai != null) {
            nodeNoWai.setScope(scope);
            nodeNoWai.checkAndFixSemantic();
        }
    }

    public NodeExpression getCondition() {
        return condition;
    }

    public void setCondition(NodeExpression condition) {
        getChildes().set(0, condition);
        this.condition = condition;
    }

    public NodeYaRly getNodeYaRly() {
        return nodeYaRly;
    }

    public void setNodeYaRly(NodeYaRly nodeYarly) {
        getChildes().set(1, nodeYarly);
        this.nodeYaRly = nodeYarly;
    }

    public NodeNoWai getNodeNoWai() {
        return nodeNoWai;
    }

    public void setNodeNoWai(NodeNoWai nodeNoWai) {
        getChildes().set(2, nodeNoWai);
        this.nodeNoWai = nodeNoWai;
    }
}
