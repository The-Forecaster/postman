package me.srgantmoomoo.postman.friend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import me.srgantmoomoo.postman.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class FriendManager {
    public record Friend(String name, UUID uuid) { }

    public static final FriendManager INSTANCE = new FriendManager();

    private final List<Friend> friends = new ArrayList<>();

    private final Path friendFile = Path.of(new File(MinecraftClient.getInstance().runDirectory, "postman") + "\\friends.json");

    private final Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();

    private FriendManager() {}

    public void load() {
        if (!Files.exists(this.friendFile)) {
            try {
                Files.createDirectories(this.friendFile);
                Files.createFile(this.friendFile);

                for (final var friend : this.gson.fromJson(Files.readString(this.friendFile), JsonObject.class).keySet()) {
                    this.friends.add(new Friend(friend, MinecraftClient.getInstance().getSocialInteractionsManager().getUuid(friend)));
                }
            } catch (final IOException e) {
                Main.logger.error("Couldn't load friend file\n" + e.getLocalizedMessage());
            }
        }
    }

    public void save() {
        final var json = new JsonObject();

        for (final var friend : this.friends) {
            json.add(friend.name, null);
        }

        try (final var writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(this.friendFile)))) {
            writer.write(this.gson.toJson(json));
        } catch (final IOException e) {
            Main.logger.error("Couldn't save friends\n" + e.getLocalizedMessage());
        }
    }

    public void add(final String name) {
        this.friends.add(new Friend(name, MinecraftClient.getInstance().getSocialInteractionsManager().getUuid(name)));
    }

    public void remove(final String name) {
        this.friends.removeIf(friend -> Objects.equals(friend.name, name));
    }

    public boolean isFriend(final ClientPlayerEntity player) {
        return this.friends.stream().anyMatch(friend -> friend.uuid == player.getUuid());
    }
}
