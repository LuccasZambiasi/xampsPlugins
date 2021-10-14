package br.poa.zambiasi.xampsranks.cmds;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampsranks.Main;
import br.poa.zambiasi.xampsranks.utils.Inventarios;

public class Ranks implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if (!p.hasPermission("xampsranks.cmd.ranks")) {
			p.sendMessage(Main.pl.getConfig().getString("mensagens.SemPerm").replace("&", "§"));
			return true;
		}
		if (Main.pl.getConfig().getBoolean("config.msg_on_ranks") == true) {
			p.sendMessage(Main.pl.getConfig().getString("mensagens.onRanksCmd").replace("&", "§"));
		}
		p.playSound(p.getLocation(), Sound.NOTE_PLING, 30, 30);	
		Inventarios.ranks(p);
		return true;
	}

}
