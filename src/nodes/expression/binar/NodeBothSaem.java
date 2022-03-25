package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

import java.util.HashMap;

public class NodeBothSaem extends NodeBinaryExpression {
    public NodeBothSaem(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.parserPred.get(PatternConstants.BOTH_SAEM);
    }
}
