package com.demo.service.remote;

import com.demo.entity.User;
import com.demo.exception.AuthException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class RemoteConnect {

    private LoadBalancerClient lc;

    public RemoteConnect(LoadBalancerClient lc) {
        this.lc = lc;
    }

    Optional<User> connect(String ip, int port, String message) {
        EventLoopGroup w = new NioEventLoopGroup();
        RemoteHandler handler = new RemoteHandler(message);
        try {
            ChannelFuture channelFuture = new Bootstrap()
                    .group(w)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                               socketChannel.pipeline().addLast("decoder", new StringDecoder());
                               socketChannel.pipeline().addLast("encoder", new StringDecoder());
                               socketChannel.pipeline().addLast(handler);
                        }
                    })
                    .connect(ip, port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            w.shutdownGracefully();
        }
        return handler.getResponse();
    }

    Address choose(String address) {
        if (Objects.isNull(address)) {
            throw new AuthException("未指定远程的service地址");
        }
        String[] ad = address.split(":");
        if (ad.length != 2) {
            throw new AuthException("地址不符合规范，请按照ip:port指定");
        }
        String ip = ad[0].matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}") ? ad[0] : lc.choose(ad[0]).getHost();
        return new Address(ip, Integer.parseInt(ad[1]));
    }

    @Data
    @AllArgsConstructor
    static class Address {
        private String ip;
        private int port;
    }

    private static class RemoteHandler extends ChannelInboundHandlerAdapter {

        private ObjectMapper mapper = new ObjectMapper();
        private User user;
        private String message;

        private RemoteHandler(String message) {
            this.message = message;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("connect remote server..");
            ctx.writeAndFlush(message);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
            String m = new String(Base64.getDecoder().decode((String) message));
            Response response = mapper.readValue(m, Response.class);
            if (!response.getStatus().equals(200)) {
                return;
            }
            user = response.getUser();
            ctx.close();
        }

        public Optional<User> getResponse() {
            return Optional.ofNullable(user);
        }
    }

}
