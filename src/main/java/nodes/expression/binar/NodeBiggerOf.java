package nodes.expression.binar;

import nodes.Node;
import parser.PatternConstants;

public class NodeBiggerOf extends NodeBinaryExpression {
    public NodeBiggerOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.BIGGER_OF);
    }
}