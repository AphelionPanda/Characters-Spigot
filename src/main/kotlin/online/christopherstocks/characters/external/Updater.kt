package online.christopherstocks.characters.external

import online.christopherstocks.characters.Characters
import online.christopherstocks.characters.libs.Config
import org.bukkit.plugin.Plugin
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class Updater {
    private var checkURL: URL? = null
    var latestVersion: String? = null
    val plugin: Plugin

    init {
        this.plugin = Config().getInstance(); this.latestVersion = plugin.description.version
        this.checkURL = URL("https://api.spigotmc.org/legacy/update.php?resource=45142")
    }

    fun checkForUpdates(): Boolean {
        val con = checkURL!!.openConnection()
        this.latestVersion = BufferedReader(InputStreamReader(con.getInputStream())).readLine()
        return plugin.description.version != latestVersion
    }
}