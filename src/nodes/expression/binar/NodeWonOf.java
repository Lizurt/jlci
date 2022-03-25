package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeWonOf extends NodeBinaryExpression {
    public NodeWonOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.parserPred.get(PatternConstants.WON_OF);
    }
}
