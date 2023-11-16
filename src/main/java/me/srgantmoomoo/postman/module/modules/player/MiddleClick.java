package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventMouse;
import me.srgantmoomoo.postman.friend.FriendManager;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;

public class MiddleClick extends Module {
    public static final MiddleClick INSTANCE = new MiddleClick();

    private static final MinecraftClient minecraft = MinecraftClient.getInstance();

    private MiddleClick() {
        super("MiddleClick", "Add or remove friends using mouse3", Category.PLAYER, GLFW.GLFW_KEY_UNKNOWN);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventMouse eventMouse) {
            if (eventMouse.getButton() == GLFW.GLFW_MOUSE_BUTTON_MIDDLE && eventMouse.getAction() == GLFW.GLFW_PRESS) {
                if (minecraft.targetedEntity == null) {
                    return;
                }

                if (minecraft.targetedEntity instanceof PlayerEntity player) {
                    if (FriendManager.INSTANCE.isFriend(player)) {
                        FriendManager.INSTANCE.remove(player.getEntityName());
                        Main.INSTANCE.commandManager.sendClientChatMessage("Removed " + player.getEntityName() + " from friends list", true);
                    } else {
                        FriendManager.INSTANCE.add(player.getEntityName());
                        Main.INSTANCE.commandManager.sendClientChatMessage("Added " + player.getEntityName() + " to friends list", true);
                    }
                }
            }
        }
    }
}
