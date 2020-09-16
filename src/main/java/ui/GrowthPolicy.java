package ui;

public enum GrowthPolicy {
    PREFERRED_BOTH,
    EXPANDING_X,
    EXPANDING_Y,
    EXPANDING_BOTH;

    public boolean isPreferredWidth() { return (this == PREFERRED_BOTH) || (this == EXPANDING_Y); }
    public boolean isPreferredHeight() { return (this == PREFERRED_BOTH) || (this == EXPANDING_X); }
    public boolean isExpandingWidth() { return (this == EXPANDING_BOTH) || (this == EXPANDING_X); }
    public boolean isExpandingHeight() { return (this == EXPANDING_BOTH) || (this == EXPANDING_Y); }
}
