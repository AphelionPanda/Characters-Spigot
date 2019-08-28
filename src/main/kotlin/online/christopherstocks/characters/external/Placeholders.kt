package online.christopherstocks.characters.external

import me.clip.placeholderapi.external.EZPlaceholderHook
import online.christopherstocks.characters.libs.Config
import online.christopherstocks.characters.libs.Storage
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin


class Placeholders(instance: Plugin) : EZPlaceholderHook(instance, "characters") {

    override fun onPlaceholderRequest(player: Player, s: String?): String? {
        val config = Config()
        val storage: Storage = config.getStorage()
        if (storage.checkPlayerExists(player.name)) storage.updatePlayer(player)
        else storage.createPlayer(player)

        if (s.equals("slot", ignoreCase = true)) return (storage.getActive(player.name) + 1).toString()
        config.getConfig().getStringList("fields").forEach {if (s.equals(it, ignoreCase = true)) return storage.getField(player.name, it)}

        return "Empty"
    }
}