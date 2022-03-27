package nodes.expression.binar;

import nodes.Node;
import parser.PatternConstants;

public class NodeQuoshuntOf extends NodeBinaryExpression {
    public NodeQuoshuntOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.QUOSHUNT_OF);
    }
}
