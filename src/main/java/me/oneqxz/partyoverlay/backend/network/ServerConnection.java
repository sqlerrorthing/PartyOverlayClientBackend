package me.oneqxz.partyoverlay.backend.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.extern.log4j.Log4j2;
import me.oneqxz.partyoverlay.backend.listeners.AuthListener;
import me.oneqxz.partyoverlay.backend.network.protocol.event.EventRegistry;
import me.oneqxz.partyoverlay.backend.network.protocol.exception.PacketRegistrationException;
import me.oneqxz.partyoverlay.backend.network.protocol.handler.PacketChannelInboundHandler;
import me.oneqxz.partyoverlay.backend.network.protocol.handler.PacketDecoder;
import me.oneqxz.partyoverlay.backend.network.protocol.handler.PacketEncoder;
import me.oneqxz.partyoverlay.backend.network.protocol.registry.IPacketRegistry;
import me.oneqxz.partyoverlay.backend.network.protocol.registry.SimplePacketRegistry;

@Log4j2
public class ServerConnection {

    private static ServerConnection INSTANCE;
    private static final long RECONNECT_DELAY_MS = 5000L;
    private IPacketRegistry packetRegistry;
    private EventRegistry eventRegistry;
    private ChannelFuture connection;

    public void connect()
    {
        packetRegistry = new SimplePacketRegistry();
        try {
            ((SimplePacketRegistry) packetRegistry).registerPackets();
        } catch (PacketRegistrationException e) {
            throw new RuntimeException(e);
        }

        eventRegistry = new EventRegistry();
        eventRegistry.registerEvents(
                new AuthListener()
        );

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        log.info("Connecting to server...");
        new Thread(() -> {
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

                    log.info("Connected!");

                    ctx.channel().closeFuture().sync();
                    Thread.sleep(RECONNECT_DELAY_MS);
                } catch (Exception e) {
                    log.error("Can't connect to server!", e);
                    try {
                        Thread.sleep(RECONNECT_DELAY_MS);
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }, "PartyOverlay server connection").start();
    }

    public ChannelFuture getConnection() {
        return connection;
    }

    public static ServerConnection getInstance()
    {
        return INSTANCE == null ? INSTANCE = new ServerConnection() : INSTANCE;
    }
}
