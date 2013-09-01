package me.dulexzach.spawnleave;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class spawnleave extends JavaPlugin implements Listener{
		
		public final Logger logger = Logger.getLogger("Minecraft");
		
		@Override
		public void onDisable(){
			
			PluginDescriptionFile pdfFile = this.getDescription();
			this.logger.info("[" + pdfFile.getName()  + "] "+ " Has been DISABLED!");
			
		}

		@Override
		public void onEnable() {

			getConfig().options().copyDefaults(true);
			saveConfig();
			
			getServer().getPluginManager().registerEvents(this,this);
			PluginDescriptionFile pdfFile = this.getDescription();
			
			this.logger.info("[" + pdfFile.getName()  + "] "+ " Has been ENABLED!");
			
		}
		

		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {	
			Player player = (Player) sender;
			
			if(label.equalsIgnoreCase("hreload")){
				if(player.hasPermission("hub.reload")) {
				reloadConfig();
				
				sender.sendMessage(ChatColor.GREEN + "Successfully Reloaded!");
			}
			}
			return false;
		}
		


		@EventHandler
		public void onPlayerQuit(PlayerQuitEvent e){
			Player player = e.getPlayer();
			if(!player.hasPermission("hub.bypass")) {
            player.teleport(new Location(Bukkit.getWorld("world"), this.getConfig().getInt("X"), this.getConfig().getInt("Y"), this.getConfig().getInt("Z")));
			}
		}

}
