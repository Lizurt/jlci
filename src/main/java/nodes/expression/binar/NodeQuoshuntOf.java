package nodes.expression.binar;

import nodes.expression.NodeExpression;

import nodes.Node;
import parser.PatternConstants;

public class NodeQuoshuntOf extends NodeBinaryExpression {
    public NodeQuoshuntOf(NodeExpression leftOperand, NodeExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.QUOSHUNT_OF);
    }
}
