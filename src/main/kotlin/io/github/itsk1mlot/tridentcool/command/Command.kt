package io.github.itsk1mlot.tridentcool.command

import io.github.itsk1mlot.tridentcool.Main
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Command: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender.hasPermission("trident.command")) {
            if (args?.isEmpty() == false) {
                if (args[0] == "reload") {
                    Main.instance.reloadConfig()
                    sender.sendMessage(Component.text("§6[TridentCooldown] §aConfig 파일이 리로드되었습니다!"))
                    return true
                } else if (args[0] == "toggle") {
                    if (Main.toggled) {
                        Main.toggled = false
                        val cooldown = Main.instance.config.get("cooldown")
                        sender.sendMessage(Component.text("§6[TridentCooldown] §a이제 급류 삼지창은 ${cooldown}초의 쿨타임을 가집니다. §7(무시 권한: trident.bypass)"))
                    } else if (!Main.toggled) {
                        Main.toggled = true
                        sender.sendMessage(Component.text("§6[TridentCooldown] §c이제 급류 삼지창은 쿨타임을 가지지 않습니다."))
                    }
                } else {
                    sender.sendMessage(Component.text("§6[TridentCooldown] §c이해할 수 없는 인수입니다. /trident [reload/toggle] 로 사용해주세요."))
                }
            } else {
                sender.sendMessage(Component.text("§6[TridentCooldown] §c/trident [reload/toggle] 로 사용해주세요."))
                return true
            }
        } else {
            sender.sendMessage(Component.text("§6[TridentCooldown] §c명령어를 사용하기 위한 권한이 없습니다."))
            return true
        }
        return false
    }
}