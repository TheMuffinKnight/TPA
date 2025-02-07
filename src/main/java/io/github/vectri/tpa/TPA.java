package io.github.vectri.tpa;

import io.github.vectri.tpa.Commands.TPACommand;
import io.github.vectri.tpa.Commands.TPRCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *  The main file for TPA.
 */
public final class TPA extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("tpa").setExecutor(new TPACommand(this));
        this.getCommand("tpr").setExecutor(new TPRCommand(this));
    }
}
