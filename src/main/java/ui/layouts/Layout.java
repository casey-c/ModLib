package ui.layouts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ui.widgets.ScreenWidget;

import java.util.Collection;

public abstract class Layout extends ScreenWidget {
    protected Collection<ScreenWidget> children;
}
