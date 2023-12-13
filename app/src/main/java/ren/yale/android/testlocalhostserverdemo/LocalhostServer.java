package ren.yale.android.testlocalhostserverdemo;
import fi.iki.elonen.NanoHTTPD;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class LocalhostServer extends NanoHTTPD {

    public LocalhostServer() {
        super(8080); // 指定监听的端口
    }

    @Override
    public Response serve(IHTTPSession session) {
        System.out.println("qian===---===:"+session.getUri());
       // String url = session.getUri(); // 获取请求的 URL
      //  String url = "http://10.23.5.203:18811/v2/contents/5016a24e-093f-4b65-84bb-e1a7d7ffb2f4#/";
        String url = "https://www.baidu.com";
      
        String mServerIp = "https://www.ranwena.com";
       String targetUri = mServerIp + session.getUri();
        System.out.println("qian===---===:"+targetUri);

        if (session.getQueryParameterString() != null && session.getQueryParameterString().length() > 0) {
            targetUri = targetUri + "?" + session.getQueryParameterString();
        }
        // 转发请求到目标服务器并获取响应
        System.out.println("===---===:"+targetUri);
        String response = forwardRequest(targetUri);

        // 构建响应对象
        return newFixedLengthResponse(response);
    }

    private String forwardRequest(String url) {
        // 使用第三方库（如 OkHttp）来发起请求并获取响应
        // 这里只是一个示例，您可以根据实际需求选择合适的库和方式
        // 以下是使用 OkHttp 的示例代码：
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            okhttp3.Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

/*    public static void main(String[] args) throws IOException {
        LocalhostServer server = new LocalhostServer();
        server.start();


    }*/


  /*  private String forwardRequest(String url) {
        // 使用第三方库（如 OkHttp）来发起请求并获取响应
        // 这里只是一个示例，您可以根据实际需求选择合适的库和方式
        // 以下是使用 OkHttp 的示例代码：
        OkHttpClient client = new OkHttpClient();

        // 修改目标服务器的 URL
        String targetUrl = "http://your-remote-server.com" + url;

        Request request = new Request.Builder()
                .url(targetUrl)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }*/

}
