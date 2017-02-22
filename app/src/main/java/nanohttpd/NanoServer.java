package nanohttpd;

import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by i329817(Jianqing.Peng@sap.com) on 22/02/2017.
 */

public class NanoServer extends NanoHTTPD {

    public NanoServer() throws IOException {
        super(NanoConstant.PORT);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to " + NanoConstant.LOCAL_URL);
    }

    public static NanoHTTPD startServer() {
        try {
            return new NanoServer();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }

        return null;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h1>Hello server</h1>\n";
        Map<String, String> parms = session.getParms();
        if (parms.get("username") == null) {
            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
        } else {
            msg += "<p>Hello, " + parms.get("username") + "!</p>";
        }
        return newFixedLengthResponse(msg + "</body></html>\n");
    }
}
