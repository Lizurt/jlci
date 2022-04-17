package nodes.expression.binar;

import nodes.expression.NodeExpression;

import nodes.Node;
import parser.PatternConstants;

public class NodeBothSaem extends NodeBinaryExpression {
    public NodeBothSaem(NodeExpression leftOperand, NodeExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.BOTH_SAEM);
    }
}
