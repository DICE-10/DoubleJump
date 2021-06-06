package com.dice_programming_etc.DoubleJump;

import org.bukkit.plugin.java.JavaPlugin;

public final class DoubleJump extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("wjump").setExecutor(new CommandClass());
        getServer().getPluginManager().registerEvents(new EventClass(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
