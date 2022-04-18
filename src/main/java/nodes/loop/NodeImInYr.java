package nodes.loop;

import compiler.Scope;
import nodes.Node;
import nodes.NodeAssignation;
import nodes.NodeStatements;
import nodes.expression.NodeExpression;
import nodes.expression.indivisible.identifiers.NodeLabel;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import parser.PatternConstants;
import nodes.expression.indivisible.identifiers.NodeIdentifier;

public class NodeImInYr extends Node {
    private NodeLabel loopName;
    private Node afterLoopAction;
    private NodeAssignation varInit;
    private NodeExpression whileCondition;
    private NodeStatements statements = new NodeStatements();

    public NodeImInYr() {
        addChild(null);
        addChild(null);
        addChild(null);
        addChild(null);
        addChild(getStatements());
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.IM_IN_YR);
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        Label lLoopStart = new Label();
        Label lLoopEnd = new Label();
        varInit.compile(classWriter, methodVisitor);
        methodVisitor.visitLabel(lLoopStart);
        whileCondition.compile(classWriter, methodVisitor);
        methodVisitor.visitInsn(Opcodes.DCONST_1);
        methodVisitor.visitJumpInsn(Opcodes.IFLT, lLoopEnd);
        statements.compile(classWriter, methodVisitor);
        afterLoopAction.compile(classWriter, methodVisitor);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, lLoopStart);
        methodVisitor.visitLabel(lLoopEnd);
    }

    @Override
    public void checkAndFixSemantic() {
        Scope scope = new Scope(getScope(), getScope().getScopeManager());
        loopName.setScope(scope);
        afterLoopAction.setScope(scope);
        varInit.setScope(scope);
        whileCondition.setScope(scope);
        for (Node statement : getStatements().getChildes()) {
            statement.setScope(scope);
        }

        loopName.semanticCheckIfNotExists();
        // despite the fact that var init in lolcode comes after its usage, we gotta init the var before it anyway
        varInit.checkAndFixSemantic();
        afterLoopAction.checkAndFixSemantic();
        whileCondition.checkAndFixSemantic();
        for (Node statement : getStatements().getChildes()) {
            statement.checkAndFixSemantic();
        }
    }

    public NodeLabel getLoopName() {
        return loopName;
    }

    public void setLoopName(NodeLabel loopName) {
        this.loopName = loopName;
    }

    public Node getAfterLoopAction() {
        return afterLoopAction;
    }

    public void setAfterLoopAction(Node afterLoopAction) {
        getChildes().set(1, afterLoopAction);
        this.afterLoopAction = afterLoopAction;
    }

    public NodeAssignation getVarInit() {
        return varInit;
    }

    public void setVarInit(NodeAssignation varInit) {
        getChildes().set(2, varInit);
        this.varInit = varInit;
    }

    public NodeExpression getWhileCondition() {
        return whileCondition;
    }

    public void setWhileCondition(NodeExpression whileCondition) {
        getChildes().set(3, whileCondition);
        this.whileCondition = whileCondition;
    }

    public NodeStatements getStatements() {
        return statements;
    }

    public void setStatements(NodeStatements statements) {
        getChildes().set(4, statements);
        this.statements = statements;
    }
}
