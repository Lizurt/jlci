package nodes.io;

import nodes.Node;
import nodes.expression.NodeExpression;
import nodes.expression.indivisible.NodeIdentifier;

public class NodeImInYr extends Node {

    private NodeIdentifier loopName;
    private Node afterLoopAction;
    private Node varsInit;
    private NodeExpression whileCOndition;

    public NodeImInYr() {
        addChild(null);
        addChild(null);
        addChild(null);
        addChild(null);
    }

    @Override
    public String toString() {
        return "IM IN YR";
    }

    public NodeIdentifier getLoopName() {
        return loopName;
    }

    public void setLoopName(NodeIdentifier loopName) {
        getChildes().set(0, loopName);
        this.loopName = loopName;
    }

    public Node getAfterLoopAction() {
        return afterLoopAction;
    }

    public void setAfterLoopAction(Node afterLoopAction) {
        getChildes().set(1, afterLoopAction);
        this.afterLoopAction = afterLoopAction;
    }

    public Node getVarsInit() {
        return varsInit;
    }

    public void setVarsInit(Node varsInit) {
        getChildes().set(2, varsInit);
        this.varsInit = varsInit;
    }

    public NodeExpression getWhileCOndition() {
        return whileCOndition;
    }

    public void setWhileCOndition(NodeExpression whileCOndition) {
        getChildes().set(3, whileCOndition);
        this.whileCOndition = whileCOndition;
    }
}
