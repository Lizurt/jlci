package parser;

import nodes.Node;
import nodes.expression.indivisible.NodeIdentifier;
import nodes.function.NodeHowIzI;

import java.util.InputMismatchException;

public class ParserFunction extends PartialParser {
    public ParserFunction(Parser mainParser) {
        super(mainParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.HOW_IZ_I, true, true);
        NodeHowIzI nodeHowIzI = new NodeHowIzI();
        NodeIdentifier functionName = getMainParser().parseIdentifier();
        nodeHowIzI.setFunctionName(functionName);

        if (!getMainParser().isParse(PatternConstants.YR, true, true)) {
            throw new InputMismatchException("Missing " + PatternConstants.YR + " section in a function at position: " + getMainParser().currPos + ".");
        }
        getMainParser().parse(PatternConstants.YR, true, true);
        Node varsInitNode = getMainParser().tokenizeStatementAndProceed();
        nodeHowIzI.setVarsInit(varsInitNode);
        while (true) {
            if (getMainParser().isParse(PatternConstants.IF_U_SAY_SO, true, true)) {
                break;
            }
            Node funcChildStatement = getMainParser().tokenizeStatementAndProceed();
            nodeHowIzI.getStatements().addChild(funcChildStatement);
        }
        if (!getMainParser().isParse(PatternConstants.IF_U_SAY_SO, true, true)) {
            throw new InputMismatchException(
                    "Missing " + PatternConstants.IF_U_SAY_SO + " section in a function at position: " + getMainParser().currPos + "."
            );
        }
        if(getMainParser().isParse(PatternConstants.IF_U_SAY_SO, true, true)) {
            getMainParser().parse(PatternConstants.IF_U_SAY_SO, true, true);

            return nodeHowIzI;
        }
        throw new InputMismatchException(
                "Couldn't find " + PatternConstants.OIC + " node at position: " + getMainParser().currPos + "."
        );
    }
}
