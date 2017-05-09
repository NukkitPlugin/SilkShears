package nukkitplugin.silkshears;

import cn.nukkit.plugin.PluginBase;
import nukkitplugin.silkshears.listener.BlockBreakListener;

public class SilkShears extends PluginBase {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
	}
}
