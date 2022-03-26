package nodes.expression;

import java.util.HashMap;

public abstract class PatternConstants {
    // todo: fill with more constants to make it harder to typo
    public static final String SUM_OF = "SUM OF";
    public static final String DIFF_OF = "DIFF OF";
    public static final String PRODUKT_OF = "PRODUKT OF";
    public static final String QUOSHUNT_OF = "QUOSHUNT OF";
    public static final String MOD_OF = "MOD OF";
    public static final String BIGGER_OF = "BIGGER OF";
    public static final String SMALLER_OF = "SMALLER OF";
    public static final String BOTH_OF = "BOTH OF";
    public static final String EITHER_OF = "EITHER OF";
    public static final String WON_OF = "WON OF";
    public static final String ALL_OF = "ALL OF";
    public static final String ANY_OF = "ANY OF";
    public static final String BOTH_SAEM = "BOTH SAEM";
    public static final String DIFFRINT = "DIFFRINT";
    public static final HashMap<String, String> parserPred = new HashMap<>();

    static {
        parserPred.put(SUM_OF, "SUM OF");
        parserPred.put(DIFF_OF, "DIFF OF");
        parserPred.put(PRODUKT_OF, "PRODUKT OF");
        parserPred.put(QUOSHUNT_OF, "SUM OF");
        parserPred.put(MOD_OF, "MOD OF");
        parserPred.put(BIGGER_OF, "BIGGER OF");
        parserPred.put(SMALLER_OF, "SMALLER OF");
        parserPred.put(BOTH_OF, "BOTH OF");
        parserPred.put(EITHER_OF, "EITHER OF");
        parserPred.put(WON_OF, "WON OF");
        parserPred.put(ALL_OF, "ALL OF");
        parserPred.put(ANY_OF, "ANY OF");
        parserPred.put(BOTH_SAEM, "BOTH SAEM");
        parserPred.put(DIFFRINT, "DIFFRINT");
    }
}
