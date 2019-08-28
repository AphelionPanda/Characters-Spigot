package online.christopherstocks.characters

import online.christopherstocks.characters.commands.CharacterCommand
import online.christopherstocks.characters.external.Placeholders
import online.christopherstocks.characters.libs.Config
import online.christopherstocks.characters.libs.Storage
import online.christopherstocks.characters.listeners.PlayerJoin
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class Characters : JavaPlugin() {

    companion object {
        lateinit var instance: Plugin
    }

    override fun onEnable() {
        saveDefaultConfig(); instance = this
        val pluginManager = server.pluginManager; val config = Config(); val storage: Storage = config.getStorage()
        config.enableSlots(); storage.createDatabase()
        pluginManager.registerEvents(PlayerJoin(), this)
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getConfig().set("placeholders", true)
            Placeholders(this).hook()
        } else getConfig().set("placeholders", false)
        if (config.getInt("slots") < 1) config.set("slots", 1)
        getCommand("characters")!!.setExecutor(CharacterCommand())
        for (p in Bukkit.getOnlinePlayers()) {
            if (storage.checkPlayerExists(p.name)) storage.updatePlayer(p)
            else if (p.hasPermission("characters.use")) { storage.createPlayer(p); storage.updatePlayer(p) }
        }
    }
}