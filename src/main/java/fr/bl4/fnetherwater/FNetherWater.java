package fr.bl4.fnetherwater;

import fr.bl4.fnetherwater.listeners.WaterListener;
import org.bukkit.plugin.java.JavaPlugin;

public class FNetherWater extends JavaPlugin {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WaterListener(), this);
    }
}
