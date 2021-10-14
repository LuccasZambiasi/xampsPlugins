package br.poa.zambiasi.xampsreinicios.utils;

import java.io.IOException;

import org.bukkit.Bukkit;

import br.poa.zambiasi.xampsreinicios.Main;

public class ARSaveConfig {

	private Main main;

	public ARSaveConfig(Main main) {
		this.main = main;
	}

	public void saveConfigC() {
		try {
			main.configC.save(main.config);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(" ");
			Bukkit.getConsoleSender().sendMessage("§a§l(xampsReinicios) §7falha ao salvar a config!");
			Bukkit.getConsoleSender().sendMessage(" ");
			e.printStackTrace();
		}
	}

}
