package me.srgantmoomoo.postman.event.events;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.Type;

public final class EventMouse extends Event<EventMouse> {
    private final int button, action, mods;

    public EventMouse(final int button, final int action, final int mods) {
        this.button = button;
        this.action = action;
        this.mods = mods;

        this.setType(Type.PRE);
    }

    public int getButton() {
        return this.button;
    }

    public int getAction() {
        return this.action;
    }

    public int getMods() {
        return this.mods;
    }
}
