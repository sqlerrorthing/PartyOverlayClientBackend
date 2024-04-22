package me.oneqxz.partyoverlay.backend.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.listeners.AuthListener;
import me.oneqxz.partyoverlay.backend.listeners.PartyListener;
import me.oneqxz.partyoverlay.backend.network.protocol.event.EventRegistry;
import me.oneqxz.partyoverlay.backend.network.protocol.exception.PacketRegistrationException;
import me.oneqxz.partyoverlay.backend.network.protocol.handler.PacketChannelInboundHandler;
import me.oneqxz.partyoverlay.backend.network.protocol.handler.PacketDecoder;
import me.oneqxz.partyoverlay.backend.network.protocol.handler.PacketEncoder;
import me.oneqxz.partyoverlay.backend.network.protocol.registry.IPacketRegistry;
import me.oneqxz.partyoverlay.backend.network.protocol.registry.SimplePacketRegistry;
import me.oneqxz.partyoverlay.backend.sctructures.AuthCredits;

@Log4j2
public class ServerConnection {

    private static ServerConnection INSTANCE;
    private static final long RECONNECT_DELAY_MS = 2000L;
    private IPacketRegistry packetRegistry;
    private EventRegistry eventRegistry;
    @Getter
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
                new AuthListener(),
                new PartyListener()
        );

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        log.info("Connecting to server...");
        new Thread(() -> {
            while (true) {
                try {
                    AuthCredits credits = PartyOverlayBackend.getInstance().getAuthCredits();

                    if(credits.getUsername().isEmpty() || credits.getPassword().isEmpty())
                    {
                        Thread.sleep(200L);
                        continue;
                    }

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

                    ChannelFuture ctx = b.connect("localhost", 1488).sync();
                    connection = ctx;

                    log.info("Connected!");

                    ctx.channel().closeFuture().sync();
                    Thread.sleep(RECONNECT_DELAY_MS);
                } catch (Exception e) {
                    log.error("Can't connect to server!", e);
                    PartyOverlayBackend.getInstance().getListener().onServerErrorDisconnect();
                    try {
                        Thread.sleep(RECONNECT_DELAY_MS);
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }, "PartyOverlay server connection").start();
    }

    public void disconnect(boolean resetCredits)
    {
        if(this.connection != null && this.connection.channel().isOpen())
        {
            if(resetCredits)
                PartyOverlayBackend.getInstance().setAuthCredits(new AuthCredits("", ""));

            this.connection.channel().close();
        }
    }

    public static ServerConnection getInstance()
    {
        return INSTANCE == null ? INSTANCE = new ServerConnection() : INSTANCE;
    }
}
