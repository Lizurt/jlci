package parser;

import java.util.HashMap;

public abstract class PatternConstants {
    // expressions
    public static final String SUM_OF = "SUM OF";
    public static final String DIFF_OF = "DIFF OF";
    public static final String PRODUKT_OF = "PRODUKT OF";
    public static final String QUOSHUNT_OF = "QUOSHUNT OF";
    public static final String BIGGER_OF = "BIGGR OF";
    public static final String SMALLER_OF = "SMALLR OF";
    public static final String BOTH_OF = "BOTH OF";
    public static final String EITHER_OF = "EITHER OF";
    public static final String BOTH_SAEM = "BOTH SAEM";
    public static final String DIFFRINT = "DIFFRINT";

    // other statements
    public static final String VISIBLE = "VISIBLE";
    public static final String GIMMEH = "GIMMEH";
    public static final String O_RLY = "O RLY?";
    public static final String YA_RLY = "YA RLY";
    public static final String NO_WAI = "NO WAI";
    public static final String R = "R";
    public static final String HAI = "HAI";
    public static final String IM_IN_YR = "IM IN YR";

    // unsouttable. These ones need only for syntax/semantic analysis, they won't be presented in asttree nor bytecode
    public static final String KTHXBYE = "KTHXBYE";
    public static final String OIC = "OIC";
    public static final String YR = "YR";
    public static final String WILE = "WILE";
    public static final String TIL = "TIL";
    public static final String IM_OUTTA_YR = "IM OUTTA YR";
    public static final String AN = "AN";

    public static final HashMap<String, String> astTreeSoutDictionary = new HashMap<>();
    static {
        boolean alternateSoutting = false;
        if (alternateSoutting) {
// expressions
            astTreeSoutDictionary.put(SUM_OF, "+");
            astTreeSoutDictionary.put(DIFF_OF, "-");
            astTreeSoutDictionary.put(PRODUKT_OF, "*");
            astTreeSoutDictionary.put(QUOSHUNT_OF, "/");
            astTreeSoutDictionary.put(BIGGER_OF, "max");
            astTreeSoutDictionary.put(SMALLER_OF, "min");
            astTreeSoutDictionary.put(BOTH_OF, "&&");
            astTreeSoutDictionary.put(EITHER_OF, "||");
            astTreeSoutDictionary.put(BOTH_SAEM, "==");
            astTreeSoutDictionary.put(DIFFRINT, "!=");

            // other statements
            astTreeSoutDictionary.put(VISIBLE, "cout");
            astTreeSoutDictionary.put(GIMMEH, "cin");
            astTreeSoutDictionary.put(O_RLY, "if");
            astTreeSoutDictionary.put(YA_RLY, "then");
            astTreeSoutDictionary.put(NO_WAI, "else");
            astTreeSoutDictionary.put(R, "=");
            astTreeSoutDictionary.put(HAI, "...");
            astTreeSoutDictionary.put(IM_IN_YR, "for");
        } else {
            // expressions
            astTreeSoutDictionary.put(SUM_OF, SUM_OF);
            astTreeSoutDictionary.put(DIFF_OF, DIFF_OF);
            astTreeSoutDictionary.put(PRODUKT_OF, PRODUKT_OF);
            astTreeSoutDictionary.put(QUOSHUNT_OF, QUOSHUNT_OF);
            astTreeSoutDictionary.put(BIGGER_OF, BIGGER_OF);
            astTreeSoutDictionary.put(SMALLER_OF, SMALLER_OF);
            astTreeSoutDictionary.put(BOTH_OF, BOTH_OF);
            astTreeSoutDictionary.put(EITHER_OF, EITHER_OF);
            astTreeSoutDictionary.put(BOTH_SAEM, BOTH_SAEM);
            astTreeSoutDictionary.put(DIFFRINT, DIFFRINT);

            // other statements
            astTreeSoutDictionary.put(VISIBLE, VISIBLE);
            astTreeSoutDictionary.put(GIMMEH, GIMMEH);
            astTreeSoutDictionary.put(O_RLY, O_RLY);
            astTreeSoutDictionary.put(YA_RLY, YA_RLY);
            astTreeSoutDictionary.put(NO_WAI, NO_WAI);
            astTreeSoutDictionary.put(R, R);
            astTreeSoutDictionary.put(HAI, HAI);
            astTreeSoutDictionary.put(IM_IN_YR, IM_IN_YR);
        }
    }
}
