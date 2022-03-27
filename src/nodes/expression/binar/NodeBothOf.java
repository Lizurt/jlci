package nodes.expression.binar;

import nodes.Node;
import parser.PatternConstants;

public class NodeBothOf extends NodeBinaryExpression {
    public NodeBothOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.BOTH_OF);
    }
}
