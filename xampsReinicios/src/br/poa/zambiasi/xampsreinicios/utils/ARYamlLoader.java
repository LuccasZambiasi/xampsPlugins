package br.poa.zambiasi.xampsreinicios.utils;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;

import br.poa.zambiasi.xampsreinicios.Main;

public class ARYamlLoader {

	private Main main;

	public ARYamlLoader(Main main) {
		this.main = main;
	}

	public void loadYamlulus() {
		try {
			main.configC.load(main.config);
		} catch (IOException | InvalidConfigurationException e) {
			Bukkit.getConsoleSender().sendMessage(" ");
			Bukkit.getConsoleSender().sendMessage("§a§l(xampsReinicios) §7falha ao carregar a config!");
			Bukkit.getConsoleSender().sendMessage(" ");
			e.printStackTrace();
		}
	}

}
