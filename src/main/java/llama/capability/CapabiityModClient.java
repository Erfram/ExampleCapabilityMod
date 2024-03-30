package llama.capability;

import llama.capability.networking.NetworkingPackets;
import net.fabricmc.api.ClientModInitializer;

public class CapabiityModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NetworkingPackets.registerS2CPackets();
    }
}
