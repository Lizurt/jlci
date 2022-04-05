package nodes.condition;

import nodes.Node;
import parser.PatternConstants;

public class NodeORly extends Node {

    private Node condition;
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

    public Node getCondition() {
        return condition;
    }

    public void setCondition(Node condition) {
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