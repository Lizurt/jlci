package translator.cplusparser;

import nodes.Node;
import nodes.expression.NodeExpression;
import nodes.io.NodeVisible;
import parser.PatternConstants;

public class CPlusPlusParserOut extends CPlusPartialParser {
    public CPlusPlusParserOut(CPlusParser mainParser) {
        super(mainParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.VISIBLE), false, true);
        NodeExpression expression = getMainParser().parseExpression();
        return new NodeVisible(expression);
    }
}
