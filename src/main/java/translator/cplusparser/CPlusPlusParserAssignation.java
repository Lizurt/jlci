package translator.cplusparser;

import nodes.Node;
import nodes.expression.indivisible.identifiers.NodeVariable;
import parser.PatternConstants;

import java.util.InputMismatchException;

public class CPlusPlusParserAssignation extends CPlusPartialParser {
    public CPlusPlusParserAssignation(CPlusParser cPlusParser) {
        super(cPlusParser);
    }

    @Override
    public Node parse() {
        getMainParser().parse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.R), true, true);
        if (!(getMainParser().lastExpressionToken instanceof NodeVariable)) {
            throw new InputMismatchException(
                    "Only variables are assignable. Tried to assign ["
                            + getMainParser().parseExpression().toString()
                            + "] value to [" + getMainParser().lastExpressionToken.toString() + "]."
            );
        }
        return getMainParser().parseAssignation((NodeVariable) getMainParser().lastExpressionToken);
    }
}
