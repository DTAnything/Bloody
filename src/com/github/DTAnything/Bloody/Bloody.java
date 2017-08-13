package com.github.DTAnything.Bloody;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Bloody extends JavaPlugin implements Listener
{
	private String title = "§e[§a§lBloody§e]§r";
	
    @Override
    public void onEnable()
    {
    	getLogger().info("Bloody加载成功!");
    	Bukkit.getPluginManager().registerEvents(this, this);
    	this.saveDefaultConfig();
    }
    
    @Override
    public void onDisable()
    {
    	getLogger().info("Bloody卸载成功!");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
    	if (cmd.getName().equalsIgnoreCase("bloody") && args.length == 0)
    	{
    		reloadConfig();
    		sender.sendMessage(title + ChatColor.DARK_RED + this.getConfig().getString("reload"));
    		return true;
    	}
    	return false;
    }
    
    @EventHandler
	public void onHitEntity(EntityDamageEvent event)
	{
    	if (this.getConfig().getBoolean("enable_bloody"))
    	{
    		event.getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK,event.getEntity().getLocation().add(0d, (event.getEntity().getHeight()+0.5d) / 2, 0d) , 200, 0.25d, event.getEntity().getHeight() / 4, 0.25d, new ItemStack(Material.REDSTONE_BLOCK).getData());
    	}
    	if (this.getConfig().getBoolean("enable_sound"))
    	{
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
    	}
    }
}
