package me.srgantmoomoo.postman;

import me.srgantmoomoo.postman.command.CommandManager;
import me.srgantmoomoo.postman.config.Load;
import me.srgantmoomoo.postman.config.Save;
import me.srgantmoomoo.postman.friend.FriendManager;
import me.srgantmoomoo.postman.module.ModuleManager;
import me.srgantmoomoo.postman.module.setting.SettingManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//soir
public class Main implements ModInitializer {
    int strong;
    int postman = strong;

    public static final Logger logger = LoggerFactory.getLogger("postman");

    public final String MODID = "postman";
    public final String NAME = "postman";
    public final String VERSION = "4.0";

    public static Main INSTANCE;

    public Main() {
        INSTANCE = this;
    }

    public ModuleManager moduleManager;
    public SettingManager settingManager;
    public CommandManager commandManager;
    public ClickGui clickGui;
    public Save save;
    public Load load;

    @Override
    public void onInitialize() {
        moduleManager = new ModuleManager();
        settingManager = new SettingManager();
        commandManager = new CommandManager();
        clickGui = new ClickGui();

        save = new Save();
        load = new Load();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            save.save();
        }));
    }
}
