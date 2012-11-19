package jp.tsuttsu305.CustomCartSpeed;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCartSpeed extends JavaPlugin {
	public static CustomCartSpeed plugin;
	Logger logger = Logger.getLogger("Minecraft");
	public double confmax = 0.4D;

	public void onEnable(){
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " is Enabled");
		//Event登録
		getServer().getPluginManager().registerEvents(new CartMoveEvent(this), this);

		getConfig().options().copyDefaults(true);
		saveConfig();
		confmax = getConfig().getDouble("max");
	}

	public void onDisable(){
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " is Disable");
	}
}
