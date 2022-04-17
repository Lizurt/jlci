package nodes.expression.binar;

import nodes.expression.NodeExpression;

import nodes.Node;
import parser.PatternConstants;

public class NodeProduktOf extends NodeBinaryExpression {
    public NodeProduktOf(NodeExpression leftOperand, NodeExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.PRODUKT_OF);
    }
}
