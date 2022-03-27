package parser;

import nodes.*;
import nodes.expression.PatternConstants;
import nodes.expression.NodeExpression;
import nodes.expression.indivisible.NodeIdentifier;
import nodes.expression.indivisible.NodeNumber;
import nodes.expression.binar.*;
import nodes.expression.unar.NodeUnaryExpression;

import java.util.InputMismatchException;

public class Parser {
    final String rawProgram;
    int currPos = 0;
    NodeExpression lastExpressionToken;

    private ParserIf parserIf = new ParserIf(this);
    private ParserLoop parserLoop = new ParserLoop(this);
    private ParserVisible parserVisible = new ParserVisible(this);
    private ParserGimmeh parserGimmeh = new ParserGimmeh(this);
    private ParserAssignation parserAssignation = new ParserAssignation(this);

    public Parser(final String rawProgram) {
        this.rawProgram = rawProgram;
    }

    public Node parse() {
        if (!isParse(PatternConstants.HAI, false, true)) {
            throw new InputMismatchException("\"" + PatternConstants.HAI + "\" is missing.");
        }
        parse(PatternConstants.HAI, false, true);
        NodeNumber version = parseNumber();
        NodeRoot root = new NodeRoot(String.valueOf(version.getValue()));

        while (currPos < rawProgram.length()) {
            if (isParse(PatternConstants.KTHXBYE, true, false)) {
                return root;
            }
            Node statement = tokenizeStatementAndProceed();
            root.addChild(statement);
        }
        throw new InputMismatchException("\"" + PatternConstants.KTHXBYE + "\" is missing.");
    }

    Node tokenizeStatementAndProceed() {
        if (isParse(PatternConstants.O_RLY, true, true)) {
            return parserIf.parse();
        }
        if (isParse(PatternConstants.IM_IN_YR, true, true)) {
            return parserLoop.parse();
        }
        if (isParse(PatternConstants.GIMMEH, true, true)) {
            return parserGimmeh.parse();
        }
        if (isParse(PatternConstants.VISIBLE, true, true)) {
            return parserVisible.parse();
        }
        parseIdentifier();
        if (isParse(PatternConstants.R, true, true)) {
            return parserAssignation.parse();
        }
        throw new InputMismatchException(
                "Unacceptable symbol \"" + rawProgram.charAt(currPos) + "\" at position " + currPos + "."
        );
    }

    NodeExpression parseExpression() {
        // ===== UNARY =====
        /*if (isParse("NOT", true, true)) {
            parse("NOT", true, true);
            return parseExpression(new TokenNot(null));
        }*/
        // ===== BINARY =====
        // --- math --
        if (isParse(PatternConstants.SUM_OF, true, true)) {
            parse(PatternConstants.SUM_OF, true, true);
            return parseExpression(new NodeSumOf(null, null));
        }
        if (isParse(PatternConstants.DIFF_OF, true, true)) {
            parse(PatternConstants.DIFF_OF, true, true);
            return parseExpression(new NodeDiffOf(null, null));
        }
        if (isParse(PatternConstants.PRODUKT_OF, true, true)) {
            parse(PatternConstants.PRODUKT_OF, true, true);
            return parseExpression(new NodeProduktOf(null, null));
        }
        if (isParse(PatternConstants.QUOSHUNT_OF, true, true)) {
            parse(PatternConstants.QUOSHUNT_OF, true, true);
            return parseExpression(new NodeQuoshuntOf(null, null));
        }
        if (isParse(PatternConstants.MOD_OF, true, true)) {
            parse(PatternConstants.MOD_OF, true, true);
            return parseExpression(new NodeModOf(null, null));
        }
        if (isParse(PatternConstants.BIGGER_OF, true, true)) {
            parse(PatternConstants.BIGGER_OF, true, true);
            return parseExpression(new NodeBiggerOf(null, null));
        }
        if (isParse(PatternConstants.SMALLER_OF, true, true)) {
            parse(PatternConstants.SMALLER_OF, true, true);
            return parseExpression(new NodeSmallerOf(null, null));
        }
        if (isParse(PatternConstants.BOTH_OF, true, true)) {
            parse(PatternConstants.BOTH_OF, true, true);
            return parseExpression(new NodeBothOf(null, null));
        }
        if (isParse(PatternConstants.EITHER_OF, true, true)) {
            parse(PatternConstants.EITHER_OF, true, true);
            return parseExpression(new NodeEitherOf(null, null));
        }
        if (isParse(PatternConstants.WON_OF, true, true)) {
            parse(PatternConstants.WON_OF, true, true);
            return parseExpression(new NodeWonOf(null, null));
        }
        if (isParse(PatternConstants.ALL_OF, true, true)) {
            parse(PatternConstants.ALL_OF, true, true);
            return parseExpression(new NodeAllOf(null, null));
        }
        if (isParse(PatternConstants.ANY_OF, true, true)) {
            parse(PatternConstants.ANY_OF, true, true);
            return parseExpression(new NodeAnyOf(null, null));
        }
        if (isParse(PatternConstants.BOTH_SAEM, true, true)) {
            parse(PatternConstants.BOTH_SAEM, true, true);
            return parseExpression(new NodeBothSaem(null, null));
        }
        if (isParse(PatternConstants.DIFFRINT, true, true)) {
            parse(PatternConstants.DIFFRINT, true, true);
            return parseExpression(new NodeDifferInt(null, null));
        }

        int fakeWhitespacesSkipLen = 0;
        while (Character.isWhitespace(rawProgram.charAt(currPos + fakeWhitespacesSkipLen))) {
            fakeWhitespacesSkipLen++;
        }
        if (
                Character.isDigit(rawProgram.charAt(currPos + fakeWhitespacesSkipLen))
                        || rawProgram.charAt(currPos + fakeWhitespacesSkipLen) == '.'
        ) {
            return parseNumber();
        }

        if (Character.isLetter(rawProgram.charAt(currPos + fakeWhitespacesSkipLen))) {
            return parseIdentifier();
        }

        throw new InputMismatchException(
                "Unable to parse an expression starting with \""
                        + rawProgram.charAt(currPos) + "\" at position: " + currPos + "."
        );
    }

