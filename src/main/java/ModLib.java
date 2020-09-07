import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import testing.TestClass;
import utils.TextureManager;

@SpireInitializer
public class ModLib implements PostInitializeSubscriber {
    public static final String modID = "ModLib";

    public ModLib() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new ModLib();

    }

    public static void setupModTextures() {
        TextureManager.registerTexture(modID, "SCREEN_LARGE_BASE", "ModLib/images/screens/screen_1000_800_base.png");
        TextureManager.registerTexture(modID, "SCREEN_LARGE_TRIM", "ModLib/images/screens/screen_1000_800_trim.png");
        TextureManager.registerTexture(modID, "ICON", "ModLib/images/icon.png");
    }

    @Override
    public void receivePostInitialize() {
        System.out.println("OJB: modlib init");

        // Setup all mod specific textures to use later
        setupModTextures();

        // TODO: temporary
        TestClass tc = new TestClass();
        BaseMod.addTopPanelItem(tc);
    }
}
