package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeBothOf extends NodeBinaryExpression {
    public NodeBothOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.parserPred.get(PatternConstants.BOTH_OF);
    }
}
