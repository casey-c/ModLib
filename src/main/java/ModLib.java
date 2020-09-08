import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import testing.TestClass;
import utils.TextureHelper;
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
