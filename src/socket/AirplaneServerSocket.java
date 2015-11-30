package socket;


import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.example.gettingstarted.timeserver.TimeServerHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class AirplaneServerSocket {

    private static int SERVER_PORT = 9123;
    public static void main(String[] args) throws IOException {
        IoAcceptor ioAcceptor = new NioSocketAcceptor();
        ioAcceptor.getFilterChain().addLast("logger",new LoggingFilter());
        ioAcceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        ioAcceptor.setHandler(new TimeServerHandler());
        ioAcceptor.getSessionConfig().setReadBufferSize(2048);
        ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);

        ioAcceptor.bind(new InetSocketAddress(SERVER_PORT));

    }
}
