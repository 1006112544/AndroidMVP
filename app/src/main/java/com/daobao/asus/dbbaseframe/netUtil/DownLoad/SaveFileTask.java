package com.daobao.asus.dbbaseframe.netUtil.DownLoad;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.daobao.asus.dbbaseframe.netUtil.CallBack.IDownloadCallBack;
import com.daobao.asus.dbbaseframe.netUtil.CallBack.IRequest;
import com.daobao.asus.dbbaseframe.netUtil.CallBack.ISuccess;
import com.daobao.asus.dbbaseframe.util.FileUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;

/**
 * Created by db on 2017/10/30.
 */

public class SaveFileTask extends AsyncTask<Object,Void,File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private Context context;
    private long DownloadLenth=0;
    private IDownloadCallBack DownLoadCallBack;
    public SaveFileTask(IRequest request, ISuccess success, Context context, IDownloadCallBack DownLoadCallBack) {
        REQUEST = request;
        SUCCESS = success;
        this.context = context;
        this.DownLoadCallBack = DownLoadCallBack;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody)params[2];
        final String name = (String) params[3];
        //文件总长度
        final long total = (long) params[4];
        final InputStream is = body.byteStream();
        if(downloadDir==null||downloadDir.equals(""))
        {
            downloadDir = "download_loads";
        }
        if(extension==null||extension.equals(""))
        {
            extension="";//先不做处理 以后需要时使用
        }
        if(name==null||name.equals(""))
        {
            Date date = new Date();
            String prefix =
                    new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.CHINA).format(date);
            return writeToDisk(is,downloadDir,prefix,extension,total);
        }
        else return writeToDisk(is,downloadDir,name,extension,total);
    }
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        //加载完毕回到主线程的方法
        super.onPreExecute();
        if(SUCCESS!=null)
        {
            SUCCESS.onSuccess(file.getPath());
        }
        if(REQUEST!=null)
        {
            REQUEST.onRequsetEnd();
        }
        //如果是apk文件直接安装
        autoInstallApk(file);
    }
    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            context.startActivity(install);
        }
    }

    /**
     * 文件写入
     * @param is 输入流
     * @param dir 文件夹
     * @param prefix 为文件名加上时间
     * @param extension 扩展名
     * @return
     */
    public File writeToDisk(InputStream is, String dir, String prefix, String extension, long total) {
        final File file = FileUtil.createFileByTime(dir, prefix, extension);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        int limit = 0;//读取次数
        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024 * 4];

            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
                DownloadLenth += count;
                int progress = (int) (DownloadLenth * 1.0f / total * 100);
                if(limit%10 == 0&&progress<=100) {
                    //没读取10次修改一次progress 减小刷新频率
                    limit=0;
                    //修改进度
                    if(DownLoadCallBack!=null){
                        DownLoadCallBack.onDownloading(progress);
                    }
                }
                limit++;
            }

            bos.flush();
            fos.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
