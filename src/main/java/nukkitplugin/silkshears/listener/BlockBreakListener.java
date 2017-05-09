package nukkitplugin.silkshears.listener;

import static cn.nukkit.event.EventPriority.HIGHEST;
import static cn.nukkit.item.enchantment.Enchantment.ID_SILK_TOUCH;

import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.item.Item;
import cn.nukkit.scheduler.Task;

public class BlockBreakListener implements Listener {
	@EventHandler(priority = HIGHEST)
	public void onPlayerInteract(BlockBreakEvent event) {
		if (!event.isCancelled()) {
			Item item = event.getItem();
			if (item.isShears() && item.getEnchantment(ID_SILK_TOUCH) != null) {
				Block block = event.getBlock();
				if (block.getDamage() >= 0x07) {
					Item[] drops = event.getDrops();
					switch (block.getId()) {
						case Block.WHEAT_BLOCK:
						case Block.BEETROOT_BLOCK:
							if (drops[1].count-- > 0)
								drops[1].count--;
							break;
						case Block.CARROT_BLOCK:
						case Block.POTATO_BLOCK:
							drops[0].count--;
							break;
					}
					event.setDrops(drops);
					Server.getInstance().getScheduler().scheduleDelayedTask(new Task() {
						public void onRun(int currentTick) {
							block.getLevel().setBlock(block, Block.get(block.getId()));
						}
					}, 10);
				}
			}
		}
	}
}
