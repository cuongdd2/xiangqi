package main;/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler implementation for the echo server.
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private boolean hasPlayer = false;
    private List<Integer> playerId;
    private int currentId = -1;
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object rawMsg) {
        
        String msg = (String)rawMsg;
        System.out.println("src.main.Server <---- " + msg);
        if (msg != null) {
            String[] arr = msg.split(":");
            int id = Integer.parseInt(arr[1]);
            switch (arr[0]) {
                case "join":
                    if (hasPlayer) {
                        ctx.write("join:0");
                        playerId.add(id);
                        ctx.write("start:" + currentId);
                    } else {
                        hasPlayer = true;
                        playerId = new ArrayList<>();
                        playerId.add(id);
                        currentId = id;
                        ctx.write("join:1");
                    }
                    break;
                default:
                    if (currentId == id) {
                        int idx = playerId.indexOf(id);
                        currentId = idx == 0 ? playerId.get(1) : playerId.get(0);
                        ctx.write("move:" + arr[2] + ":" + arr[3]);
                    }
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
