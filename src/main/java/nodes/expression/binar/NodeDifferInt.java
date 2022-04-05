package nodes.expression.binar;

import nodes.Node;
import parser.PatternConstants;

public class NodeDifferInt extends NodeBinaryExpression {
    public NodeDifferInt(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.DIFFRINT);

    }
}
