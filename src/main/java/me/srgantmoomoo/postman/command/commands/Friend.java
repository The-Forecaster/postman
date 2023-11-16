package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import me.srgantmoomoo.postman.friend.FriendManager;

public final class Friend extends Command {
    public static final Friend INSTANCE = new Friend();

    private Friend() {
        super("friend", "Manage friends", "friend list | friend clear | friend <add/remove> <name>", "f");
    }

    @Override
    public void onCommand(final String[] args, final String command) {
        switch (args.length) {
            case 1 -> {
                switch (args[0].toLowerCase()) {
                    case "list" -> {
                        Main.INSTANCE.commandManager.sendClientChatMessage("Friends", true);

                        for (final var friend : FriendManager.INSTANCE.getFriends()) {
                            Main.INSTANCE.commandManager.sendClientChatMessage(friend.name(), true);
                        }
                    }
                    case "clear" -> {
                        FriendManager.INSTANCE.clearFriends();
                        Main.INSTANCE.commandManager.sendClientChatMessage("Cleared friend list", true);
                    }
                    default -> Main.INSTANCE.commandManager.sendCorrectionMessage(this);
                }
            }
            case 2 -> {
                switch (args[0].toLowerCase()) {
                    case "add" -> {
                        if (FriendManager.INSTANCE.isFriend(args[1])) {
                            Main.INSTANCE.commandManager.sendClientChatMessage(args[1] + " is already in your friends list", true);
                        } else {
                            FriendManager.INSTANCE.add(args[1]);
                            Main.INSTANCE.commandManager.sendClientChatMessage(args[1] + " was added to friends", true);
                        }
                    }
                    case "remove" -> {
                        if (!FriendManager.INSTANCE.isFriend(args[1])) {
                            Main.INSTANCE.commandManager.sendClientChatMessage(args[1] + " is not in your friends list", true);
                        } else {
                            FriendManager.INSTANCE.remove(args[1]);
                            Main.INSTANCE.commandManager.sendClientChatMessage(args[1] + " was removed from friends", true);
                        }
                    }
                    default -> Main.INSTANCE.commandManager.sendCorrectionMessage(this);
                }
            }
            default -> Main.INSTANCE.commandManager.sendCorrectionMessage(this);
        }
    }
}
