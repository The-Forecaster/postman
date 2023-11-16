package me.srgantmoomoo.postman.command;

import java.util.List;

public abstract class Command {
    private final String name;
    private final String description;
    private final String syntax;

    private final List<String> aliases;

    public Command(String name, String description, String syntax, String... aliases) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
        this.aliases = List.of(aliases);
    }

    public abstract void onCommand(String[] args, String command);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSyntax() {
        return syntax;
    }

    public List<String> getAliases() {
        return aliases;
    }
}
