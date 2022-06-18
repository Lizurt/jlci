package nodes;

import compiler.Compiler;
import compiler.Identifier;
import nodes.expression.NodeExpression;
import nodes.expression.indivisible.identifiers.NodeVariable;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import parser.PatternConstants;

public class NodeAssignation extends Node {
    private NodeExpression expression;
    private NodeVariable identifier;

    public NodeAssignation(NodeVariable identifier, NodeExpression expression) {
        this.identifier = identifier;
        this.expression = expression;
        addChild(identifier);
        addChild(expression);
    }

    public NodeExpression getExpression() {
        return expression;
    }

    public void setExpression(NodeExpression expression) {
        this.expression = expression;
        getChildes().set(1, expression);
    }

    public NodeVariable getIdentifier() {
        return identifier;
    }

    public void setIdentifier(NodeVariable identifier) {
        this.identifier = identifier;
        getChildes().set(0, identifier);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.R);
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        expression.compile(classWriter, methodVisitor);
        int index = getScope().getScopeManager().findOrGenerateIndexForVariable(getScope(), identifier.getName());
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitVarInsn(Opcodes.FSTORE, index);
        methodVisitor.visitVarInsn(Opcodes.FSTORE, Compiler.ID_LAST_EXPRESSION);
    }

    @Override
    public void checkAndFixSemantic() {
        expression.setScope(getScope());
        identifier.setScope(getScope());
        expression.checkAndFixSemantic();
        if (identifier.getScope().tryGetVariableByName(identifier.getName()) != null) {
            return;
        }
        identifier.getScope().unsafelyAddVariableToScope(
                new Identifier(
                        identifier.getName(), identifier.getScope().getScopeManager().getAndProceedAvailableIndex()
                )
        );
    }
}
