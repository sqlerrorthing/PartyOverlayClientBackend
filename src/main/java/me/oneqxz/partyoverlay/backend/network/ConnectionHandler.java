package me.oneqxz.partyoverlay.backend.network;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.manager.PartyManager;

@ChannelHandler.Sharable
public class ConnectionHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        reset();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        reset();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        ctx.fireChannelInactive();
        super.exceptionCaught(ctx, cause);
    }

    private void reset()
    {
        PartyOverlayBackend.getInstance().setSession(null);
        PartyManager.getInstance().reset();
    }
}
