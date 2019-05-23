package com.zhou.netlan.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @program: netlen
 * @description:
 * @author: zhouchunji
 * @create: 2019-05-22 14:56
 **/
public interface TCPProtocol{
    //accept I/O形式
    void handleAccept(SelectionKey key) throws IOException;
    //read I/O形式
    void handleRead(SelectionKey key) throws IOException;
    //write I/O形式
    void handleWrite(SelectionKey key) throws IOException;
}