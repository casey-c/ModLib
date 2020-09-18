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
