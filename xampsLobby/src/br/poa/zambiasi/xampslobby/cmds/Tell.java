package br.poa.zambiasi.xampslobby.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class Tell implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
	    if (args.length == 0)
	    {
	      sender.sendMessage("§e* §7Utilize /tell §a<jogador§a> <mensagem§a>§7.");
	      return true;
	    }
	    if (args.length == 1)
	    {
	      sender.sendMessage("§e* §7Utilize /tell §a<jogador§a> <mensagem>§7.");
	      return true;
	    }
	    if (args.length > 1)
	    {
	      Player target = Bukkit.getPlayerExact(args[0]);
	      if ((target == null) || (!(target instanceof Player)))
	      {
	        sender.sendMessage("§c* §7O jogador mencionado não foi encontrado!");
	        return true;
	      }
	      StringBuilder sb = new StringBuilder();
	      for (int i = 1; i < args.length; i++) {
	        sb.append(args[i] + " ");
	      }
	      String msg = sb.toString();
	      target.sendMessage(String.format("§a%s §7para §7você§8:§7 %s", new Object[] { sender.getName(), msg }));
	      sender.sendMessage(String.format("§7Você §7para §a%s§8:§7 %s", new Object[] { target.getName(), msg }));
	    return false;
	  }
		return false;
	}
}