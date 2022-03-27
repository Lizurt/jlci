package parser;

import nodes.Node;
import nodes.expression.NodeExpression;
import nodes.expression.indivisible.NodeIdentifier;
import nodes.io.NodeImInYr;

import java.util.InputMismatchException;

public class ParserLoop extends PartialParser {
    public ParserLoop(Parser mainParser) {
        super(mainParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.IM_IN_YR, true, true);
        NodeImInYr nodeImInYr = new NodeImInYr();
        NodeIdentifier loopName = getMainParser().parseIdentifier();
        nodeImInYr.setLoopName(loopName);
        Node afterLoopAction = getMainParser().tokenizeStatementAndProceed();
        nodeImInYr.setAfterLoopAction(afterLoopAction);
        if (!getMainParser().isParse(PatternConstants.YR, true, true)) {
            throw new InputMismatchException("Missing " + PatternConstants.YR + " section in a loop at position: " + getMainParser().currPos + ".");
        }
        getMainParser().parse(PatternConstants.YR, true, true);
        Node varsInitNode = getMainParser().tokenizeStatementAndProceed();
        nodeImInYr.setVarsInit(varsInitNode);
        if (!getMainParser().isParse(PatternConstants.WILE, true, true)) {
            throw new InputMismatchException(
                    "Missing " + PatternConstants.TIL + "/" + PatternConstants.WILE
                            + " section in a loop at position: " + getMainParser().currPos + "."
            );
        }
        getMainParser().parse(PatternConstants.WILE, true, true);
        NodeExpression loopCondition = getMainParser().parseExpression();
        nodeImInYr.setWhileCOndition(loopCondition);
        while (true) {
            if (getMainParser().isParse(PatternConstants.IM_OUTTA_YR, true, true)) {
                break;
            }
            Node loopChildStatement = getMainParser().tokenizeStatementAndProceed();
            nodeImInYr.addChild(loopChildStatement);
        }
        if (!getMainParser().isParse(PatternConstants.IM_OUTTA_YR, true, true)) {
            throw new InputMismatchException(
                    "Missing " + PatternConstants.IM_OUTTA_YR + " section in a loop at position: " + getMainParser().currPos + "."
            );
        }
        getMainParser().parse(PatternConstants.IM_OUTTA_YR, true, true);
        NodeIdentifier loopEndName = getMainParser().parseIdentifier();
        if (!loopEndName.getIdentifier().equals(nodeImInYr.getLoopName().getIdentifier())) {
            throw new InputMismatchException(
                    "Wrong label for a loop with a label: " + nodeImInYr
                            + ". Found: " + loopEndName.getIdentifier() + "."
            );
        }
        return nodeImInYr;
    }
}
