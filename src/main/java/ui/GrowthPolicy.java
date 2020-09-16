package ui;

public enum GrowthPolicy {
    BOTH_PREFERRED,
    FIXED_WIDTH,
    FIXED_HEIGHT,
    BOTH_FIXED;

    public boolean bothPreferred() { return (this == BOTH_PREFERRED); }
    public boolean bothFixed() { return (this == BOTH_FIXED); }

    public boolean preferredWidth() { return (this == BOTH_PREFERRED || this == FIXED_HEIGHT); }
    public boolean preferredHeight() { return (this == BOTH_PREFERRED || this == FIXED_WIDTH); }

    public boolean fixedWidth() { return (this == BOTH_FIXED || this == FIXED_WIDTH); }
    public boolean fixedHeight() { return (this == BOTH_FIXED || this == FIXED_HEIGHT); }
}
