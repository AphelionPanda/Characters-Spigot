package online.christopherstocks.characters.listeners

import online.christopherstocks.characters.libs.Config
import online.christopherstocks.characters.libs.Storage
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoin : Listener {

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val storage: Storage = Config().getStorage(); val player: Player = e.player
        if (storage.checkPlayerExists(player.name)) storage.updatePlayer(player)
        else if (player.hasPermission("characters.use")) storage.createPlayer(player); storage.updatePlayer(player)
        // Not fixed yet
        /*if (player.hasPermission("characters.update")){
            if (Updater().check()) ChatOptions(player).sendMessage(Config().getString("update"))
            else ChatOptions(player).sendMessage(Config().getString("no-update"))
        }*/
    }

}