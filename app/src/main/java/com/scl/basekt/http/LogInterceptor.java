package com.scl.basekt.http;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.scl.basekt.utils.JsonUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by admin on 2021/6/7.
 * name:
 */
public class LogInterceptor implements Interceptor {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        try {
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.getBuffer();
            String body = buffer.clone().readString(StandardCharsets.UTF_8);
            StringBuilder builder = new StringBuilder();
            builder.append(" ");
            builder.append("\n");
            builder.append("----------------------------发送请求----------------------------");
            builder.append("\n");
            builder.append(request.url());
//        builder.append("\n");
//        builder.append(chain.connection());
//        builder.append("\n");
//        builder.append("请求头：");
//        builder.append("\n");
//        builder.append(request.headers());
            builder.append("\n");
            builder.append("请求参数：");
            builder.append("\n");

            Buffer buf = new Buffer();
            try {
                request.body().writeTo(buf);
                MediaType contentType = request.body().contentType();
                if (contentType != null) {
                    String requestString = buf.readString(contentType.charset(StandardCharsets.UTF_8));
                    builder.append(requestString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //builder.append(request.body().toString());
            builder.append("\n");
            builder.append("返回数据：");
            builder.append("\n");
            builder.append(body);
            builder.append("\n");
            builder.append("----------------------------完成----------------------------");
            builder.append("\n");
            builder.append(" ");
            String msg = builder.toString();
            if (msg.length() <= 3000 * 1000) {
                showLogCompletion(JsonUtil.formatJson(msg));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private synchronized void showLogCompletion(String msg) {
        if (msg == null || msg.length() == 0) {
            return;
        }
        int segmentSize = 3 * 1024;
        long length = msg.length();
        // 打印剩余日志
        if (length > segmentSize) {
            // 长度小于等于限制直接打印
            while (msg.length() > segmentSize) {
                // 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.i("http_log", " " + "\n" + logContent);
            }
            Log.i("http_log", " " + "\n" + msg);
        } else {
            Log.i("http_log", msg);
        }

    }
}
