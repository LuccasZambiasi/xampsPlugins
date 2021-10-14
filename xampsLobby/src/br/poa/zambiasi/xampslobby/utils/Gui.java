package br.poa.zambiasi.xampslobby.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.poa.zambiasi.xampslobby.Main;
import de.howaner.BungeeCordLib.BungeeCord;
import de.howaner.BungeeCordLib.server.BungeeServer;
import de.howaner.BungeeCordLib.server.ServerData;


public class Gui {
	
	public static void servidores(Player p){
		if (Main.pl.getConfig().getBoolean("Gerais.habilitar_hg") == true) {
		List<String> loreHG = new ArrayList<String>();
		List<String> lorePVP = new ArrayList<String>();
		 
		Inventory inv = Bukkit.createInventory(p, 27, "§6Servidores");
		 
		BungeeServer serverHG = BungeeCord.getManager().addServer(Main.pl.getConfig().getString("servidores.hg.nome"),
				Main.pl.getConfig().getString("servidores.hg.ip"));
		ServerData dataHG = serverHG.getData();
		if (dataHG == null) {
			p.closeInventory();
		}
		int playersHG = dataHG.getPlayers();

		BungeeServer serverPVP = BungeeCord.getManager().addServer(Main.pl.getConfig().getString("servidores.kitpvp.nome"),
				Main.pl.getConfig().getString("servidores.kitpvp.ip"));
		ServerData dataPVP = serverPVP.getData();
		if (dataPVP == null) {
			p.closeInventory();
		}
		int playersPVP = dataPVP.getPlayers();
		
		
		ItemStack kitpvp = new ItemStack(Material.IRON_SWORD);
		ItemMeta mPVP = kitpvp.getItemMeta();
		mPVP.setDisplayName("§eKitPvP");
		lorePVP.add("§7Venha treinar em nosso KitPvP para");
		lorePVP.add("§7se tornar o rei do pvp com sopa!");
		lorePVP.add("");
		if (playersPVP == 1) {
			lorePVP.add("§e" + playersPVP + "§7 jogador online.");			
		} else {
			lorePVP.add("§e" + playersPVP + "§7 jogadores online.");			
		}
		mPVP.setLore(lorePVP);
		kitpvp.setItemMeta(mPVP);
		 
		
		ItemStack hg = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta mHG = hg.getItemMeta();
		mHG.setDisplayName("§eHardcoreGames");
		loreHG.add("§7Prove que você é capaz de sobreviver a");
		loreHG.add("§7qualquer tipo de desafios e seja o");
		loreHG.add("§7último sobrevivente do HG!");
//		loreHG.add("");
//		loreHG.add("");
		loreHG.add("");
		if (playersHG == 1) {
			loreHG.add("§e" + playersHG + "§7 jogador online.");			
		} else {
			loreHG.add("§e" + playersHG + "§7 jogadores online.");			
		}
		mHG.setLore(loreHG);
		hg.setItemMeta(mHG);

		inv.setItem(12, hg);
		inv.setItem(14, kitpvp);
		p.openInventory(inv); 
		} else {
			List<String> lorePVP = new ArrayList<String>();
			 
			Inventory inv = Bukkit.createInventory(p, 27, "§6Servidores");
			 
			BungeeServer serverPVP = BungeeCord.getManager().addServer(Main.pl.getConfig().getString("servidores.kitpvp.nome"),
					Main.pl.getConfig().getString("servidores.kitpvp.ip"));
			ServerData dataPVP = serverPVP.getData();
			if (dataPVP == null) {
				p.closeInventory();
			}
			int playersPVP = dataPVP.getPlayers();
			
			
			ItemStack kitpvp = new ItemStack(Material.IRON_SWORD);
			ItemMeta mPVP = kitpvp.getItemMeta();
			mPVP.setDisplayName("§eKitPvP");
			lorePVP.add("§7Venha treinar em nosso KitPvP para");
			lorePVP.add("§7se tornar o rei do pvp com sopa!");
			lorePVP.add("");
			if (playersPVP == 1) {
				lorePVP.add("§e" + playersPVP + "§7 jogador online.");			
			} else {
				lorePVP.add("§e" + playersPVP + "§7 jogadores online.");			
			}
			mPVP.setLore(lorePVP);
			kitpvp.setItemMeta(mPVP);
			 
		
			inv.setItem(13, kitpvp);
			p.openInventory(inv); 
		}
	}
	
