package ui.interactivity;

public interface IHasInteractivity {
    default boolean isCurrentlyInteractive() { return true; }
    void enableInteractivity();
    void disableInteractivity();
}
