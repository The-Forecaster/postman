package me.srgantmoomoo.postman.module;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventKeyPress;
import me.srgantmoomoo.postman.module.modules.ClickGuiModule;
import me.srgantmoomoo.postman.module.modules.Example;
import me.srgantmoomoo.postman.module.modules.player.MiddleClick;
import me.srgantmoomoo.postman.module.modules.render.*;

import java.util.ArrayList;
import java.util.List;

public final class ModuleManager {
    private final List<Module> modules = List.of(
            MiddleClick.INSTANCE,
            new FullBright(),
            new VibrantShader(),
            new ClickGuiModule(),
            new Example()
    );

    public void onEvent(Event e) {
        for(Module module : getModules()) {
            if(!module.isModuleEnabled())
                continue;
            module.onEvent(e);
        }
    }

    // for key binds, called in MixinKeyboard.
    public void onKeyPress(Event e, int key, int scanCode) {
        if(e instanceof EventKeyPress) {
            modules.stream().filter(m -> m.getKey() == ((EventKeyPress) e).getKey()).forEach(Module::toggle);
        }
    }

    public List<Module> getModules() {
        return this.modules;
    }

    public Module getModule(String name) {
        for(Module module : modules) {
            if(module.getName().equalsIgnoreCase(name))
                return module;
        }
        return null;
    }

    public List<Module> getModulesInCategory(final Category category) {
        final List<Module> result = new ArrayList<>();

        for (final Module module : this.modules) {
            if (module.getCategory().getName().equalsIgnoreCase(category.getName())) {
                result.add(module);
            }
        }
        return result;
    }
}
