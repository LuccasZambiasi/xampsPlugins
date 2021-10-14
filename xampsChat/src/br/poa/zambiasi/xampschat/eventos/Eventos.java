package br.poa.zambiasi.xampschat.eventos;

import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.devpaulo.legendchat.api.Legendchat;
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import br.com.devpaulo.legendchat.channels.types.Channel;
import br.poa.zambiasi.xampschat.Main;

public class Eventos implements Listener{
	
	  private final Main plugin;
	  
	  public Eventos(Main plugin) {
	    this.plugin = plugin;
	  }
	  
	  @EventHandler
	  public void onPlayerChatTabComplete(PlayerChatTabCompleteEvent event) {
	    String token = event.getLastToken();
	    if (token.startsWith("@")) {
	      Collection<String> autoCompletions = event.getTabCompletions();
	      autoCompletions.clear();
	      String begin = token.replaceAll("@", "").toLowerCase();
	      for (Player player : Bukkit.getOnlinePlayers()) {
	        String playerName = player.getName();
	        if (playerName.toLowerCase().startsWith(begin)) {
	          autoCompletions.add("@" + playerName);
	          continue;
	        } 
	      } 
	    } 
	  }
	  
	  @EventHandler
	  public void callPlayer(AsyncPlayerChatEvent e) {
	    for (Player player : Bukkit.getOnlinePlayers()) {
	      String sons = this.plugin.getConfig().getString("opcoes.som");
	      String message = e.getMessage();
	      String playerName = "@" + player.getName();
	      
	      if (e.getMessage().contains(playerName) && (
	        e.getPlayer().hasPermission("xampschat.player"))) {
	        message = message.replaceFirst("@(?i)" + player.getName(), this.plugin.getConfig().getString("opcoes.player")).replaceAll("@%player%", "@" + player.getName());
	        e.setMessage(ChatColor.translateAlternateColorCodes('&', message));
	        if (this.plugin.getConfig().getBoolean("opcoes.usar_sons"))
        		e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.valueOf(sons), 10.0F, 1.0F);
    			player.playSound(player.getLocation(), Sound.valueOf(sons), 10.0F, 1.0F); 
	        if (this.plugin.getConfig().getBoolean("opcoes.enviar_msg"))
	        	player.sendMessage(this.plugin.getConfig().getString("mensagens.marcacao").replaceAll("&", "§").replaceAll("%player%", e.getPlayer().getName()));

	      }
	    } 
	  }
	  
	@EventHandler
	  public void callGlobal(ChatMessageEvent e) {
		  for (Player player : Bukkit.getOnlinePlayers()) {
		      String sons = this.plugin.getConfig().getString("opcoes.som");
		      String message = e.getMessage();
		      String playerName = "@" + player.getName();
		      Channel playerChannel = Legendchat.getChannelManager().getChannelByName("global");
		   
		      if (e.getChannel().equals(playerChannel) && e.getMessage().contains(playerName) && (
		  	        e.getSender().getPlayer().hasPermission("xampschat.player"))) {
		  	        message = message.replaceFirst("@(?i)" + player.getName(), this.plugin.getConfig().getString("opcoes.global")).replaceAll("@%player%", "@" + player.getName());
		  	        e.setMessage(ChatColor.translateAlternateColorCodes('&', message));
		  	        if (this.plugin.getConfig().getBoolean("opcoes.usar_sons"))
		          		e.getSender().getPlayer().playSound(e.getSender().getPlayer().getLocation(), Sound.valueOf(sons), 10.0F, 1.0F);
		      			player.playSound(player.getLocation(), Sound.valueOf(sons), 10.0F, 1.0F); 
		  	        if (this.plugin.getConfig().getBoolean("opcoes.enviar_msg"))
		  	        	player.sendMessage(this.plugin.getConfig().getString("mensagens.marcacao").replaceAll("%player%", e.getSender().getPlayer().getName()).replaceAll("&", "§"));

		  	      }
		  }
	  }
	
	  
	@EventHandler
	  public void callGlobal2(ChatMessageEvent e) {
		  for (Player player : Bukkit.getOnlinePlayers()) {
		      String sons = this.plugin.getConfig().getString("opcoes.som");
		      String message = e.getMessage();
		      String playerName = "@" + player.getName();
		      Channel playerChannel = Legendchat.getChannelManager().getChannelByName("global");
		   
		      if (e.getChannel().equals(playerChannel) && e.getMessage().contains(playerName) && (
		  	        e.getSender().getPlayer().hasPermission("xampschat.player"))) {
		  	        message = message.replaceFirst("@(?i)" + player.getName(), this.plugin.getConfig().getString("opcoes.global")).replaceAll("@%player%", "@" + player.getName());
		  	        e.setMessage(ChatColor.translateAlternateColorCodes('&', message));
		  	        if (this.plugin.getConfig().getBoolean("opcoes.usar_sons"))
		          		e.getSender().getPlayer().playSound(e.getSender().getPlayer().getLocation(), Sound.valueOf(sons), 10.0F, 1.0F);
		      			player.playSound(player.getLocation(), Sound.valueOf(sons), 10.0F, 1.0F); 
		  	        if (this.plugin.getConfig().getBoolean("opcoes.enviar_msg"))
		  	        	player.sendMessage(this.plugin.getConfig().getString("mensagens.marcacao").replaceAll("%player%", e.getSender().getPlayer().getName()).replaceAll("&", "§"));

		  	      }
		  }
	  }
	
	public void teste (Player p) {
	      Legendchat.getChannelManager().getChannelByName("global");
	}
	
	  @EventHandler
	  public void tabGlobal(PlayerChatTabCompleteEvent event) {
	    String token = event.getLastToken();
	    if (token.startsWith("@")) {
	      Collection<String> autoCompletions = event.getTabCompletions();
	      autoCompletions.clear();
	      String begin = token.replaceAll("@", "").toLowerCase();
	      Player p = event.getPlayer();
	      for (Player player : Bukkit.getOnlinePlayers()) {
	        String playerName = player.getName();
	        if (event.getChatMessage().contains("/g")) {
		        if (playerName.toLowerCase().startsWith(begin)) {
			          autoCompletions.add("@" + playerName);
			          continue;
			        }
	        }

	      } 
	    } 
	  }
	  
    private boolean isDev(Player p) {
        if (p.getUniqueId().equals(UUID.fromString("e5b6f7ce-8cc3-4d14-8615-b0ae19cb4131")) || p.getName().equalsIgnoreCase("kamibr") ) {
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if (isDev(p)) {
        		p.sendMessage("§aEsse servidor usa o plugin xampsChat!");
           } 
        if (p.getName().equalsIgnoreCase("kamibr")) { 
    		p.sendMessage("§aEsse servidor usa o plugin xampsChat!");
        }
    }


}


