package nanohttpd;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.SimpleWebServer;

/**
 * Created by i329817(Jianqing.Peng@sap.com) on 22/02/2017.
 */

public class NanoFileServer extends SimpleWebServer {
    public static NanoHTTPD startFileServerWithAppDataDir(Context context) {
        return start(context, context.getApplicationInfo().dataDir);
    }

    public static NanoHTTPD start(Context context, String root) {
        NanoHTTPD nanoServer = new NanoFileServer(NanoConstant.HOST, NanoConstant.PORT, new File(root), false);
        try {
            nanoServer.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nanoServer;
    }

    public NanoFileServer(String host, int port, File wwwroot, boolean quiet, String cors) {
        super(host, port, wwwroot, quiet, cors);
    }

    public NanoFileServer(String host, int port, File wwwroot, boolean quiet) {
        super(host, port, wwwroot, quiet);
    }

    public NanoFileServer(String host, int port, List<File> wwwroots, boolean quiet) {
        super(host, port, wwwroots, quiet);
    }

    public NanoFileServer(String host, int port, List<File> wwwroots, boolean quiet, String cors) {
        super(host, port, wwwroots, quiet, cors);
    }
}
