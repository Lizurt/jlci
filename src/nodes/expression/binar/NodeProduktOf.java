package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeProduktOf extends NodeBinaryExpression {
    public NodeProduktOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.parserPred.get(PatternConstants.PRODUKT_OF);
    }
}
