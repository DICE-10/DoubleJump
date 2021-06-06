package com.dice_programming_etc.DoubleJump;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

import java.util.List;

import static com.dice_programming_etc.DoubleJump.CommandClass.WJ_KEY;

public class EventClass implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        e.getPlayer().setAllowFlight(true);
    }

    @EventHandler
    public void onPlayerDoubleJump(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.SURVIVAL) {
            e.setCancelled(true);
            List<MetadataValue> list = p.getMetadata(WJ_KEY);
            if(list.get(0).value().toString().equalsIgnoreCase("on")){
                Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0, 2, 0));
                if (!b.getType().equals(Material.AIR)) {
                    Vector v = p.getLocation().getDirection().multiply(0.5).setY(0.5);
                    p.setVelocity(v);
                }
            }
        }
    }
}
