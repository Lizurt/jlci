package parser;

import nodes.Node;
import nodes.expression.indivisible.NodeIdentifier;
import nodes.io.NodeGimmeh;

public class ParserGimmeh extends PartialParser {
    public ParserGimmeh(Parser mainParser) {
        super(mainParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.GIMMEH, true, true);
        NodeIdentifier identifier = getMainParser().parseIdentifier();
        return new NodeGimmeh(identifier);
    }
}
