package llama.capability.networking;

import llama.capability.CapabilityMod;
import llama.capability.capability.CoinsCapability;
import llama.capability.networking.packets.RequestPlayerStateC2SPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class NetworkingPackets {
    //C2S
    public static final Identifier REQUEST_PLAYER_STATE_C2S_PACKET_ID = new Identifier(CapabilityMod.MOD_ID, "request_player_state_c2s_packet");
    //S2C
    public static final Identifier SEND_PLAYERSTATE_S2C_PACKET_ID = new Identifier(CapabilityMod.MOD_ID, "send_player_state_s2c_packet");
    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(REQUEST_PLAYER_STATE_C2S_PACKET_ID, RequestPlayerStateC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(SEND_PLAYERSTATE_S2C_PACKET_ID, (client, handler, buf, responseSender) -> {
            int coins = buf.readInt();
            client.execute(() -> {
                CoinsCapability.setCoins(coins);
            });
        });
    }
}
