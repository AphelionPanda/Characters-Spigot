package online.christopherstocks.characters.libs

import org.bukkit.entity.Player
import java.sql.Connection

// Use this class in conjunction with Config and MySQL to add in your own Storage Method
abstract class Storage {

    val config = Config()
    val database = config.getConfig().getString("database")!!
    val table_prefix = config.getConfig().getString("table_prefix")!!
    val inactive = config.getConfig().getDouble("inactive-days")

    // Async
    abstract fun createDatabase()
    abstract fun updatePlugin()
    abstract fun updateFields()
    abstract fun deleteInactivePlayers()
    abstract fun createPlayer(player: Player)
    abstract fun updatePlayer(player: Player)

    // Sync
    abstract fun openConnection(): Connection
    abstract fun getField(name: String, field: String, slot: Int = getActive(name)): String
    abstract fun setField(name: String, field: String, value: String, slot: Int = getActive(name))

    abstract fun checkPlayerExists(name: String): Boolean
    abstract fun getActive(name: String): Int
    abstract fun setActive(name: String, value: Int)
    abstract fun reset(name: String, slot: Int = getActive(name))
}