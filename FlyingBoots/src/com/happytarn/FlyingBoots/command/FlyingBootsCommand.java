package com.happytarn.FlyingBoots.command;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.happytarn.FlyingBoots.Main;

public class FlyingBootsCommand implements CommandExecutor {

	public final Logger log = Main.log;
	private Main plugin;

	/**
	 * デフォルトコンストラクタ
	 * @param plugin
	 */
	public FlyingBootsCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

		if(commandLabel.equalsIgnoreCase("flyingboots") || commandLabel.equalsIgnoreCase("fb")){
			//引数なし
			if(args.length == 0){
				if(sender instanceof Player){
					Player player = (Player) sender;
					//権限チェック
					if(player.hasPermission("hapitanCmd.flyingboots.admin")){
						player.sendMessage("引数無しでは実行できません。/fb help で確認してください。");
						return true;
					}else{
						player.sendMessage("権限がありません。hapitanCmd.flyingboots.admin");
						return true;
					}
				}else{
					sender.sendMessage("引数無しでは実行できません。/fb help で確認してください。");
					return true;
				}
			}

			// coatingObsidian reload
			if(args.length >= 1 && (args[0].equalsIgnoreCase("reload"))){
				if(sender instanceof Player){
					Player player = (Player) sender;
					//権限チェック
					if(!player.hasPermission("hapitanCmd.flyingboots.admin")){
						player.sendMessage("権限がありません。hapitanCmd.flyingboots.admin");
						return true;
					}
					plugin.loadConfigFile();
					sender.sendMessage(new StringBuffer().append(ChatColor.GOLD).append("リロード完了！").toString());
					return true;
				}else{
					sender.sendMessage("引数無しでは実行できません。/fb help で確認してください。");
					return true;
				}
			}

			// coatingObsidian reload
			if(args.length >= 2 && (args[0].equalsIgnoreCase("get"))){
				if(sender instanceof Player){
					Player player = (Player) sender;
					//権限チェック
					if(!player.hasPermission("hapitanCmd.flyingboots.admin")){
						player.sendMessage("権限がありません。hapitanCmd.flyingboots.admin");
						return true;
					}

					if(args[1].equals("1")){
						ItemStack db = new ItemStack(Material.LEATHER_BOOTS);
						ItemMeta meta = db.getItemMeta();
						meta.setDisplayName("\247aI can Fly！(コマンド産)");
						ArrayList lore = new ArrayList();
						lore.add("\247eFlyingBoots");
						lore.add("\247eSpeed:1");
						meta.setLore(lore);
						db.setItemMeta(meta);
						player.getInventory().addItem(new ItemStack(db));
					}
					if(args[1].equals("2")){
						ItemStack db = new ItemStack(Material.GOLD_BOOTS);
						ItemMeta meta = db.getItemMeta();
						meta.setDisplayName("\247aI can Fly！(コマンド産)");
						ArrayList lore = new ArrayList();
						lore.add("\247eFlyingBoots");
						lore.add("\247eSpeed:2");
						meta.setLore(lore);
						db.setItemMeta(meta);
						player.getInventory().addItem(new ItemStack(db));
					}
					if(args[1].equals("3")){
						ItemStack db = new ItemStack(Material.IRON_BOOTS);
						ItemMeta meta = db.getItemMeta();
						meta.setDisplayName("\247aI can Fly！(コマンド産)");
						ArrayList lore = new ArrayList();
						lore.add("\247eFlyingBoots");
						lore.add("\247eSpeed:3");
						meta.setLore(lore);
						db.setItemMeta(meta);
						player.getInventory().addItem(new ItemStack(db));
					}
					if(args[1].equals("4")){
						ItemStack db = new ItemStack(Material.DIAMOND_BOOTS);
						ItemMeta meta = db.getItemMeta();
						meta.setDisplayName("\247aI can Fly！(コマンド産)");
						ArrayList lore = new ArrayList();
						lore.add("\247eFlyingBoots");
						lore.add("\247eSpeed:4");
						meta.setLore(lore);
						db.setItemMeta(meta);
						player.getInventory().addItem(new ItemStack(db));
					}
					return true;
				}else{
					sender.sendMessage("引数無しでは実行できません。/fb help で確認してください。");
					return true;
				}
			}


			if (args.length >= 1 && (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h"))){
				sender.sendMessage("作者に聞いてください");
				return true;
			}
		}
		return false;
	}



}
