package com.technohub.melifeapp.classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HtmlImgConverter  extends AsyncTask<String, Void, Drawable> {
    TextView taskTextView;
    String taskHtmlString;
    Context con;

    public HtmlImgConverter(TextView v, String s, Context c) {
        taskTextView = v;
        taskHtmlString = s;
        con=c;
    }

    @Override
    protected Drawable doInBackground(String... params) {
        Drawable drawable = null;
        URL sourceURL;
        try {
            sourceURL = new URL(params[0]);
            URLConnection urlConnection = sourceURL.openConnection();
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(
                    inputStream);
            Bitmap bm = BitmapFactory.decodeStream(bufferedInputStream);

            // convert Bitmap to Drawable
            drawable = new BitmapDrawable(con.getResources(), bm);

            drawable.setBounds(0, 0, bm.getWidth(), bm.getHeight());

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return drawable;
    }

    @Override
    protected void onPostExecute(Drawable result) {

        final Drawable taskDrawable = result;

        if (taskDrawable != null) {
            taskTextView.setText(Html.fromHtml(taskHtmlString,
                    source -> taskDrawable, null));
        }

    }
}
