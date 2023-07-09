package io.github.itsk1mlot.tridentcool.listener

import io.github.itsk1mlot.tridentcool.Main
import io.github.itsk1mlot.tridentcool.misc.Convert
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class Interact: Listener {
    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        val player = event.player
        val seconds = Main.instance.config.getInt("cooldown")
        val cooldownMessage = Main.instance.config.getString("message.cooldown") as String
        val ct = event.action

        if (ct.isRightClick) {
            if (player.inventory.itemInMainHand.type == Material.TRIDENT || player.inventory.itemInOffHand.type == Material.TRIDENT) {
                if(Enchantment.RIPTIDE in player.inventory.itemInMainHand.enchantments || Enchantment.RIPTIDE in player.inventory.itemInOffHand.enchantments) {
                    if (!player.hasPermission("trident.bypass")) {
                        if (player.hasCooldown(Material.TRIDENT)) {
                            val cooldown = player.getCooldown(Material.TRIDENT)
                            val cooldownString = "${Convert.toSeconds(cooldown)}"
                            val cooldownMessageLast = cooldownMessage.replace("[seconds]", cooldownString)
                            player.sendActionBar(Component.text(cooldownMessageLast))
                        } else {
                            Bukkit.getScheduler().runTaskLater(Main.instance, Runnable {
                                player.setCooldown(Material.TRIDENT, Convert.toTick(seconds))
                            }, 1)
                        }
                    }
                }
            }
        }
    }
}