package nodes.io;

import nodes.Node;
import nodes.expression.NodeExpression;

public class NodeORly extends Node {

    private Node condition;
    private NodeYaRly nodeYarly;
    private NodeNoWai nodeNoWai;

    public NodeORly() {
        addChild(null);
        addChild(null);
        addChild(null);
    }

    @Override
    public String toString() {
        return "O RLY?";
    }

    public Node getCondition() {
        return condition;
    }

    public void setCondition(Node condition) {
        getChildes().set(0, condition);
        this.condition = condition;
    }

    public NodeYaRly getNodeYarly() {
        return nodeYarly;
    }

    public void setNodeYaRly(NodeYaRly nodeYarly) {
        getChildes().set(1, nodeYarly);
        this.nodeYarly = nodeYarly;
    }

    public NodeNoWai getNodeNoWai() {
        return nodeNoWai;
    }

    public void setNodeNoWai(NodeNoWai nodeNoWai) {
        getChildes().set(2, nodeNoWai);
        this.nodeNoWai = nodeNoWai;
    }
}
