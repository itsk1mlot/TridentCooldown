package io.github.itsk1mlot.tridentcool.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class TabComplete: TabCompleter {
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): List<String> {
        if(args?.size == 1) {
            return listOf("reload", "toggle")
        }
        return listOf()
    }
}