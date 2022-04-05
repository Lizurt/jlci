package nodes.expression.binar;

import nodes.Node;
import parser.PatternConstants;

public class NodeEitherOf extends NodeBinaryExpression {
    public NodeEitherOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.EITHER_OF);
    }
}
