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

//    public static boolean isCentral(AnchorPosition p) {
//        return p == CENTER_LEFT || p == CENTER || p == ;
//    }

    public static boolean isCentralX(AnchorPosition p) {
        return p == TOP_CENTER || p == CENTER || p == BOTTOM_CENTER;
    }
    public static boolean isCentralY(AnchorPosition p) {
        return p == CENTER_LEFT || p == CENTER || p == CENTER_RIGHT;
    }

    public static boolean isLeft(AnchorPosition p) {
        return p == TOP_LEFT || p == CENTER_LEFT || p == BOTTOM_LEFT;
    }

    public static boolean isRight(AnchorPosition p) {
        return p == TOP_RIGHT || p == CENTER_RIGHT || p == BOTTOM_RIGHT;
    }

    public static boolean isTop(AnchorPosition p) {
        return p == TOP_LEFT || p == TOP_CENTER || p == TOP_RIGHT;
    }

    public static boolean isBottom(AnchorPosition p) {
        return p == BOTTOM_LEFT || p == BOTTOM_CENTER || p == BOTTOM_RIGHT;
    }
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
