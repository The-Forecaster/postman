package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import me.srgantmoomoo.postman.command.CommandManager;
import me.srgantmoomoo.postman.friend.FriendManager;

public final class Friend extends Command {
    public static final Friend INSTANCE = new Friend();

    private Friend() {
        super("friend", "Manage friends", "friend list : friend <add/remove> <name>", "f");
    }

    @Override
    public void onCommand(final String[] args, final String command) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {
                for (final var friend : FriendManager.INSTANCE.getFriends()) {
                    Main.INSTANCE.commandManager.sendClientChatMessage(friend.name(), true);
                }
            } else {
                Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            }
        } else if (args.length == 2) {
            switch (args[0].toLowerCase()) {
                case ("add") -> {
                    FriendManager.INSTANCE.add(args[1]);
                    Main.INSTANCE.commandManager.sendClientChatMessage(args[1] + " was added to friends", true);
                }
                case ("remove") -> {
                    FriendManager.INSTANCE.remove(args[1]);
                    Main.INSTANCE.commandManager.sendClientChatMessage(args[1] + " was removed from friends", true);
                }
                default -> Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            }
        } else {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
        }
    }
}
