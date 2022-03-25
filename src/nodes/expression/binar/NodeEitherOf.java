package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeEitherOf extends NodeBinaryExpression {
    public NodeEitherOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.parserPred.get(PatternConstants.EITHER_OF);
    }
}
