package llama.capability;

import llama.capability.networking.NetworkingPackets;
import llama.capability.state.PlayerState;
import llama.capability.state.ServerState;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CapabilityMod implements ModInitializer {
	public static final String MOD_ID = "capability";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric mod "+ MOD_ID +"!");

		NetworkingPackets.registerC2SPackets();

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerState serverState = ServerState.getServerState(handler.player.server);
			PlayerState playerState = ServerState.getPlayerState(handler.player);
		});

		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
			if(!world.isClient) {
				PlayerState playerState = ServerState.getPlayerState(player);
				ServerState serverState = ServerState.getServerState(world.getServer());

				playerState.setCoins(playerState.getCoins() + 10);
				serverState.markDirty();
				player.sendMessage(Text.of("coins: "+ playerState.getCoins()));
			}

			return ActionResult.PASS;
        });
	}
}