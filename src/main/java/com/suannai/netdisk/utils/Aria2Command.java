package com.suannai.netdisk.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Aria2Command {
    public final String METHOD_ADD_URI = "aria2.addUri";
    public final String METHOD_ADD_TORRENT = "aria2.addTorrent";
    public final String METHOD_ADD_METALINK = "aria2.addMetaLink";
    public final String METHOD_REMOVE = "aria2.remove";
    public final String METHOD_FORCEREMOVE = "aria2.forceRemove";
    public final String METHOD_PAUSE = "aria2.pause";
    public final String METHOD_PAUSEALL = "aria2.pauseAll";
    public final String METHOD_FORCEPAUSE = "aria2.forcePause";
    public final String METHOD_FORCEPAUSEALL = "aria2.forcePauseAll";
    public final String METHOD_UNPAUSE = "aria2.unpause";
    public final String METHOD_UNPAUSEALL = "aria2.unpauseAll";
    public final String METHOD_TELLSTATUS = "aria2.tellStatus";
    public final String METHOD_GETURIS = "aria2.getUris";
    public final String METHOD_GETFILES = "aria2.getFiles";
    public final String METHOD_GETPEERS = "aria2.getPeers";
    public final String METHOD_GETSERVERS = "aria2.getServers";
    public final String METHOD_TELLACTIVE = "aria2.tellActive";
    public final String METHOD_TELLWAITING = "aria2.tellWaiting";
    public final String METHOD_TELLSTOPPED = "ari2a.tellStopped";
    public final String METHOD_CHANGEPOSITION = "aria2.changePosition";
    public final String METHOD_CHANGEURI = "aria2.changeUri";
    public final String METHOD_GETOPTION = "aria2.getOption";
    public final String METHOD_CHANGEOPTION = "aria2.changeOption";
    public final String METHOD_GETGLOBALOPTION = "aria2.getGlobalOption";
    public final String METHOD_CHANGEGLOBALOPTION = "aria2.changeGlobalOption";
    public final String METHOD_GETGLOBALSTAT = "aria2.getGlobalStat";
    public final String METHOD_PURGEDOWNLOADRESULT = "aria2.purgeDownloadResult";
    public final String METHOD_REMOVEDOWNLOADRESULT = "aria2.removeDownloadResult";
    public final String METHOD_GETVERSION = "aria2.getVersion";
    public final String METHOD_GETSESSIONINFO = "aria2.getSessionInfo";
    public final String METHOD_SHUTDOWN = "aria2.shutdown";
    public final String METHOD_FORCESHUTDOWN = "aria2.forceShutdown";
    public final String METHOD_SAVESESSION = "aria2.saveSession";
    public final String METHOD_MULTICALL = "system.multicall";
    public final String METHOD_LISTMETHODS = "system.listMethods";
    public final String METHOD_LISTNOTIFICATIONS = "system.listNotifications";

    private String id = UUID.randomUUID().toString();
    private String jsonrpc = "2.0";
    private String method = METHOD_ADD_URI;
    private String url;
    private List<Object> params = new ArrayList<>();

    public Aria2Command addParam(Object obj)
    {
        params.add(obj);
        return this;
    }

    public String send(String jsonRpcUrl) throws IOException {
        jsonRpcUrl = StringUtils.isEmpty(jsonRpcUrl) ? "http://localhost:6800" : jsonRpcUrl;
        HttpPost httpPost = new HttpPost(jsonRpcUrl);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        httpPost.setEntity(new StringEntity(JSON.toJSONString(this), StandardCharsets.UTF_8));

        CloseableHttpResponse response;
        response = HttpClients.createDefault().execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();

        String result = null;
        result = EntityUtils.toString(entity,StandardCharsets.UTF_8);
        if(statusCode == HttpStatus.SC_OK)
        {
            EntityUtils.consume(entity);
            return result;
        }

        System.out.println("statusCode = " + statusCode);
        System.out.println("result = " + result);

        return null;

    }
 }
