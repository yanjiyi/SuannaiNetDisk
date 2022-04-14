package com.suannai.netdisk.utils;

import com.suannai.netdisk.dao.AddUriOption;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Aria2Utils {
    public static String addUri(String jsonRpcURL,String[] uri, AddUriOption option) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_ADD_URI);
        aria2Command.addParam(uri).addParam(option);
        return aria2Command.send(jsonRpcURL);
    }

    public static String addTorrent(String jsonRpcURL,String whereTorrentFile) throws IOException {
        byte[] b = Files.readAllBytes(Paths.get(whereTorrentFile));
        String torrent = Base64.getEncoder().encodeToString(b);

        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_ADD_TORRENT);
        aria2Command.addParam(new String[]{torrent});
        return aria2Command.send(jsonRpcURL);
    }

    public static String addMetaLink(String jsonRpcURL,String whereMetaLinkFile) throws IOException {
        byte[] b = Files.readAllBytes(Paths.get(whereMetaLinkFile));
        String metalink = Base64.getEncoder().encodeToString(b);

        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_ADD_METALINK);
        aria2Command.addParam(new String[]{metalink});
        return aria2Command.send(jsonRpcURL);
    }

    public static String remove(String jsonRpcURL,String gid) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_REMOVE);
        aria2Command.addParam(new String[]{gid});
        return aria2Command.send(jsonRpcURL);
    }

    public static String forceRemove(String jsonRpcURL,String gid) throws IOException {
        Aria2Command aria2Command  = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_FORCEREMOVE);
        aria2Command.addParam(new String[]{gid});
        return aria2Command.send(jsonRpcURL);
    }

    public static String pause(String jsonRpcURL,String gid) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_PAUSE);
        aria2Command.addParam(new String[]{gid});
        return aria2Command.send(jsonRpcURL);
    }

    public static String pauseAll(String jsonRpcURL) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_PAUSEALL);
        return aria2Command.send(jsonRpcURL);
    }

    public static String forcePause(String jsonRpcURL,String gid) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_FORCEPAUSE);
        aria2Command.addParam(new String[]{gid});
        return aria2Command.send(jsonRpcURL);
    }

    public static String forcePauseAll(String jsonRpcURL) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_FORCEPAUSEALL);
        return aria2Command.send(jsonRpcURL);
    }

    public static String unpause(String jsonRpcURL,String gid) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_UNPAUSE);
        aria2Command.addParam(new String[]{gid});
        return aria2Command.send(jsonRpcURL);
    }

    public static String unpauseAll(String jsonRpcURL) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_UNPAUSEALL);
        return aria2Command.send(jsonRpcURL);
    }


}
