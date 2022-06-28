package parser;

import nodes.Node;
import nodes.expression.indivisible.identifiers.NodeIdentifier;
import nodes.function.NodeIIZ;

import java.util.InputMismatchException;

public class ParserCallingFunction extends PartialParser {
    public ParserCallingFunction(Parser mainParser) {
        super(mainParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.I_IZ, true, true);
        NodeIIZ nodeIIZ = new NodeIIZ();
        NodeIdentifier functionName = getMainParser().parseVariable();
        nodeIIZ.setFunctionName(functionName);

        if (!getMainParser().isParse(PatternConstants.YR, true, true)) {
            throw new InputMismatchException("Missing " + PatternConstants.YR + " section in a function at position: " + getMainParser().currPos + ".");
        }
        getMainParser().parse(PatternConstants.YR, true, true);
        Node varsInitNode = getMainParser().tokenizeStatementAndProceed();
        nodeIIZ.setVarsInit(varsInitNode);

        if (!getMainParser().isParse(PatternConstants.MKAY, true, true)) {
            throw new InputMismatchException(
                    "Missing " + PatternConstants.MKAY + " section in a function at position: " + getMainParser().currPos + "."
            );
        }

        getMainParser().parse(PatternConstants.MKAY, true, true);

        return nodeIIZ;
    }
}
