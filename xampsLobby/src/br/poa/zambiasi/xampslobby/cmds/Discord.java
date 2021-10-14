package br.poa.zambiasi.xampslobby.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampslobby.Main;
import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class Discord implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("xampslobby.cmd.discord")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		} else {
			p.sendMessage(Main.pl.getConfig().getString("Mensagens.Discord").replace("&", "§").replace("%discord%", Main.pl.getConfig().getString("Gerais.Discord")));
		}
		return false;
	}
}