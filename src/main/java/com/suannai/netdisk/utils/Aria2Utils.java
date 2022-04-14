package com.suannai.netdisk.utils;

import com.suannai.netdisk.dao.AddUriOption;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;

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

    public static String addTorrent(String jsonRpcURL,String whereTorrentFile,AddUriOption option) throws IOException {
        byte[] b = Files.readAllBytes(Paths.get(whereTorrentFile));
        String torrent = Base64.getEncoder().encodeToString(b);

        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_ADD_TORRENT);
        aria2Command.addParam(new String[]{torrent}).addParam(option);
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

    public static String tellStatus(String jsonRpcURL,String gid) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_TELLSTATUS);
        aria2Command.addParam(gid);
        return aria2Command.send(jsonRpcURL);
    }

    public static String tellStatus(String jsonRpcURL,String gid,String[] whatKeyUWant) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_TELLSTATUS);
        aria2Command.addParam(gid).addParam(whatKeyUWant);
        return aria2Command.send(jsonRpcURL);
    }

    public static String getUris(String jsonRpcURL,String gid) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_GETURIS);
        aria2Command.addParam(gid);
        return aria2Command.send(jsonRpcURL);
    }

    public static String getFiles(String jsonRpcURL,String gid) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_GETFILES);
        aria2Command.addParam(gid);
        return aria2Command.send(jsonRpcURL);
    }

    public static String getPeers(String jsonRpcURL,String gid) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_GETPEERS);
        aria2Command.addParam(gid);
        return aria2Command.send(jsonRpcURL);
    }

    public static String getServers(String jsonRpcURL,String gid) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_GETSERVERS);
        aria2Command.addParam(gid);
        return aria2Command.send(jsonRpcURL);
    }

    public static String tellActive(String jsonRpcURL) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_TELLACTIVE);
        return aria2Command.send(jsonRpcURL);
    }

    public static String tellActive(String jsonRpcURL,String[] whatKeyUWant) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_TELLACTIVE);
        aria2Command.addParam(whatKeyUWant);
        return aria2Command.send(jsonRpcURL);
    }

    public static String tellWatiting(String jsonRpcURL,int offset,int num) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_TELLWAITING);
        aria2Command.addParam(offset).addParam(num);
        return aria2Command.send(jsonRpcURL);
    }

    public static String tellWatiting(String jsonRpcURL,int offset,int num,String[] whatKeyUWant) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_TELLWAITING);
        aria2Command.addParam(offset).addParam(num).addParam(whatKeyUWant);
        return aria2Command.send(jsonRpcURL);
    }

    public static String tellStopped(String jsonRpcURL,int offset,int num) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_TELLSTOPPED);
        aria2Command.addParam(offset).addParam(num);
        return aria2Command.send(jsonRpcURL);
    }

    public static String tellStopped(String jsonRpcURL,int offset,int num,String[] whatKeyUWant) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_TELLSTOPPED);
        aria2Command.addParam(offset).addParam(num).addParam(whatKeyUWant);
        return aria2Command.send(jsonRpcURL);
    }

    public static String changePosition(String jsonRpcURL,String gid,int pos,String how) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_CHANGEPOSITION);
        aria2Command.addParam(gid).addParam(pos).addParam(how);
        return aria2Command.send(jsonRpcURL);
    }

    public static String changeUri(String jsonRpcURL,String gid,int fileindex,String[] delUris,String[] addUris) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_CHANGEURI);
        aria2Command.addParam(gid).addParam(fileindex).addParam(delUris).addParam(addUris);
        return aria2Command.send(jsonRpcURL);
    }

    public static String changeUri(String jsonRpcURL,String gid,int fileindex,String[] delUris,String[] addUris,int position) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_CHANGEURI);
        aria2Command.addParam(gid).addParam(fileindex).addParam(delUris).addParam(addUris).addParam(position);
        return aria2Command.send(jsonRpcURL);
    }

    public static String getOption(String jsonRpcURL,String gid) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_GETOPTION);
        aria2Command.addParam(gid);
        return aria2Command.send(jsonRpcURL);
    }

    public static String getGlobalStat(String jsonRpcURL) throws IOException {
        Aria2Command aria2Command = new Aria2Command();
        aria2Command.setMethod(aria2Command.METHOD_GETGLOBALSTAT);
        return aria2Command.send(jsonRpcURL);
    }

}
