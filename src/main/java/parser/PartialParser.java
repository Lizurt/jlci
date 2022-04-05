package parser;

import nodes.Node;
import parser.Parser;

public abstract class PartialParser {
    private final Parser mainParser;

    public PartialParser(Parser mainParser) {
        this.mainParser = mainParser;
    }

    public abstract Node parse();

    public Parser getMainParser() {
        return mainParser;
    }
}
