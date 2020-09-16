package ui.layouts;

public enum AnchorPosition {
    BOTTOM_LEFT,
    BOTTOM_CENTER,
    BOTTOM_RIGHT,

    CENTER_LEFT,
    CENTER,
    CENTER_RIGHT,

    TOP_LEFT,
    TOP_CENTER,
    TOP_RIGHT;

    public boolean isLeft() { return (this == TOP_LEFT || this == CENTER_LEFT || this == BOTTOM_LEFT); }
    public boolean isRight() { return (this == TOP_RIGHT || this == CENTER_RIGHT || this == BOTTOM_RIGHT); }

    public boolean isBottom() { return (this == BOTTOM_LEFT || this == BOTTOM_CENTER || this == BOTTOM_RIGHT); }
    public boolean isTop() { return (this == TOP_LEFT || this == TOP_CENTER || this == TOP_RIGHT); }

    public boolean isCentralX() { return (this == TOP_CENTER || this ==  CENTER || this ==  BOTTOM_CENTER); }
    public boolean isCentralY() { return (this == CENTER_LEFT || this ==  CENTER || this ==  CENTER_RIGHT); }

//    public static boolean isCentral(AnchorPosition p) {
//        return p == CENTER_LEFT || p == CENTER || p == ;
//    }

    @Deprecated public static boolean isCentralX(AnchorPosition p) { return p == TOP_CENTER || p == CENTER || p == BOTTOM_CENTER; }
    @Deprecated public static boolean isCentralY(AnchorPosition p) { return p == CENTER_LEFT || p == CENTER || p == CENTER_RIGHT; }
    @Deprecated public static boolean isLeft(AnchorPosition p) { return p == TOP_LEFT || p == CENTER_LEFT || p == BOTTOM_LEFT; }
    @Deprecated public static boolean isRight(AnchorPosition p) { return p == TOP_RIGHT || p == CENTER_RIGHT || p == BOTTOM_RIGHT; }
    @Deprecated public static boolean isTop(AnchorPosition p) { return p == TOP_LEFT || p == TOP_CENTER || p == TOP_RIGHT; }
    @Deprecated public static boolean isBottom(AnchorPosition p) { return p == BOTTOM_LEFT || p == BOTTOM_CENTER || p == BOTTOM_RIGHT; }
}

/*

            TOP LEFT                   TOP CENTER                  TOP RIGHT
                       +-----------------------------------------+
                       |                                         |
                       |                                         |
                       |                                         |
                       |                                         |
                       |                                         |
       CENTER LEFT     |                 CENTER                  |    CENTER RIGHT
                       |                                         |
                       |                                         |
                       |                                         |
                       |                                         |
                       |                                         |
                       +-----------------------------------------+
         BOTTOM LEFT               BOTTOM CENTER                     BOTTOM RIGHT


 */
