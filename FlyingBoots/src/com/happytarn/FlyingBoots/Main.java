package com.happytarn.FlyingBoots;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.happytarn.FlyingBoots.command.FlyingBootsCommand;
import com.happytarn.FlyingBoots.listener.Flyc;
import com.happytarn.FlyingBoots.util.ConfigurationManager;
import com.happytarn.FlyingBoots.util.FileDirectoryStructure;

public class Main extends JavaPlugin {
	public static Main plugin;
	public static ConfigurationManager config;
	public static Logger log;
	public Main() {
	}

	public void onEnable() {

		log = getLogger();
		plugin = this;
		loadConfigFile();
//		LetherBoots();
//		GoldBoots();
//		IronBoots();
//		DiamondBoots();
		saveConfig();
		getServer().getPluginManager().registerEvents(new Flyc(this), this);
		//コマンド登録
		getServer().getPluginCommand("flyingBoots").setExecutor(new FlyingBootsCommand(this));

		getLogger().info("[FlyingBoots] Loading FlyingBoots plugin by Ittrio");
	}

	public void LetherBoots() {
		ItemStack db = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta meta = db.getItemMeta();
		meta.setDisplayName("\247aI can Fly！(皮)");
		ArrayList lore = new ArrayList();
		lore.add("\247eFlyingBoots");
		lore.add("\247eSpeed:1");
		meta.setLore(lore);
		db.setItemMeta(meta);
		ShapedRecipe recipe = new ShapedRecipe( new ItemStack(db));
		recipe.shape(new String[] {"ABC", "DEF", "GHI"});
		recipe.setIngredient('A', Material.COAL_ORE);
		recipe.setIngredient('B', Material.REDSTONE_ORE);
		recipe.setIngredient('C', Material.COAL_ORE);

		recipe.setIngredient('D', Material.FEATHER);
		recipe.setIngredient('E', Material.LEATHER_BOOTS);
		recipe.setIngredient('F', Material.FEATHER);

		recipe.setIngredient('G', Material.LEATHER);
		recipe.setIngredient('H', Material.PISTON_BASE);
		recipe.setIngredient('I', Material.LEATHER);
		Bukkit.addRecipe(recipe);
	}

	public void DiamondBoots() {
		ItemStack db = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta meta = db.getItemMeta();
		meta.setDisplayName("\247aI can Fly！(ダイア)");
		ArrayList lore = new ArrayList();
		lore.add("\247eFlyingBoots");
		lore.add("\247eSpeed:4");
		meta.setLore(lore);
		db.setItemMeta(meta);
		ShapedRecipe recipe = new ShapedRecipe(new ItemStack(db));

		recipe.shape(new String[] {"ABC", "DEF", "GHI"});
		recipe.setIngredient('A', Material.DIAMOND_BARDING);
		recipe.setIngredient('B', Material.EMERALD_ORE);
		recipe.setIngredient('C', Material.DIAMOND_BARDING);

		recipe.setIngredient('D', Material.NAME_TAG);
		recipe.setIngredient('E', Material.DIAMOND_BOOTS);
		recipe.setIngredient('F', Material.NAME_TAG);

		recipe.setIngredient('G', Material.SPONGE);
		recipe.setIngredient('H', Material.PISTON_BASE);
		recipe.setIngredient('I', Material.SPONGE);
		Bukkit.addRecipe(recipe);
	}

	public void GoldBoots() {
		ItemStack db = new ItemStack(Material.GOLD_BOOTS);
		ItemMeta meta = db.getItemMeta();
		meta.setDisplayName("\247aI can Fly！(金)");
		ArrayList lore = new ArrayList();
		lore.add("\247eFlyingBoots");
		lore.add("\247eSpeed:2");
		meta.setLore(lore);
		db.setItemMeta(meta);
		ShapedRecipe recipe = new ShapedRecipe(new ItemStack(db));

		recipe.shape(new String[] {"ABC", "DEF", "GHI"});
		recipe.setIngredient('A', Material.COAL_ORE);
		recipe.setIngredient('B', Material.GOLD_ORE);
		recipe.setIngredient('C', Material.COAL_ORE);

		recipe.setIngredient('D', Material.FEATHER);
		recipe.setIngredient('E', Material.GOLD_BOOTS);
		recipe.setIngredient('F', Material.FEATHER);

		recipe.setIngredient('G', Material.SPONGE);
		recipe.setIngredient('H', Material.PISTON_BASE);
		recipe.setIngredient('I', Material.SPONGE);
		getServer().addRecipe(recipe);
	}

	public void IronBoots() {
		ItemStack db = new ItemStack(Material.IRON_BOOTS);
		ItemMeta meta = db.getItemMeta();
		meta.setDisplayName("\247aI can Fly！(鉄)");
		ArrayList lore = new ArrayList();
		lore.add("\247eFlyingBoots");
		lore.add("\247eSpeed:3");
		meta.setLore(lore);
		db.setItemMeta(meta);
		ShapedRecipe recipe = new ShapedRecipe(new ItemStack(db));

		recipe.shape(new String[] {"ABC", "DEF", "GHI"});
		recipe.setIngredient('A', Material.COAL_ORE);
		recipe.setIngredient('B', Material.DIAMOND_ORE);
		recipe.setIngredient('C', Material.COAL_ORE);

		recipe.setIngredient('D', Material.FEATHER);
		recipe.setIngredient('E', Material.IRON_BOOTS);
		recipe.setIngredient('F', Material.FEATHER);

		recipe.setIngredient('G', Material.SPONGE);
		recipe.setIngredient('H', Material.PISTON_BASE);
		recipe.setIngredient('I', Material.SPONGE);
		getServer().addRecipe(recipe);
	}

	public void onDisable() {

		getLogger().info("[FlyingBoots] Disable FlyingBoots plugin by Ittrio");
	}

	/**
	 * 設定ファイルを読み込む
	 */
	public void loadConfigFile(){
		// ファイルマネージャセットアップ
		FileDirectoryStructure.setup();

		// 設定ファイルパスを取得
		String filepath = getDataFolder() + System.getProperty("file.separator") + "config.yml";
		File file = new File(filepath);

		// 設定ファイルが見つからなければデフォルトのファイルをコピー
		if (!file.exists()){
			this.saveDefaultConfig();
			log.info("config.yml is not found! Created default config.yml!");
		}

		// 設定ファイルを読み込む
		config = new ConfigurationManager();
		try{
			config.load(this);
		}catch(Exception ex){
			log.warning("an error occured while trying to load the config file.");
			ex.printStackTrace();
		}
	}

	public static JavaPlugin getInstance() {
		return plugin;
	}

}
