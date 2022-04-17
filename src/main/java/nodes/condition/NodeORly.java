package nodes.condition;

import compiler.Scope;
import nodes.Node;
import nodes.expression.NodeExpression;
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
