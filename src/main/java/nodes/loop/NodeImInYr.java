package nodes.loop;

import nodes.Node;
import nodes.NodeStatements;
import nodes.expression.NodeExpression;
import parser.PatternConstants;
import nodes.expression.indivisible.NodeIdentifier;

public class NodeImInYr extends Node {

    private NodeIdentifier loopName;
    private Node afterLoopAction;
    private Node varsInit;
    private NodeExpression whileCondition;
    private NodeStatements statements = new NodeStatements();

    public NodeImInYr() {
        addChild(null);
        addChild(null);
        addChild(null);
        addChild(null);
        addChild(getStatements());
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.IM_IN_YR);
    }

    public NodeIdentifier getLoopName() {
        return loopName;
    }

    public void setLoopName(NodeIdentifier loopName) {
        getChildes().set(0, loopName);
        this.loopName = loopName;
    }

    public Node getAfterLoopAction() {
        return afterLoopAction;
    }

    public void setAfterLoopAction(Node afterLoopAction) {
        getChildes().set(1, afterLoopAction);
        this.afterLoopAction = afterLoopAction;
    }

    public Node getVarsInit() {
        return varsInit;
    }

    public void setVarsInit(Node varsInit) {
        getChildes().set(2, varsInit);
        this.varsInit = varsInit;
    }

    public NodeExpression getWhileCondition() {
        return whileCondition;
    }

    public void setWhileCondition(NodeExpression whileCondition) {
        getChildes().set(3, whileCondition);
        this.whileCondition = whileCondition;
    }

    public NodeStatements getStatements() {
        return statements;
    }

    public void setStatements(NodeStatements statements) {
        getChildes().set(4, statements);
        this.statements = statements;
    }
}
