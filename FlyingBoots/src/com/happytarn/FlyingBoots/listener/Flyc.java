package com.happytarn.FlyingBoots.listener;

import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import com.happytarn.FlyingBoots.Main;

public class Flyc implements Listener {

	public Main main;

	public Flyc(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getCause() == DamageCause.FALL && event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			boolean isFlyBoots = false;
			for (ItemStack item : player.getInventory()) {
				if(item == null){
					continue;
				}
				if ((item.getType() == Material.GOLD_BOOTS || item.getType() == Material.LEATHER_BOOTS
						|| item.getType() == Material.DIAMOND_BOOTS || item.getType() == Material.IRON_BOOTS)
						&& item.getItemMeta().getLore() != null
						&& ((String) item.getItemMeta().getLore().get(0)).contains("FlyingBoots")) {
					isFlyBoots = true;
					break;
				}
			}
			if ((player.getHealth() - event.getDamage()) < 0 && isFlyBoots) {
				event.setDamage(player.getHealth() -1);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if (p.getGameMode() == GameMode.CREATIVE) {
			p.setAllowFlight(true);
			p.setFlySpeed(0.1F);
			return;
		}
		if (p.getEquipment().getBoots() == null) {
			p.setAllowFlight(false);
		} else {
			boolean x = event.getFrom().getX() == event.getTo().getX() ? true : false;
			boolean y = event.getFrom().getY() == event.getTo().getY() ? true : false;
			boolean z = event.getFrom().getZ() == event.getTo().getZ() ? true : false;
			try {
				if (((String) p.getEquipment().getBoots().getItemMeta().getLore().get(0)).contains("FlyingBoots")) {
					if (((String) p.getEquipment().getBoots().getItemMeta().getLore().get(1)).contains("Speed:1")) {
						String i = Main.config.getString("LEATHER_SPEED");
						float speed = Float.parseFloat(i);
						p.setAllowFlight(true);
						p.setFlySpeed(speed);
						damageBoots(p, p.getEquipment().getBoots(), x, y, z);
						if (p.getEquipment().getBoots().getDurability() > 65) {
							kowareru(p);
						}
					}
					if (((String) p.getEquipment().getBoots().getItemMeta().getLore().get(1)).contains("Speed:2")) {
						String i = Main.config.getString("GOLD_SPEED");
						float speed = Float.parseFloat(i);
						p.setAllowFlight(true);
						p.setFlySpeed(speed);
						damageBoots(p, p.getEquipment().getBoots(), x, y, z);
						if (p.getEquipment().getBoots().getDurability() > 91) {
							kowareru(p);
						}
					}
					if (((String) p.getEquipment().getBoots().getItemMeta().getLore().get(1)).contains("Speed:3")) {
						String i = Main.config.getString("IRON_SPEED");
						float speed = Float.parseFloat(i);
						p.setAllowFlight(true);
						p.setFlySpeed(speed);
						damageBoots(p, p.getEquipment().getBoots(), x, y, z);
						if (p.getEquipment().getBoots().getDurability() > 195) {
							kowareru(p);
						}
					}

					if (((String) p.getEquipment().getBoots().getItemMeta().getLore().get(1)).contains("Speed:4")) {
						String i = Main.config.getString("DIAMOND_SPEED");
						float speed = Float.parseFloat(i);
						p.setAllowFlight(true);
						p.setFlySpeed(speed);
						damageBoots(p, p.getEquipment().getBoots(), x, y, z);
						if (p.getEquipment().getBoots().getDurability() > 429) {
							kowareru(p);
						}
					}
				} else {
					p.setAllowFlight(false);
				}
			}

			catch (Exception localException) {
				if (!(p.hasPermission("essentials.fly") | (p.getGameMode() == GameMode.CREATIVE)))

					p.setAllowFlight(false);
			}
		}
	}

	private void kowareru(Player p) {
		for (Player player : main.getServer().getOnlinePlayers()) {
			player.playSound(p.getLocation(), Sound.EXPLODE, 1F, 1F);
		}
		p.getInventory().setBoots(new ItemStack(Material.AIR));
	}

	private void damageBoots(Player p, ItemStack boots, boolean x, boolean y, boolean z) {

		// 移動してなかった場合
		if (x && y && z) {
			return;
		}

		int taikyuu = 1;
		if (boots.getEnchantments().containsKey(Enchantment.DURABILITY)) {
			Integer level = boots.getEnchantments().get(Enchantment.DURABILITY);
			taikyuu = taikyuu + level;
		}

		if (y) {
			taikyuu = taikyuu + 3;
		}

		Random ran = new Random();
		if (ran.nextInt(taikyuu) == 0) {
			for (Player player : main.getServer().getOnlinePlayers()) {
				player.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 256);
				player.playEffect(p.getLocation(), Effect.SMOKE, 256);
			}
			boots.setDurability((short) (boots.getDurability() + 1));
		}

	}

}
