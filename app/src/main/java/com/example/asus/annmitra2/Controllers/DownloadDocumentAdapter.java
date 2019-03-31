package com.example.asus.annmitra2.Controllers;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.annmitra2.Models.DownloadDocument;
import com.example.asus.annmitra2.R;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 9/16/2018.
 */

//donwload reference : - https://github.com/anugotta/DownloadManagerExample/blob/master/app/src/main/java/com/gadgetsaint/downloadmanagerexample/MainActivity.java
public class DownloadDocumentAdapter extends RecyclerView.Adapter<DownloadDocumentAdapter.ViewHolder>
{

    private Context context;
    private List<DownloadDocument> downloadDocuments;
    private DownloadManager downloadManager;
    private long refid;
    private Uri Download_Uri;
    ArrayList<Long> list = new ArrayList<>();

    public DownloadDocumentAdapter(Context context, List<DownloadDocument> downloadDocuments)
    {
        this.context = context;
        this.downloadDocuments = downloadDocuments;
    }

    @NonNull
    @Override
    public DownloadDocumentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.download_document_card, parent, false);
        DownloadDocumentAdapter.ViewHolder viewHolder = new DownloadDocumentAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DownloadDocumentAdapter.ViewHolder holder, int position)
    {
        DownloadDocument downloadDocument1 = downloadDocuments.get(position);
        holder.textView_subject.setText(downloadDocument1.getSubject());
        holder.textView_timeStamp.setText(downloadDocument1.getTimeStamp());
        holder.download.setTag(""+downloadDocument1.getUrl());

        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        context.registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        holder.download.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String urldown=holder.download.getTag()+"";
//                Download_Uri = Uri.parse("http://www.gadgetsaint.com/wp-content/uploads/2016/11/cropped-web_hi_res_512.png");
                Download_Uri = Uri.parse(urldown);
                list.clear();
                //using download manager class to download the resources
                DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE );
                request.setAllowedOverRoaming(false);
                request.setTitle(urldown.substring(urldown.lastIndexOf('/')+1, urldown.length()));
                request.setDescription("Downloading " + urldown.substring(urldown.lastIndexOf('/')+1, urldown.length()));
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,  urldown.substring(urldown.lastIndexOf('/')+1, urldown.length()));


                refid = downloadManager.enqueue(request);


                Log.e("OUT", "" + refid);

                list.add(refid);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return downloadDocuments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView_subject, textView_timeStamp;
        public Button download;

        public ViewHolder(View itemView)
        {
            super(itemView);
            textView_subject = itemView.findViewById(R.id.subject);
            textView_timeStamp = itemView.findViewById(R.id.timeStamp);
            download=itemView.findViewById(R.id.downloadButton);
        }

    }
    BroadcastReceiver onComplete = new BroadcastReceiver()
    {
        public void onReceive(Context ctxt, Intent intent)
        {
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            Log.e("IN", "" + referenceId);
            list.remove(referenceId);
            if (list.isEmpty())
            {
                Log.e("INSIDE", "" + referenceId);
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle(R.string.app_name+"")
                                .setContentText("All Download completed");
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(455, mBuilder.build());
                Toast.makeText(context,"Download completed",Toast.LENGTH_SHORT).show();
            }
        }
    };
//    class DownloadFileFromURL extends AsyncTask<String, String, String> {
//
//        /**
//         * Before starting background thread
//         * Show Progress Bar Dialog
//         * */
//        @Override
//        protected void onPreExecute()
//        {
//            super.onPreExecute();
//        }
//
//        /**
//         * Downloading file in background thread
//         * */
//        @Override
//        protected String doInBackground(String... f_url) {
//            int count;
//            try {
//                URL url = new URL(f_url[0]);
//                URLConnection connection = url.openConnection();
//                connection.connect();
//                // this will be useful so that you can show a tipical 0-100% progress bar
//                int lenghtOfFile = connection.getContentLength();
//
//                // download the file
//                InputStream input = new BufferedInputStream(url.openStream(), 8192);
//
//                // Output stream
//                OutputStream output = new FileOutputStream("/sdcard/Download/"+ f_url[0].substring(f_url[0].lastIndexOf('/')+1, f_url[0].length()));
//
//                byte data[] = new byte[1024];
//
//                long total = 0;
//
//                while ((count = input.read(data)) != -1) {
//                    total += count;
//                    // publishing the progress....
//                    // After this onProgressUpdate will be called
//                    publishProgress(""+(int)((total*100)/lenghtOfFile));
//
//                    // writing data to file
//                    output.write(data, 0, count);
//                }
//
//                // flushing output
//                output.flush();
//
//                // closing streams
//                output.close();
//                input.close();
//
//            } catch (Exception e) {
//                Log.e("Error: ", e.getMessage());
//            }
//
//            return null;
//        }
//
//        /**
//         * Updating progress bar
//         * */
//        protected void onProgressUpdate(String... progress) {
//            // setting progress percentage
//
//        }
//
//        /**
//         * After completing background task
//         * Dismiss the progress dialog
//         * **/
//        @Override
//        protected void onPostExecute(String file_url) {
//            // dismiss the dialog after the file was downloaded
//
//            // Displaying downloaded image into image view
//            // Reading image path from sdcard
//            String imagePath = Environment.getExternalStorageDirectory().toString() + "/downloadedfile";
//            // setting downloaded into image view
////            my_image.setImageDrawable(Drawable.createFromPath(imagePath));
//            Toast.makeText(context, "Downloaded see in downloads", Toast.LENGTH_SHORT).show();
//        }
//
//    }
}
