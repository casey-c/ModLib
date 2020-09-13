package ui.layouts;

public enum GrowthPolicy {
    PREFERRED,
    FIXED_WIDTH,
    FIXED_HEIGHT,
    FIXED;

    public static boolean isPreferredHeight(GrowthPolicy policy) {
        return (policy == PREFERRED || policy == FIXED_WIDTH);
    }

    public static boolean isPreferredWidth(GrowthPolicy policy) {
        return (policy == PREFERRED || policy == FIXED_HEIGHT);
    }
}
