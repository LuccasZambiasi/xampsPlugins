package br.poa.zambiasi.xampsranks.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import br.poa.zambiasi.xampsranks.Main;

public class Inventarios {
	
	
	public static ItemStack parseString(String itemId) {
	    String[] parts = itemId.split(":");
	    int matId = Integer.parseInt(parts[0]);
	    if (parts.length == 2) {
	        short data = Short.parseShort(parts[1]);
	        return new ItemStack(Material.getMaterial(matId), 1, data);
	    }
	    return new ItemStack(Material.getMaterial(matId));
	}

	public static void ranks(Player p) {
		if (Main.pl.getConfig().getBoolean("config.rank_per_rank") == true) {
		Inventory inv = Bukkit.getServer().createInventory(p, 54, Main.pl.getConfig().getString("config.gui_name").replace("&", "§"));
		
		if (Main.pl.getConfig().getBoolean("config.glass_gui")) {
			ItemStack vidro = ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 0, " ", false);
			for (int i = 0; i < 54; i++) {
				inv.setItem(i, vidro);
			}	
		}
		
		if (Main.pl.getConfig().getBoolean("config.enchant") == true) {
		
		inv.setItem(4, ItemAPI.configItem(Main.pl.getConfig().getString("gui.base.icone"), 1, Main.pl.getConfig().getString("gui.base.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.base.descricao"), Enchantment.DURABILITY, 1));
						
		inv.setItem(19, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank1.icone"), 1, Main.pl.getConfig().getString("rank.rank1.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank1.descricao"), Enchantment.DURABILITY, 1));
		inv.setItem(20, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank2.icone"), 1, Main.pl.getConfig().getString("rank.rank2.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank2.descricao"), Enchantment.DURABILITY, 1));
		inv.setItem(21, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank3.icone"), 1, Main.pl.getConfig().getString("rank.rank3.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank3.descricao"), Enchantment.DURABILITY, 1));
					
		inv.setItem(22, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank4.icone"), 1, Main.pl.getConfig().getString("rank.rank4.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank4.descricao"), Enchantment.DURABILITY, 1));
		inv.setItem(23, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank5.icone"), 1, Main.pl.getConfig().getString("rank.rank5.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank5.descricao"), Enchantment.DURABILITY, 1));
		inv.setItem(24, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank6.icone"), 1, Main.pl.getConfig().getString("rank.rank6.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank6.descricao"), Enchantment.DURABILITY, 1));
					
		inv.setItem(25, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank7.icone"), 1, Main.pl.getConfig().getString("rank.rank7.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank7.descricao"), Enchantment.DURABILITY, 1));
		inv.setItem(28, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank8.icone"), 1, Main.pl.getConfig().getString("rank.rank8.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank8.descricao"), Enchantment.DURABILITY, 1));
		inv.setItem(29, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank9.icone"), 1, Main.pl.getConfig().getString("rank.rank9.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank9.descricao"), Enchantment.DURABILITY, 1));
				
		inv.setItem(30, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank10.icone"), 1, Main.pl.getConfig().getString("rank.rank10.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank10.descricao"), Enchantment.DURABILITY, 1));
		inv.setItem(31, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank11.icone"), 1, Main.pl.getConfig().getString("rank.rank11.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank11.descricao"), Enchantment.DURABILITY, 1));
		inv.setItem(32, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank12.icone"), 1, Main.pl.getConfig().getString("rank.rank12.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank12.descricao"), Enchantment.DURABILITY, 1));
				
		inv.setItem(33, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank13.icone"), 1, Main.pl.getConfig().getString("rank.rank13.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank13.descricao"), Enchantment.DURABILITY, 1));	
		inv.setItem(34, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank14.icone"), 1, Main.pl.getConfig().getString("rank.rank14.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank14.descricao"), Enchantment.DURABILITY, 1));	
			
		inv.setItem(39, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank15.icone"), 1, Main.pl.getConfig().getString("rank.rank15.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank15.descricao"), Enchantment.DURABILITY, 1));	
		inv.setItem(40, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank16.icone"), 1, Main.pl.getConfig().getString("rank.rank16.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank16.descricao"), Enchantment.DURABILITY, 1));	
		inv.setItem(41, ItemAPI.configItem(Main.pl.getConfig().getString("rank.rank17.icone"), 1, Main.pl.getConfig().getString("rank.rank17.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank17.descricao"), Enchantment.DURABILITY, 1));	
		
		} else {
		inv.setItem(4, ItemAPI.configItem2(Main.pl.getConfig().getString("gui.base.icone"), 1, Main.pl.getConfig().getString("gui.base.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.base.descricao")));
			
		inv.setItem(19, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank1.icone"), 1, Main.pl.getConfig().getString("rank.rank1.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank1.descricao")));
		inv.setItem(20, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank2.icone"), 1, Main.pl.getConfig().getString("rank.rank2.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank2.descricao")));
		inv.setItem(21, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank3.icone"), 1, Main.pl.getConfig().getString("rank.rank3.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank3.descricao")));
					
		inv.setItem(22, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank4.icone"), 1, Main.pl.getConfig().getString("rank.rank4.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank4.descricao")));
		inv.setItem(23, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank5.icone"), 1, Main.pl.getConfig().getString("rank.rank5.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank5.descricao")));
		inv.setItem(24, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank6.icone"), 1, Main.pl.getConfig().getString("rank.rank6.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank6.descricao")));
					
		inv.setItem(25, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank7.icone"), 1, Main.pl.getConfig().getString("rank.rank7.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank7.descricao")));
		inv.setItem(28, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank8.icone"), 1, Main.pl.getConfig().getString("rank.rank8.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank8.descricao")));
		inv.setItem(29, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank9.icone"), 1, Main.pl.getConfig().getString("rank.rank9.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank9.descricao")));
				
		inv.setItem(30, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank10.icone"), 1, Main.pl.getConfig().getString("rank.rank10.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank10.descricao")));
		inv.setItem(31, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank11.icone"), 1, Main.pl.getConfig().getString("rank.rank11.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank11.descricao")));
		inv.setItem(32, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank12.icone"), 1, Main.pl.getConfig().getString("rank.rank12.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank12.descricao")));
				
		inv.setItem(33, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank13.icone"), 1, Main.pl.getConfig().getString("rank.rank13.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank13.descricao")));	
		inv.setItem(34, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank14.icone"), 1, Main.pl.getConfig().getString("rank.rank14.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank14.descricao")));	
			
		inv.setItem(39, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank15.icone"), 1, Main.pl.getConfig().getString("rank.rank15.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank15.descricao")));	
		inv.setItem(40, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank16.icone"), 1, Main.pl.getConfig().getString("rank.rank16.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank16.descricao")));	
		inv.setItem(41, ItemAPI.configItem2(Main.pl.getConfig().getString("rank.rank17.icone"), 1, Main.pl.getConfig().getString("rank.rank17.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("rank.rank17.descricao")));	
		
		}
		
		p.openInventory(inv);
	} else {
		
		Inventory inv = Bukkit.getServer().createInventory(p, 27, Main.pl.getConfig().getString("config.gui_name").replace("&", "§"));
		
		if (Main.pl.getConfig().getBoolean("config.glass_gui")) {
			ItemStack vidro = ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 0, " ", false);
			for (int i = 0; i < 27; i++) {
				inv.setItem(i, vidro);
			}	
		}
		if (Main.pl.getConfig().getBoolean("config.enchant") == true) {	
			inv.setItem(4, ItemAPI.configItem(Main.pl.getConfig().getString("gui.base.icone"), 1, Main.pl.getConfig().getString("gui.base.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.base.descricao"), Enchantment.DURABILITY, 1));
				
			inv.setItem(10, ItemAPI.configItem(Main.pl.getConfig().getString("gui.rank1.icone"), 1, Main.pl.getConfig().getString("gui.rank1.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank1.descricao"), Enchantment.DURABILITY, 1));
			inv.setItem(11, ItemAPI.configItem(Main.pl.getConfig().getString("gui.rank2.icone"), 1, Main.pl.getConfig().getString("gui.rank2.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank2.descricao"), Enchantment.DURABILITY, 1));
			inv.setItem(12, ItemAPI.configItem(Main.pl.getConfig().getString("gui.rank3.icone"), 1, Main.pl.getConfig().getString("gui.rank3.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank3.descricao"), Enchantment.DURABILITY, 1));
			inv.setItem(13, ItemAPI.configItem(Main.pl.getConfig().getString("gui.rank4.icone"), 1, Main.pl.getConfig().getString("gui.rank4.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank4.descricao"), Enchantment.DURABILITY, 1));
			inv.setItem(14, ItemAPI.configItem(Main.pl.getConfig().getString("gui.rank5.icone"), 1, Main.pl.getConfig().getString("gui.rank5.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank5.descricao"), Enchantment.DURABILITY, 1));			
			inv.setItem(15, ItemAPI.configItem(Main.pl.getConfig().getString("gui.rank6.icone"), 1, Main.pl.getConfig().getString("gui.rank6.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank6.descricao"), Enchantment.DURABILITY, 1));
			inv.setItem(16, ItemAPI.configItem(Main.pl.getConfig().getString("gui.rank7.icone"), 1, Main.pl.getConfig().getString("gui.rank7.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank7.descricao"), Enchantment.DURABILITY, 1));
		} else {
			inv.setItem(4, ItemAPI.configItem2(Main.pl.getConfig().getString("gui.base.icone"), 1, Main.pl.getConfig().getString("gui.base.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.base.descricao")));
			
			inv.setItem(10, ItemAPI.configItem2(Main.pl.getConfig().getString("gui.rank1.icone"), 1, Main.pl.getConfig().getString("gui.rank1.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank1.descricao")));
			inv.setItem(11, ItemAPI.configItem2(Main.pl.getConfig().getString("gui.rank2.icone"), 1, Main.pl.getConfig().getString("gui.rank2.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank2.descricao")));
			inv.setItem(12, ItemAPI.configItem2(Main.pl.getConfig().getString("gui.rank3.icone"), 1, Main.pl.getConfig().getString("gui.rank3.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank3.descricao")));
			inv.setItem(13, ItemAPI.configItem2(Main.pl.getConfig().getString("gui.rank4.icone"), 1, Main.pl.getConfig().getString("gui.rank4.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank4.descricao")));
			inv.setItem(14, ItemAPI.configItem2(Main.pl.getConfig().getString("gui.rank5.icone"), 1, Main.pl.getConfig().getString("gui.rank5.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank5.descricao")));			
			inv.setItem(15, ItemAPI.configItem2(Main.pl.getConfig().getString("gui.rank6.icone"), 1, Main.pl.getConfig().getString("gui.rank6.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank6.descricao")));
			inv.setItem(16, ItemAPI.configItem2(Main.pl.getConfig().getString("gui.rank7.icone"), 1, Main.pl.getConfig().getString("gui.rank7.nome").replace("&", "§"), false, Main.pl.getConfig().getStringList("gui.rank7.descricao")));
		}
			p.openInventory(inv);
		
	}
 }
}
