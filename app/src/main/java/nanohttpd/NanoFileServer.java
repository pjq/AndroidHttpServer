package nanohttpd;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.List;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.SimpleWebServer;

/**
 * Created by i329817(Jianqing.Peng@sap.com) on 22/02/2017.
 */

public class NanoFileServer extends SimpleWebServer {
    public static NanoHTTPD startFileServerWithAppDataDir(Context context) {
        return startHttps(context, context.getApplicationInfo().dataDir);
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


    public static void copy(Context _context, String assertFile, String to) throws IOException {
        // Open your local db as the input stream
        InputStream myInput = _context.getAssets().open(assertFile);

        // Path to the just created empty db
        String outFileName = to;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    public static NanoHTTPD startHttps(Context context, String root) {
        String keystorePath = context.getApplicationInfo().dataDir + java.io.File.separator + "keystore.jks";
        try {
            copy(context, "keystore.jks", keystorePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        NanoFileServer nanoServer = new NanoFileServer(NanoConstant.HOST, NanoConstant.PORT, new File(root), false);
        try {
            nanoServer.makeSecure(NanoHTTPD.makeSSLSocketFactory(keystorePath, "123456".toCharArray()), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
