package parser;

import nodes.Node;
import nodes.expression.PatternConstants;

public class ParserAssignation extends PartialParser {
    public ParserAssignation(Parser mainParser) {
        super(mainParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.R, true, true);
        return getMainParser().parseAssignation(getMainParser().lastIdentifierToken);
    }
}
