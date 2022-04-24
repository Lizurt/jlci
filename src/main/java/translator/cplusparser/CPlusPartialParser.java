package translator.cplusparser;

import nodes.Node;

public abstract class CPlusPartialParser {
    private final CPlusParser mainParser;

    public CPlusPartialParser(CPlusParser mainParser) {
        this.mainParser = mainParser;
    }

    public abstract Node parse();

    public CPlusParser getMainParser() {
        return mainParser;
    }
}
