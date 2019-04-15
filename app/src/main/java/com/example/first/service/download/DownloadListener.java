package com.example.first.service.download;

/**
 * @ClassName: DownloadListener
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/10 16:50
 * @Version: 1.0
 */
public interface DownloadListener {

    void onProgress(int progress);//通知当前下载进度
    void onSuccess();//通知下载成功事件
    void onFailed();//通知下载失败事件
    void onPaused();//通知下载暂停事件
    void onCanceled();//通知下载取消事件
}
