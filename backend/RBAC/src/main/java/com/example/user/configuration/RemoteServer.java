package com.example.user.configuration;

import com.demo.config.SecurityProperties;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class RemoteServer {

    private UserRepository repository;
    private int port;
    private List<String> whiteList = new ArrayList<>(0);

    public RemoteServer(UserRepository repository, int port, SecurityProperties properties) {
        this.repository = repository;
        this.port = port;
        List<String> w = Arrays.asList(properties.getWhiteList());
        this.whiteList = w;
        CompletableFuture.runAsync(() -> init(repository, whiteList, port));
    }

    private void init(UserRepository repository, List<String> whiteList, int port) {
        EventLoopGroup m = new NioEventLoopGroup();
        EventLoopGroup w = new NioEventLoopGroup(15);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(m, w)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("decoder", new StringDecoder());
                            socketChannel.pipeline().addLast("encoder", new StringEncoder());
                            socketChannel.pipeline().addLast(new RemoteHandler(repository, whiteList));
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind("localhost", port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            w.shutdownGracefully();
            m.shutdownGracefully();
        }
    }



    private static class RemoteHandler extends ChannelInboundHandlerAdapter {

        private UserRepository repository;
        private ObjectMapper mapper = new ObjectMapper();
        private List<String> whiteList = new ArrayList<>(0);

        public RemoteHandler(UserRepository repository, List<String> whiteList) {
            this.repository = repository;
            this.whiteList = whiteList;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("rbac server init..");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
            String m = new String(Base64.getDecoder().decode((String) message));
            Map<String, String> map = mapper.readValue(m, Map.class);
            if (whiteList.contains(map.get("service-id"))) {
                String username = map.get("username");
                String response = "{\"status\" : 200, user: " +
                            mapper.writeValueAsString(repository.findByUsername(username).orElse(new User()))
                        + "}";
                ctx.writeAndFlush(new String(Base64.getEncoder().encode(response.getBytes(StandardCharsets.UTF_8))));
                return;
            }
            String response = "{\"status\": 401, user: \"\"}";
            ctx.writeAndFlush(new String(Base64.getEncoder().encode(response.getBytes(StandardCharsets.UTF_8))));
        }
    }

}
