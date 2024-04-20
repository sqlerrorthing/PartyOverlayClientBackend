package me.oneqxz.partyoverlay.backend;

import lombok.Getter;
import lombok.Setter;
import me.oneqxz.partyoverlay.backend.listeners.IEventListener;
import me.oneqxz.partyoverlay.backend.listeners.InternalEvents;
import me.oneqxz.partyoverlay.backend.network.ServerConnection;
import me.oneqxz.partyoverlay.backend.providers.IMinecraft;
import me.oneqxz.partyoverlay.backend.providers.IMinecraftSession;
import me.oneqxz.partyoverlay.backend.sctructures.AuthCredits;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 17.04.2024
 */
@Getter
public final class PartyOverlayBackend {

    private static PartyOverlayBackend INSTANCE;
    @Setter
    @Nullable private AuthCredits authCredits = new AuthCredits("", "");

    @Getter @Setter private Session session;

    private IMinecraft minecraft;
    private IMinecraftSession minecraftSession;
    private IEventListener listener;
    private InternalEvents internalEvents;
    private boolean init;

    public void init(@NotNull IMinecraft minecraft,
                     @NotNull IMinecraftSession session,
                     @NotNull IEventListener listener)
    {
        this.minecraft = minecraft;
        this.minecraftSession = session;
        this.listener = listener;
        this.internalEvents = new InternalEvents();

        ServerConnection.getInstance().connect();
        this.init = true;
    }

    public boolean isConnected()
    {
        return ServerConnection.getInstance().getConnection().channel().isOpen();
    }

    public static PartyOverlayBackend getInstance()
    {
        return INSTANCE == null ? INSTANCE = new PartyOverlayBackend() : INSTANCE;
    }

}
