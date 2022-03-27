package parser;

import nodes.Node;
import nodes.expression.NodeExpression;
import nodes.expression.PatternConstants;
import nodes.io.NodeVisible;

public class ParserVisible extends PartialParser {
    public ParserVisible(Parser mainParser) {
        super(mainParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.VISIBLE, true, true);
        NodeExpression expression = getMainParser().parseExpression();
        getMainParser().lastExpressionToken = expression;
        return new NodeVisible(expression);
    }
}
