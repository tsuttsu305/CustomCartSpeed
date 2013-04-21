package net.tsuttsu305.CustomCartSpeed;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCartSpeed extends JavaPlugin {
	public static CustomCartSpeed plugin;
	Logger logger = Logger.getLogger("Minecraft");
	//↓static参照にしてもいい 0.4Dはデフォルト値(ロードエラー時にバグらないように)
	public double confmax = 0.4D;

	public void onEnable(){
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " is Enabled");
		
		
		//Event登録
		getServer().getPluginManager().registerEvents(new CartMoveEvent(this), this);
		
		//コンフィグがない場合jarからコピーする(実際にはまだ)
		getConfig().options().copyDefaults(true);
		//セーブ(実際に保存)
		saveConfig();
		//コンフィグから値をロード。
		confmax = getConfig().getDouble("max");
	}

	public void onDisable(){
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " is Disable");
	}
}
