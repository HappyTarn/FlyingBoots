package com.happytarn.FlyingBoots.util;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.happytarn.FlyingBoots.Main;

public class ConfigurationManager {
	public final static Logger log = Main.log;

	@SuppressWarnings("unused")
	private JavaPlugin plugin;
	private FileConfiguration conf;


	/**
	 * 設定ファイルから設定を読み込む
	 * @param plugin JavaPlugin
	 */
	public void load(final JavaPlugin plugin){
		this.plugin = plugin;
		// 設定ファイルを読み込む
		plugin.reloadConfig();
		conf = plugin.getConfig();
	}


	public String getString(String string) {
		return conf.getString(string, "");
	}

	/* 以下設定取得用getter */

}