    NodeExpression parseExpression(NodeBinaryExpression outerExpression) {
        outerExpression.setLeftOperand(parseExpression());
        if (isParse(PatternConstants.AN, true, true)) {
            parse(PatternConstants.AN, true, true);
        } else if (!outerExpression.isANIgnorable()) {
            throw new InputMismatchException(PatternConstants.AN + " is missing in the expression.");
        }
        outerExpression.setRightOperand(parseExpression());
        lastExpressionToken = outerExpression;
        return outerExpression;
    }

    NodeExpression parseExpression(NodeUnaryExpression outerExpression) {
        lastExpressionToken = outerExpression;
        return null;
    }

    NodeNumber parseNumber() {
        consumeWhitespaces();
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
        NodeNumber result = new NodeNumber(Double.parseDouble(rawProgram.substring(fromPos, currPos)));
        lastExpressionToken = result;
        return result;
    }

    NodeIdentifier parseIdentifier() {
        consumeWhitespaces();
        int fromPos = currPos;
        if (Character.isLetter(rawProgram.charAt(currPos))) {
            currPos++;
        } else {
            throw new InputMismatchException(
                    "Identifiers cannot start with something except letters. Met: " + rawProgram.charAt(currPos) + "."
            );
        }
        while (Character.isLetterOrDigit(rawProgram.charAt(currPos))) {
            currPos++;
        }
        if (Character.isWhitespace(rawProgram.charAt(currPos))) {
            NodeIdentifier result = new NodeIdentifier(rawProgram.substring(fromPos, currPos));
            lastExpressionToken = result;
            return result;
        }
        throw new InputMismatchException("Vars cannot contain such symbols: " + rawProgram.charAt(currPos) + ".");
    }

    NodeAssignation parseAssignation(NodeIdentifier identifier) {
        return new NodeAssignation(identifier, parseExpression());
    }

    boolean isParse(String text, boolean requireLeftWhitespaces, boolean requireRightWhitespaces) {
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

    void parse(String text, boolean requireLeftWhitespaces, boolean requireRightWhitespaces) {
        if (isParse(text, requireLeftWhitespaces, requireRightWhitespaces)) {
            currPos += consumeWhitespaces() + text.length();
            return;
        }
        throw new InputMismatchException("Parsing error.");
    }

    int consumeWhitespaces() {
        int consumedAmt = 0;
        while (Character.isWhitespace(rawProgram.charAt(currPos))) {
            consumedAmt++;
            currPos++;
        }
        return consumedAmt;
    }
}