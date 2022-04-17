package nodes.expression.binar;

import nodes.expression.NodeExpression;

import nodes.Node;
import parser.PatternConstants;

public class NodeSmallerOf extends NodeBinaryExpression {
    public NodeSmallerOf(NodeExpression leftOperand, NodeExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.SMALLER_OF);
    }
}
