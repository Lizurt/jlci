package nodes.function;

import compiler.Scope;
import nodes.Node;
import nodes.NodeReturn;
import nodes.NodeStatements;
import nodes.expression.indivisible.identifiers.NodeIdentifier;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import parser.PatternConstants;

public class NodeHowIzI extends Node {

    private NodeIdentifier functionName;
    private Node varsInit;
    private NodeStatements statements = new NodeStatements();
    private NodeReturn nodeReturn = new NodeReturn();

    public NodeHowIzI() {
        addChild(null);
        addChild(null);
        addChild(getStatements());
        addChild(getNodeReturn());
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.HOW_IZ_I);
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {

    }

    @Override
    public void checkAndFixSemantic() {
        Scope scope = new Scope(getScope());
    }

    public void setFunctionName(NodeIdentifier functionName) {
        getChildes().set(0, functionName);
        this.functionName = functionName;
    }

    public NodeIdentifier getFunctionName() {
        return functionName;
    }

    public void setVarsInit(Node varsInit) {
        getChildes().set(1, varsInit);
        this.varsInit = varsInit;
    }

    public Node getVarsInit() {
        return varsInit;
    }

    public NodeStatements getStatements() {
        return statements;
    }

    public void setStatements(NodeStatements statements) {
        getChildes().set(2, statements);
        this.statements = statements;
    }

    public NodeReturn getNodeReturn() {
        return nodeReturn;
    }

    public void setNodeReturn(NodeReturn nodeReturn) {
        getChildes().set(3, nodeReturn);
        this.nodeReturn = nodeReturn;
    }
}
