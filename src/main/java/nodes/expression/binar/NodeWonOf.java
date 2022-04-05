package nodes.expression.binar;

import nodes.Node;
import parser.PatternConstants;

public class NodeWonOf extends NodeBinaryExpression {
    public NodeWonOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.WON_OF);
    }
}
