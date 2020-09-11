import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import config.Config;
import testing.TestClass;
import utils.TextureHelper;
import utils.TextureManager;

@SpireInitializer
public class ModLib implements PostInitializeSubscriber {
    public static final String modID = "ModLib";
    public static boolean MOD_LIB_DEBUG = false;

    public ModLib() {
        BaseMod.subscribe(this);

        // For debugging layouts
//        Config.MOD_LIB_DEBUG_MODE = true;
    }

    public static void initialize() {
        new ModLib();

    }

    @Override
    public void receivePostInitialize() {
        System.out.println("OJB: modlib init");

        // Setup all mod specific textures to use later
        TextureHelper.registerModTextures();

        // TODO: temporary
        TestClass tc = new TestClass();
        BaseMod.addTopPanelItem(tc);
    }
}
