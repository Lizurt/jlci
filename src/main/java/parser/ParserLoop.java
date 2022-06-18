package parser;

import nodes.Node;
import nodes.NodeAssignation;
import nodes.expression.NodeExpression;
import nodes.expression.indivisible.identifiers.NodeIdentifier;
import nodes.expression.indivisible.identifiers.NodeLabel;
import nodes.loop.NodeImInYr;

import java.util.InputMismatchException;

public class ParserLoop extends PartialParser {
    public ParserLoop(Parser mainParser) {
        super(mainParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.IM_IN_YR, true, true);
        NodeImInYr nodeImInYr = new NodeImInYr();
        NodeLabel loopName = getMainParser().parseLabel();
        nodeImInYr.setLoopName(loopName);
        Node afterLoopAction = getMainParser().tokenizeStatementAndProceed();
        nodeImInYr.setAfterLoopAction(afterLoopAction);
        if (!getMainParser().isParse(PatternConstants.YR, true, true)) {
            throw new InputMismatchException("Missing " + PatternConstants.YR + " section in a loop at position: " + getMainParser().currPos + ".");
        }
        getMainParser().parse(PatternConstants.YR, true, true);
        Node varsInitNode = getMainParser().tokenizeStatementAndProceed();
        nodeImInYr.setVarInit((NodeAssignation) varsInitNode);
        if (!getMainParser().isParse(PatternConstants.WILE, true, true)) {
            throw new InputMismatchException(
                    "Missing " + PatternConstants.TIL + "/" + PatternConstants.WILE
                            + " section in a loop at position: " + getMainParser().currPos + "."
            );
        }
        getMainParser().parse(PatternConstants.WILE, true, true);
        NodeExpression loopCondition = getMainParser().parseExpression();
        nodeImInYr.setWhileCondition(loopCondition);
        while (!getMainParser().isParse(PatternConstants.IM_OUTTA_YR, true, true)) {
            Node loopChildStatement = getMainParser().tokenizeStatementAndProceed();
            nodeImInYr.getStatements().addChild(loopChildStatement);
        }
        if (!getMainParser().isParse(PatternConstants.IM_OUTTA_YR, true, true)) {
            throw new InputMismatchException(
                    "Missing " + PatternConstants.IM_OUTTA_YR + " section in a loop at position: " + getMainParser().currPos + "."
            );
        }
        getMainParser().parse(PatternConstants.IM_OUTTA_YR, true, true);
        NodeIdentifier loopEndName = getMainParser().parseLabel();
        if (!loopEndName.getName().equals(nodeImInYr.getLoopName().getName())) {
            throw new InputMismatchException(
                    "Wrong label for a loop with a label: " + nodeImInYr
                            + ". Found: \"" + loopEndName.getName() + "\"."
            );
        }
        return nodeImInYr;
    }
}
