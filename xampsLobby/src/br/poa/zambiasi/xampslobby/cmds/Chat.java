package br.poa.zambiasi.xampslobby.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class Chat implements CommandExecutor {

	public static boolean chat = true;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("xampslobby.cmd.chat")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§e* §7Use /chat <on/off>");
			return true;
		}
		if (args[0].equalsIgnoreCase("on")) {
			Bukkit.broadcastMessage("§a* §7O chat foi ativado!");
			chat = true;
			return true;
		} else if (args[0].equalsIgnoreCase("off")) {
			Bukkit.broadcastMessage("§a* §7O chat foi desativado!");
			chat = false;
			return true;
		} else {
			p.sendMessage("§e* §7Use /chat <on/off>");
		}
		return false;
	}
}
