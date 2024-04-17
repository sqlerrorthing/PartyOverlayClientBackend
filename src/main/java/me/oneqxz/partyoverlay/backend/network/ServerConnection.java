package me.oneqxz.partyoverlay.backend.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import me.oneqxz.partyoverlay.backend.network.protocol.event.EventRegistry;
import me.oneqxz.partyoverlay.backend.network.protocol.exception.PacketRegistrationException;
import me.oneqxz.partyoverlay.backend.network.protocol.handler.PacketChannelInboundHandler;
import me.oneqxz.partyoverlay.backend.network.protocol.handler.PacketDecoder;
import me.oneqxz.partyoverlay.backend.network.protocol.handler.PacketEncoder;
import me.oneqxz.partyoverlay.backend.network.protocol.listeners.GlobalPacketListener;
import me.oneqxz.partyoverlay.backend.network.protocol.registry.IPacketRegistry;
import me.oneqxz.partyoverlay.backend.network.protocol.registry.SimplePacketRegistry;


public class ServerConnection {

    private static final long RECONNECT_DELAY_MS = 5000L;
    private static IPacketRegistry packetRegistry;
    private static EventRegistry eventRegistry;
    private static ChannelFuture connection;

    public static void connect()
    {
        packetRegistry = new SimplePacketRegistry();
        try {
            ((SimplePacketRegistry) packetRegistry).registerPackets();
        } catch (PacketRegistrationException e) {
            throw new RuntimeException(e);
        }

        eventRegistry = new EventRegistry();
        eventRegistry.registerEvents(new GlobalPacketListener());

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        while (true) {
            try {
                Bootstrap b = new Bootstrap();
                b.group(workerGroup)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<Channel>() {
                            @Override
                            protected void initChannel(Channel ch) throws Exception {
                                ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                                ch.pipeline().addLast(new PacketDecoder(packetRegistry));

                                ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                                ch.pipeline().addLast(new PacketEncoder(packetRegistry));

                                ch.pipeline().addLast(new ConnectionHandler());
                                ch.pipeline().addLast(new PacketChannelInboundHandler(eventRegistry));
                            }
                        })
                        .option(ChannelOption.AUTO_READ, true);

                ChannelFuture ctx = b.connect("localhost", 8080).sync();
                connection = ctx;

                ctx.channel().closeFuture().sync();
                Thread.sleep(RECONNECT_DELAY_MS);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(RECONNECT_DELAY_MS);
                } catch (InterruptedException ex) {

                }
            }
        }
    }

    public static ChannelFuture getConnection() {
        return connection;
    }
}
