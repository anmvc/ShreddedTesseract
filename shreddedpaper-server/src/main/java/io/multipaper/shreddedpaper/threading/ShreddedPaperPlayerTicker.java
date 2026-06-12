package io.multipaper.shreddedpaper.threading;

import ca.spottedleaf.moonrise.patches.chunk_system.player.RegionizedPlayerChunkLoader;
import ca.spottedleaf.moonrise.common.util.TickThread;
import net.minecraft.server.level.ServerPlayer;

public class ShreddedPaperPlayerTicker {
	public static void tickPlayer(ServerPlayer serverPlayer) {
		if (!TickThread.isTickThreadFor(serverPlayer) || !TickThread.isTickThreadFor(serverPlayer.level(), serverPlayer.blockPosition())) {
			return;
		}

		serverPlayer.connection.connection.tick();
		final RegionizedPlayerChunkLoader.PlayerChunkLoaderData loader = serverPlayer.moonrise$getChunkLoader();
		loader.update(); // can't invoke plugin logic
		loader.updateQueues(System.nanoTime());
	}
}
