package nodes;

import nodes.expression.NodeExpression;
import parser.PatternConstants;
import nodes.expression.indivisible.identifiers.NodeIdentifier;

public class NodeAssignation extends Node {
    private NodeExpression expression;
    private NodeIdentifier identifier;

    public NodeAssignation(NodeIdentifier identifier, NodeExpression expression) {
        this.identifier = identifier;
        this.expression = expression;
        addChild(identifier);
        addChild(expression);
    }

    public NodeExpression getExpression() {
        return expression;
    }

    public void setExpression(NodeExpression expression) {
        this.expression = expression;
        getChildes().set(1, expression);
    }

    public NodeIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(NodeIdentifier identifier) {
        this.identifier = identifier;
        getChildes().set(0, identifier);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.R);
    }

    @Override
    public void checkAndFixSemantic() {
        expression.setScope(getScope());
        identifier.setScope(getScope());
        expression.checkAndFixSemantic();
        if (identifier.getScope().tryGetVariableByName(identifier.getName()) != null) {
            return;
        }
        identifier.getScope().unsafelyAddIdentifierToScope(identifier.getName());
    }
}
