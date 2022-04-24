package translator.cplusparser;

import nodes.Node;
import nodes.NodeAssignation;
import nodes.NodeRoot;
import nodes.expression.NodeExpression;
import nodes.expression.binar.*;
import nodes.expression.indivisible.NodeNumber;
import nodes.expression.indivisible.identifiers.NodeLabel;
import nodes.expression.indivisible.identifiers.NodeVariable;
import parser.PatternConstants;

import java.util.InputMismatchException;

public class CPlusParser {
    String rawProgram;
    int currPos = 0;
    NodeExpression lastExpressionToken;

    private final CPlusPlusParserIf cPlusParserIf = new CPlusPlusParserIf(this);
    private final CPlusPlusParserLoop cPlusParserLoop = new CPlusPlusParserLoop(this);
    private final CPlusPlusParserOut cPlusParserOut = new CPlusPlusParserOut(this);
    private final CPlusPlusParserIn cPlusParserIn = new CPlusPlusParserIn(this);
    private final CPlusPlusParserAssignation cPlusParserAssignation = new CPlusPlusParserAssignation(this);

    public CPlusParser(final String rawProgram) {
        this.rawProgram = rawProgram;
    }

    public NodeRoot parse() {
        NodeRoot root = new NodeRoot();
        while (currPos < rawProgram.length()) {
            if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.KTHXBYE), true, false)) {
                return root;
            }
            Node statement = tokenizeStatementAndProceed();
            if (statement == null) {
                continue;
            }
            root.addChild(statement);
        }
        throw new InputMismatchException("\"" + PatternConstants.KTHXBYE + "\" is missing.");
    }

    protected Node tokenizeStatementAndProceed() {
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.O_RLY), true, true)) {
            return cPlusParserIf.parse();
        }
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.IM_IN_YR), true, true)) {
            return cPlusParserLoop.parse();
        }
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.GIMMEH), false, true)) {
            return cPlusParserIn.parse();
        }
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.VISIBLE), false, true)) {
            return cPlusParserOut.parse();
        }
        parseExpression();
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.R), true, true)) {
            return cPlusParserAssignation.parse();
        }
        if (isParse(";", true, true)) {
            return null;
        }
        return lastExpressionToken;
    }

    NodeExpression parseExpression() {
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.SUM_OF), true, true)) {
            parse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.SUM_OF), true, true);
            return parseExpression(new NodeSumOf(lastExpressionToken, null));
        }
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.DIFF_OF), true, true)) {
            parse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.DIFF_OF), true, true);
            return parseExpression(new NodeDiffOf(lastExpressionToken, null));
        }
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.PRODUKT_OF), true, true)) {
            parse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.PRODUKT_OF), true, true);
            return parseExpression(new NodeProduktOf(lastExpressionToken, null));
        }
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.QUOSHUNT_OF), true, true)) {
            parse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.QUOSHUNT_OF), true, true);
            return parseExpression(new NodeQuoshuntOf(lastExpressionToken, null));
        }
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.BOTH_OF), true, true)) {
            parse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.BOTH_OF), true, true);
            return parseExpression(new NodeBothOf(lastExpressionToken, null));
        }
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.EITHER_OF), true, true)) {
            parse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.EITHER_OF), true, true);
            return parseExpression(new NodeEitherOf(lastExpressionToken, null));
        }
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.BOTH_SAEM), true, true)) {
            parse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.BOTH_SAEM), true, true);
            return parseExpression(new NodeBothSaem(lastExpressionToken, null));
        }
        if (isParse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.DIFFRINT), true, true)) {
            parse(PatternConstants.astTreeSoutDictionary.get(PatternConstants.DIFFRINT), true, true);
            return parseExpression(new NodeDiffrint(lastExpressionToken, null));
        }

        int fakeWhitespacesSkipLen = 0;
        while (Character.isWhitespace(rawProgram.charAt(currPos + fakeWhitespacesSkipLen))
                || rawProgram.charAt(currPos + fakeWhitespacesSkipLen) == ';') {
            fakeWhitespacesSkipLen++;
        }
        if (
                Character.isDigit(rawProgram.charAt(currPos + fakeWhitespacesSkipLen))
                        || rawProgram.charAt(currPos + fakeWhitespacesSkipLen) == '.'
                        || rawProgram.charAt(currPos + fakeWhitespacesSkipLen) == '-'
        ) {
            return parseNumber();
        }

        if (Character.isLetter(rawProgram.charAt(currPos + fakeWhitespacesSkipLen))) {
            return parseVariable();
        }

        throw new InputMismatchException(
                "Unable to parse an expression starting with \""
                        + rawProgram.charAt(currPos) + "\" at position: " + currPos + "."
        );
    }

    NodeExpression parseExpression(NodeBinaryExpression outerExpression) {
//        outerExpression.setLeftOperand(parseExpression());
//        if (isParse(PatternConstants.AN, true, true)) {
//            parse(PatternConstants.AN, true, true);
//        } else if (!outerExpression.isANIgnorable()) {
//            throw new InputMismatchException(PatternConstants.AN + " is missing in the expression.");
//        }
        outerExpression.setRightOperand(parseExpression());
        lastExpressionToken = outerExpression;
        return outerExpression;
    }

    NodeAssignation parseAssignation(NodeVariable variable) {
        System.out.println("var: " + variable);
        return new NodeAssignation(variable, parseExpression());
    }

    protected NodeNumber parseNumber() {
        consumeWhitespaces();
        boolean negate = false;
        if (rawProgram.charAt(currPos) == '-') {
            negate = true;
            currPos++;
        }
        int fromPos = currPos;
        boolean alreadyHadDot = false;
        while (true) {
            if (Character.isDigit(rawProgram.charAt(currPos))) {
                currPos++;
                continue;
            }
            if (rawProgram.charAt(currPos) == '.') {
                if (alreadyHadDot) {
                    throw new InputMismatchException(
                            "A number can't have multiple dots. Position: " + currPos + "."
                    );
                }
                alreadyHadDot = true;
                currPos++;
                continue;
            }
            break;
        }
        float value = Float.parseFloat(rawProgram.substring(fromPos, currPos));
        if (negate) {
            value = -value;
        }
        NodeNumber result = new NodeNumber(value);
        lastExpressionToken = result;
        return result;
    }

    public NodeLabel parseLabel() {
        consumeWhitespaces();
        int fromPos = currPos;
        parseIdentifierWhileLetterOrDigit();
        if (Character.isWhitespace(rawProgram.charAt(currPos))) {
            NodeLabel result = new NodeLabel(rawProgram.substring(fromPos, currPos));
            lastExpressionToken = result;
            return result;
        }
        throw new InputMismatchException("Vars cannot contain such symbols: " + rawProgram.charAt(currPos) + ".");
    }

    public NodeVariable parseVariable() {
        consumeWhitespaces();
        int fromPos = currPos;
        parseIdentifierWhileLetterOrDigit();
        if (Character.isWhitespace(rawProgram.charAt(currPos))) {
            NodeVariable result;
            if (rawProgram.charAt(currPos - 1) == ';') {
                result = new NodeVariable(rawProgram.substring(fromPos, currPos - 1));
            } else {
                result = new NodeVariable(rawProgram.substring(fromPos, currPos));
            }
            lastExpressionToken = result;
            return result;
        }
        throw new InputMismatchException("Vars cannot contain such symbols: " + rawProgram.charAt(currPos) + ".");
    }

    private void parseIdentifierWhileLetterOrDigit() {
        if (Character.isLetter(rawProgram.charAt(currPos))) {
            currPos++;
        } else {
            throw new InputMismatchException(
                    "Identifiers cannot start with something except letters. Met: " + rawProgram.charAt(currPos) + "."
            );
        }
        while (Character.isLetterOrDigit(rawProgram.charAt(currPos)) || rawProgram.charAt(currPos) == ';') {
            currPos++;
        }
    }

    void parse(String text, boolean requireLeftWhitespaces, boolean requireRightWhitespaces) {
        if (isParse(text, requireLeftWhitespaces, requireRightWhitespaces)) {
            currPos += consumeWhitespaces() + text.length();
            return;
        }
        throw new InputMismatchException("Parsing error.");
    }

    public boolean isParse(String text, boolean requireLeftWhitespaces, boolean requireRightWhitespaces) {
        int leftWhitespacesAmt = 0;
        while (Character.isWhitespace(rawProgram.charAt(currPos + leftWhitespacesAmt))) {
            leftWhitespacesAmt++;
        }

        if (requireLeftWhitespaces && leftWhitespacesAmt == 0) {
            return false;
        }

        for (int i = 0; i < text.length(); i++) {
            if (rawProgram.charAt(i + currPos + leftWhitespacesAmt) != text.charAt(i)) {
                return false;
            }
        }

        return !requireRightWhitespaces
                || Character.isWhitespace(rawProgram.charAt(text.length() + leftWhitespacesAmt + currPos));
    }

    int consumeWhitespaces() {
        int consumedAmt = 0;
        while (Character.isWhitespace(rawProgram.charAt(currPos)) || rawProgram.charAt(currPos) == ';') {
            consumedAmt++;
            currPos++;
        }
        return consumedAmt;
    }
}
