package io.github.vectri.tpa.Commands;

import io.github.vectri.tpa.Requests.RequestHandler;
import io.github.vectri.tpa.TPA;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;


/**
 * Handles the requesting of teleports.
 */
public class TPRCommand implements CommandExecutor {
    private TPA plugin;
    private static RequestHandler requestHandler;

    public TPRCommand(TPA plugin) {
        this.plugin = plugin;
        requestHandler = new RequestHandler(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            String target = (args.length != 0) ? args[0].toLowerCase() : null;
            if (target != null) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equalsIgnoreCase(target)) {
                        UUID playerUniqueId = player.getUniqueId();
                        UUID senderUniqueId = ((Player) sender).getUniqueId();
                        if (requestHandler.alreadyRequested(playerUniqueId, senderUniqueId)) {
                            sender.sendMessage("You have already requested to teleport to " + player.getName() + "!");
                        } else {
                            requestHandler.add(playerUniqueId, senderUniqueId);
                            sender.sendMessage("Sent a teleport request to " + player.getName() + ".");
                            player.sendMessage(sender.getName() + " has requested to teleport to you. /tpa " + sender.getName() + " to accept.");
                        }
                        return true;
                    }
                }
            }
        }
        return false; // Returning false shows the correct usage of the command.
    }
}
