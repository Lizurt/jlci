package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeBiggerOf extends NodeBinaryExpression {
    public NodeBiggerOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.parserPred.get(PatternConstants.BIGGER_OF);
    }
}
