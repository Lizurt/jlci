package nodes.function;

import nodes.Node;
import nodes.NodeStatements;
import nodes.expression.indivisible.NodeIdentifier;

public class NodeHowIzI extends Node {

    private NodeIdentifier functionName;
    private Node varsInit;
    private NodeStatements statements = new NodeStatements();

    @Override
    public String toString() {
        return null;
    }
    public void setFunctionName(NodeIdentifier functionName) {
        getChildes().set(0, functionName);
        this.functionName = functionName;
    }

    public NodeIdentifier getFunctionName() {
        return functionName;
    }

    public void setVarsInit(Node varsInit) {
        getChildes().set(2, varsInit);
        this.varsInit = varsInit;
    }

    public Node getVarsInit() {
        return varsInit;
    }

    public NodeStatements getStatements() {
        return statements;
    }

    public void setStatements(NodeStatements statements) {
        getChildes().set(3, statements);
        this.statements = statements;
    }
}
