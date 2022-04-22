package nodes.function;

import nodes.Node;
import nodes.expression.indivisible.NodeIdentifier;
import parser.PatternConstants;

public class NodeIIZ extends Node {

    private NodeIdentifier functionName;
    private Node varsInit;

    public NodeIIZ() {
        addChild(null);
        addChild(null);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.I_IZ);
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
}
