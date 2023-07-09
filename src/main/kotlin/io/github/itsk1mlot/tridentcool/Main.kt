package io.github.itsk1mlot.tridentcool

import io.github.itsk1mlot.tridentcool.command.Command
import io.github.itsk1mlot.tridentcool.command.TabComplete
import io.github.itsk1mlot.tridentcool.listener.Interact
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {
    companion object {
        lateinit var instance: Main
        var toggled: Boolean = false
    }
    init {
        instance = this
    }
    override fun onEnable() {
        println("""
             _____        _      _               _   
            |_   _|      (_)    | |             | |  
              | |   _ __  _   __| |  ___  _ __  | |_ 
              | |  | '__|| | / _` | / _ \| '_ \ | __|
              | |  | |   | || (_| ||  __/| | | || |_ 
              \_/  |_|   |_| \__,_| \___||_| |_| \__|
             _____                _      _                         
            /  __ \              | |    | |                        
            | /  \/  ___    ___  | |  __| |  ___  __      __ _ __  
            | |     / _ \  / _ \ | | / _` | / _ \ \ \ /\ / /| '_ \ 
            | \__/\| (_) || (_) || || (_| || (_) | \ V  V / | | | |
             \____/ \___/  \___/ |_| \__,_| \___/   \_/\_/  |_| |_|
        """.trimIndent())
        println("[플러그인 버전: 1.0]")
        println("플러그인이 활성화됩니다!")

        // Config File Setup
        saveDefaultConfig()

        // Add Command Executor & TabCompleter
        getCommand("trident")?.setExecutor(Command())
        getCommand("trident")?.tabCompleter = TabComplete()

        // Add Event Listener
        Bukkit.getPluginManager().registerEvents(Interact(), this)
    }

    override fun onDisable() {
        println("""
             _____        _      _               _   
            |_   _|      (_)    | |             | |  
              | |   _ __  _   __| |  ___  _ __  | |_ 
              | |  | '__|| | / _` | / _ \| '_ \ | __|
              | |  | |   | || (_| ||  __/| | | || |_ 
              \_/  |_|   |_| \__,_| \___||_| |_| \__|
             _____                _      _                         
            /  __ \              | |    | |                        
            | /  \/  ___    ___  | |  __| |  ___  __      __ _ __  
            | |     / _ \  / _ \ | | / _` | / _ \ \ \ /\ / /| '_ \ 
            | \__/\| (_) || (_) || || (_| || (_) | \ V  V / | | | |
             \____/ \___/  \___/ |_| \__,_| \___/   \_/\_/  |_| |_|
        """.trimIndent())
        println("[플러그인 버전: 1.0]")
        println("플러그인이 비활성화됩니다!")
    }
}