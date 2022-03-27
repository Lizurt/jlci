package parser;

import nodes.Node;
import nodes.io.NodeNoWai;
import nodes.io.NodeORly;
import nodes.io.NodeYaRly;

import java.util.InputMismatchException;

public class ParserIf extends PartialParser {
    public ParserIf(Parser mainParser) {
        super(mainParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.O_RLY, true, true);
        NodeORly nodeORly = new NodeORly();
        nodeORly.setCondition(getMainParser().lastExpressionToken);
        if (getMainParser().isParse(PatternConstants.YA_RLY, true, true)) {
            getMainParser().parse(PatternConstants.YA_RLY, true, true);
            NodeYaRly nodeYaRly = new NodeYaRly();
            nodeORly.setNodeYaRly(nodeYaRly);
            while (true) {
                if (getMainParser().isParse(PatternConstants.NO_WAI, true, true)) {
                    break;
                }
                if (getMainParser().isParse(PatternConstants.OIC, true, true)) {
                    break;
                }
                Node childStatement = getMainParser().tokenizeStatementAndProceed();
                nodeYaRly.addChild(childStatement);
            }
        }
        if (getMainParser().isParse(PatternConstants.NO_WAI, true, true)) {
            getMainParser().parse(PatternConstants.NO_WAI, true, true);
            NodeNoWai nodeNoWai = new NodeNoWai();
            nodeORly.setNodeNoWai(nodeNoWai);
            while (true) {
                if (getMainParser().isParse(PatternConstants.OIC, true, true)) {
                    break;
                }
                Node childStatement = getMainParser().tokenizeStatementAndProceed();
                nodeNoWai.addChild(childStatement);
            }
        }
        if (getMainParser().isParse(PatternConstants.OIC, true, true)) {
            getMainParser().parse(PatternConstants.OIC, true, true);
            return nodeORly;
        }
        throw new InputMismatchException(
                "Couldn't find " + PatternConstants.OIC + " node at position: " + getMainParser().currPos + "."
        );
    }
}
