package parser;

import nodes.Node;
import nodes.expression.indivisible.identifiers.NodeIdentifier;

import java.util.InputMismatchException;

public class ParserAssignation extends PartialParser {
    public ParserAssignation(Parser mainParser) {
        super(mainParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.R, true, true);
        if (!(getMainParser().lastExpressionToken instanceof NodeIdentifier)) {
            throw new InputMismatchException(
                    "Only variables are assignable. Tried to assign ["
                    + getMainParser().parseExpression().toString()
                    + "] value to [" + getMainParser().lastExpressionToken.toString() + "]."
            );
        }
        return getMainParser().parseAssignation((NodeIdentifier) getMainParser().lastExpressionToken);
    }
}
