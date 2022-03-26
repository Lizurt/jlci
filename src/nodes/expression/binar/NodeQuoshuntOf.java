package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeQuoshuntOf extends NodeBinaryExpression {
    public NodeQuoshuntOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.parserPred.get(PatternConstants.QUOSHUNT_OF);
    }
}
