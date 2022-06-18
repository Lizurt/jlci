package translator.cplusparser;

import nodes.Node;
import nodes.expression.indivisible.identifiers.NodeIdentifier;
import nodes.io.NodeGimmeh;
import parser.PatternConstants;

public class CPlusPlusParserIn extends CPlusPartialParser {
    public CPlusPlusParserIn(CPlusParser mainParser) {
        super(mainParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.GIMMEH), false, true);
        NodeIdentifier identifier = getMainParser().parseVariable();
        return new NodeGimmeh(identifier);
    }
}
