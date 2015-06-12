package loosegoose.ProspectingTools;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import loosegoose.ProspectingTools.misc.LibCommon;
import loosegoose.ProspectingTools.proxy.CommonProxy;

@Mod(modid = LibCommon.MOD_ID, name = LibCommon.MOD_NAME, version = "0.1")
public class ProspectingTools {

    @SidedProxy(serverSide = LibCommon.PROXY_COMMON, clientSide = LibCommon.PROXY_CLIENT)
    public static CommonProxy proxy;

    @Instance(LibCommon.MOD_ID)
    public static ProspectingTools instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

}