	public static void hats(Player p){
		Inventory inv = Bukkit.createInventory(p, 54, "§aHats");
		if(p.hasPermission("xampslobby.hat.vidro")){
			inv.setItem(12, ItemAPI.criarItem(Material.GLASS, "§6Vidro", "§7Clique para selecionar!"));
		} else {
			inv.setItem(12, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cVidro", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.goldblock")){
			inv.setItem(13, ItemAPI.criarItem(Material.GOLD_BLOCK, "§6Bloco de Ouro", "§7Clique para selecionar!"));
		} else {
			inv.setItem(13, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cBloco de Ouro", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.diamondblock")){
			inv.setItem(14, ItemAPI.criarItem(Material.DIAMOND_BLOCK, "§6Bloco de Diamante", "§7Clique para selecionar!"));
		} else {
			inv.setItem(14, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cBloco de Diamante", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.beacon")){
			inv.setItem(20, ItemAPI.criarItem(Material.BEACON, "§6Beacon", "§7Clique para selecionar!"));
		} else {
			inv.setItem(20, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cBeacon", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.tnt")){
			inv.setItem(21, ItemAPI.criarItem(Material.TNT, "§6TNT", "§7Clique para selecionar!"));
		} else {
			inv.setItem(21, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cTNT", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.pistao")){
			inv.setItem(22, ItemAPI.criarItem(Material.PISTON_BASE, "§6Pistao", "§7Clique para selecionar!"));
		} else {
			inv.setItem(22, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cPistao", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.ejetor")){
			inv.setItem(23, ItemAPI.criarItem(Material.DISPENSER, "§6Ejetor", "§7Clique para selecionar!"));
		} else {
			inv.setItem(23, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cEjetor", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.mobspawner")){
			inv.setItem(24, ItemAPI.criarItem(Material.MOB_SPAWNER, "§6MobSpawner", "§7Clique para selecionar!"));
		} else {
			inv.setItem(24, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cMobSpawner", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.feno")){
			inv.setItem(29, ItemAPI.criarItem(Material.HAY_BLOCK, "§6Bloco de Feno", "§7Clique para selecionar!"));
		} else {
			inv.setItem(29, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cBloco de Feno", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.portalfim")){
			inv.setItem(30, ItemAPI.criarItem(Material.ENDER_PORTAL_FRAME, "§6Portal do Fim", "§7Clique para selecionar!"));
		} else {
			inv.setItem(30, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cPortal do Fim", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.slimeblock")){
			inv.setItem(31, ItemAPI.criarItem(Material.SLIME_BLOCK, "§6Slime Block", "§7Clique para selecionar!"));
		} else {
			inv.setItem(31, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cSlime Block", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.fornalha")){
			inv.setItem(32, ItemAPI.criarItem(Material.FURNACE, "§6Fornalha", "§7Clique para selecionar!"));
		} else {
			inv.setItem(32, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cFornalha", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.enderchest")){
			inv.setItem(33, ItemAPI.criarItem(Material.ENDER_CHEST, "§6Ender Chest", "§7Clique para selecionar!"));
		} else {
			inv.setItem(33, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cEnder Chest", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.bau")){
			inv.setItem(39,ItemAPI.criarItem(Material.CHEST, "§6Bau", "§7Clique para selecionar!"));
		} else {
			inv.setItem(39, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cBau", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.folha")){
			inv.setItem(40, ItemAPI.criarItem(Material.LEAVES, "§6Folha", "§7Clique para selecionar!"));
		} else {
			inv.setItem(40, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cFolha", false, "§7Você não pode selecionar esse hat!"));
		}
		
		if(p.hasPermission("xampslobby.hat.estante")){
			inv.setItem(41, ItemAPI.criarItem(Material.BOOKSHELF, "§6Estante", "§7Clique para selecionar!"));
		} else {
			inv.setItem(41, ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 14, "§cEstante", false, "§7Você não pode selecionar esse hat!"));
		}
		
		inv.setItem(49, ItemAPI.criarItem(Material.BARRIER, "§cRemover"));
		
	    ItemStack[] arrayOfItemStack;
	    int deschats = (arrayOfItemStack = inv.getContents()).length;
	    for (int metahats = 0; metahats < deschats; metahats++){
	      ItemStack item = arrayOfItemStack[metahats];
	      if (item == null) {
	        inv.setItem(inv.firstEmpty(), ItemAPI.criarItem(Material.STAINED_GLASS_PANE, " "));
	      }
	    }
	    
		p.openInventory(inv);
	}
	
	

}
