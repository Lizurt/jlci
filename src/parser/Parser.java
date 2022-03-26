package parser;

import nodes.*;
import nodes.expression.PatternConstants;
import nodes.expression.NodeExpression;
import nodes.expression.indivisible.NodeIdentifier;
import nodes.expression.indivisible.NodeNumber;
import nodes.expression.binar.*;
import nodes.expression.unar.NodeNot;
import nodes.expression.unar.NodeUnaryExpression;
import nodes.io.*;

import java.util.InputMismatchException;

public class Parser {
    private final String rawProgram;
    private int currPos = 0;
    private NodeExpression lastExpressionToken;

    public Parser(final String rawProgram) {
        this.rawProgram = rawProgram;
    }

    public Node parse() {
        if (!isParse("HAI", false, true)) {
            throw new InputMismatchException("\"HAI\" is missing.");
        }
        parse("HAI", false, true);
        NodeNumber version = parseNumber();
        NodeRoot root = new NodeRoot(String.valueOf(version.getValue()));

        while (currPos < rawProgram.length()) {
            if (isParse("KTHXBYE", true, false)) {
                return root;
            }
            Node statement = tokenizeStatementAndProceed();
            root.addChild(statement);
        }
        throw new InputMismatchException("\"KTHXBYE\" is missing.");
    }

    private Node tokenizeStatementAndProceed() {
        if (isParse("O RLY?", true, true)) {
            parse("O RLY?", true, true);
            NodeORly nodeORly = new NodeORly();
            nodeORly.setCondition(lastExpressionToken);
            if (isParse("YA RLY", true, true)) {
                parse("YA RLY", true, true);
                NodeYaRly nodeYaRly = new NodeYaRly();
                nodeORly.setNodeYaRly(nodeYaRly);
                while (true) {
                    if (isParse("NO WAI", true, true)) {
                        break;
                    }
                    if (isParse("OIC", true, true)) {
                        break;
                    }
                    Node childStatement = tokenizeStatementAndProceed();
                    nodeYaRly.addChild(childStatement);
                }
            }
            if (isParse("NO WAI", true, true)) {
                parse("NO WAI", true, true);
                NodeNoWai nodeNoWai = new NodeNoWai();
                nodeORly.setNodeNoWai(nodeNoWai);
                while (true) {
                    if (isParse("OIC", true, true)) {
                        break;
                    }
                    Node childStatement = tokenizeStatementAndProceed();
                    nodeNoWai.addChild(childStatement);
                }
            }
            if (isParse("OIC", true, true)) {
                parse("OIC", true, true);
                return nodeORly;
            }
            throw new InputMismatchException("Couldn't find OIC node at position: " + currPos + ".");
        }
        if (isParse("IM IN YR", true, true)) {
            parse("IM IN YR", true, true);
            NodeImInYr nodeImInYr = new NodeImInYr();
            NodeIdentifier loopName = parseIdentifier();
            nodeImInYr.setLoopName(loopName);
            Node afterLoopAction = tokenizeStatementAndProceed();
            nodeImInYr.setAfterLoopAction(afterLoopAction);
            if (!isParse("YR", true, true)) {
                throw new InputMismatchException("Missing \"YR\" section in a loop at position: " + currPos + ".");
            }
            parse("YR", true, true);
            Node varsInitNode = tokenizeStatementAndProceed();
            nodeImInYr.setVarsInit(varsInitNode);
            if (!isParse("WILE", true, true)) {
                throw new InputMismatchException("Missing \"WILE\" section in a loop at position: " + currPos + ".");
            }
            parse("WILE", true, true);
            NodeExpression loopCondition = parseExpression();
            nodeImInYr.setWhileCOndition(loopCondition);
            while (true) {
                if (isParse("IM OUTTA YR", true, true)) {
                    break;
                }
                Node loopChildStatement = tokenizeStatementAndProceed();
                nodeImInYr.addChild(loopChildStatement);
            }
            if (!isParse("IM OUTTA YR", true, true)) {
                throw new InputMismatchException("Missing \"IM OUTTA YR\" section in a loop at position: " + currPos + ".");
            }
            parse("IM OUTTA YR", true, true);
            NodeIdentifier loopEndName = parseIdentifier();
            if (!loopEndName.getIdentifier().equals(nodeImInYr.getLoopName().getIdentifier())) {
                throw new InputMismatchException(
                        "Wrong label for a loop with a label: " + nodeImInYr
                                + ". Found: " + loopEndName.getIdentifier() + "."
                );
            }
            return nodeImInYr;
        }
        if (isParse("GIMMEH", true, true)) {
            parse("GIMMEH", true, true);
            NodeIdentifier identifier = parseIdentifier();
            lastExpressionToken = identifier;
            return new NodeGimmeh(identifier);
        }
        if (isParse("VISIBLE", true, true)) {
            parse("VISIBLE", true, true);
            NodeExpression expression = parseExpression();
            lastExpressionToken = expression;
            return new NodeVisible(expression);
        }
        NodeIdentifier identifier = parseIdentifier();
        lastExpressionToken = identifier;
        if (isParse("R", true, true)) {
            parse("R", true, true);
            return parseAssignation(identifier);
        }
        throw new InputMismatchException(
                "Unacceptable symbol \"" + rawProgram.charAt(currPos) + "\" at position " + currPos + "."
        );
    }

    private NodeExpression parseExpression() {
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

    private NodeExpression parseExpression(NodeBinaryExpression outerExpression) {
        outerExpression.setLeftOperand(parseExpression());
        if (isParse("AN", true, true)) {
            parse("AN", true, true);
        } else if (!outerExpression.isANIgnorable()) {
            throw new InputMismatchException("\"AN\" is missing in the expression.");
        }
        outerExpression.setRightOperand(parseExpression());
        return outerExpression;
    }

    private NodeExpression parseExpression(NodeUnaryExpression outerExpression) {
        // todo: unary expressions
        return null;
    }

    private NodeNumber parseNumber() {
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
        return new NodeNumber(Double.parseDouble(rawProgram.substring(fromPos, currPos)));
    }

    private NodeIdentifier parseIdentifier() {
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
            return new NodeIdentifier(rawProgram.substring(fromPos, currPos));
        }
        throw new InputMismatchException("Vars cannot contain such symbols: " + rawProgram.charAt(currPos) + ".");
    }

    private NodeAssignation parseAssignation(NodeIdentifier identifier) {
        return new NodeAssignation(identifier, parseExpression());
    }

    private boolean isParse(String text, boolean requireLeftWhitespaces, boolean requireRightWhitespaces) {
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

    private void parse(String text, boolean requireLeftWhitespaces, boolean requireRightWhitespaces) {
        if (isParse(text, requireLeftWhitespaces, requireRightWhitespaces)) {
            currPos += consumeWhitespaces() + text.length();
            return;
        }
        throw new InputMismatchException("Parsing error.");
    }

    private int consumeWhitespaces() {
        int consumedAmt = 0;
        while (Character.isWhitespace(rawProgram.charAt(currPos))) {
            consumedAmt++;
            currPos++;
        }
        return consumedAmt;
    }
}