package fr.bl4.fnetherwater.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WaterListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block block = event.getClickedBlock();
        if (block == null) return;

        Player player = event.getPlayer();
        if (!player.hasPermission("netherwater.use")) return;

        World world = block.getWorld();
        if (world.getEnvironment() != Environment.NETHER) return;

        ItemStack item = event.getItem();
        if (item == null) return;
        if (item.getType() != Material.WATER_BUCKET) return;

        event.setCancelled(true);

        if (block.getBlockData() instanceof Waterlogged waterloggedBlockData) {
            waterloggedBlockData.setWaterlogged(true);
        } else {
            block.getRelative(event.getBlockFace()).setType(Material.WATER);
        }

        if (player.getGameMode() != GameMode.CREATIVE) {
            this.removeItem(item, player);
        }
    }

    private void removeItem(ItemStack item, Player p) {
        if (item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
            p.getInventory().addItem(new ItemStack(Material.BUCKET));
        } else {
            item.setType(Material.BUCKET);
        }

    }
}
