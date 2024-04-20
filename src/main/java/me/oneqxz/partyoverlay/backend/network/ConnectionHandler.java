package me.oneqxz.partyoverlay.backend.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;

public class ConnectionHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        PartyOverlayBackend.getInstance().setSession(null);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        PartyOverlayBackend.getInstance().setSession(null);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        PartyOverlayBackend.getInstance().setSession(null);

        super.exceptionCaught(ctx, cause);
    }
}
