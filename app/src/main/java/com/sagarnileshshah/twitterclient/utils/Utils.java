package com.sagarnileshshah.twitterclient.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.twitterclient.R;
import com.malmstein.fenster.controller.MediaFensterPlayerController;
import com.malmstein.fenster.controller.SimpleMediaFensterPlayerController;
import com.malmstein.fenster.view.FensterVideoView;
import com.sagarnileshshah.twitterclient.models.Medium______;
import com.sagarnileshshah.twitterclient.models.Tweet;
import com.sagarnileshshah.twitterclient.models.Url_____;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sshah on 2/18/16.
 */
public class Utils {


    public static String getFormattedRelativeTimestamp(Date date) {
        DateTime historicDateTime = new DateTime(date);
        DateTime now = new DateTime();

        Interval interval = new Interval(historicDateTime, now);

        Period period = interval.toPeriod();

        String elapsed;

        if (period.getYears() > 0) {
            elapsed = String.valueOf(period.getYears()) + "Y";
        } else if (period.getMonths() > 0) {
            elapsed = String.valueOf(period.getMonths()) + "M";
        } else if (period.getWeeks() > 0) {
            elapsed = String.valueOf(period.getWeeks()) + "W";
        } else if (period.getDays() > 0) {
            elapsed = String.valueOf(period.getDays()) + "d";
        } else if (period.getMinutes() > 0) {
            elapsed = String.valueOf(period.getMinutes()) + "m";
        } else {
            elapsed = String.valueOf(period.getSeconds()) + "s";
        }

        return elapsed;
    }

    public static void unwrapAndRenderTweetTextLinks(final Context context, final Tweet tweet, ImageView mediaImage, final FensterVideoView fensterVideoView, final SimpleMediaFensterPlayerController mediaFensterPlayerController, final ImageView iconImage, TextView textView) {
        String text = tweet.getText();
        mediaImage.setImageResource(0);


        if (tweet.getEntities() != null && tweet.getEntities().getUrls() != null && tweet.getEntities().getUrls().size() > 0) {
            for (Url_____ url_____ : tweet.getEntities().getUrls()) {
                String wrapperUrl = url_____.getUrl();
                String displayUrl = url_____.getDisplayUrl();
                text = text.replace(wrapperUrl, displayUrl);
            }
        }

        if (tweet.getExtendedEntities() != null && tweet.getExtendedEntities().getMedia() != null && tweet.getExtendedEntities().getMedia().size() > 0) {
            Medium______ medium______ = tweet.getExtendedEntities().getMedia().get(0);
            String type = medium______.getType();
            String wrapperUrl = medium______.getUrl();
            text = text.replace(wrapperUrl, "");
            String media_url = medium______.getMediaUrl() + ":medium";
            Glide.with(context).load(media_url).error(R.drawable.photo_placeholder).dontAnimate().into(mediaImage);
            mediaImage.setVisibility(View.VISIBLE);

            iconImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fensterVideoView.setVisibility(View.VISIBLE);
                    fensterVideoView.start();
                    //fensterVideoView.setMediaController(mediaFensterPlayerController);
                    //mediaFensterPlayerController.setVisibility(View.VISIBLE);
                    iconImage.setVisibility(View.GONE);
                }
            });

            fensterVideoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fensterVideoView != null) {
                        if (fensterVideoView.isPlaying()) {
                            fensterVideoView.pause();
                            iconImage.setVisibility(View.VISIBLE);
                        } else {
                            iconImage.setVisibility(View.GONE);
                            fensterVideoView.resume();
                        }
                    }
                }
            });

            fensterVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    iconImage.setVisibility(View.VISIBLE);
                    fensterVideoView.setVisibility(View.GONE);
                }
            });

            if (type.equals("video") || type.equals("animated_gif")) {
                iconImage.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams mediaImageLayoutParams = mediaImage.getLayoutParams();
                fensterVideoView.setLayoutParams(mediaImageLayoutParams);
                String videoUrl = tweet.getExtendedEntities().getMedia().get(0).getVideoInfo().getVariants().get(0).getUrl();

                fensterVideoView.setVideo(videoUrl, MediaFensterPlayerController.DEFAULT_VIDEO_START);

            } else {
                iconImage.setVisibility(View.GONE);
                fensterVideoView.setVisibility(View.GONE);
            }
        } else {
            mediaImage.setVisibility(View.GONE);
            fensterVideoView.setVisibility(View.GONE);
            iconImage.setVisibility(View.GONE);
        }

        SpannableString spannableString = formatTweetText(context, text);
        textView.setText(spannableString);
    }

    public static SpannableString formatTweetText(Context context, String text) {
        SpannableString captionSpannableString = new SpannableString(text);
        Pattern pattern = Pattern.compile("[#|@].+?\\b");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ForegroundColorSpan tagMentionColorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary));
            captionSpannableString.setSpan(tagMentionColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return captionSpannableString;
    }


    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean value = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        if (!value) {
            renderSnackBar(activity, "No network connection. Please check network settings and activate either Wifi or Data.");
        }
        return value;
    }

    public static boolean isOnline(Activity activity) {
        Runtime runtime = Runtime.getRuntime();
        Boolean value = false;
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            value = (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!value) {
            renderSnackBar(activity, "Current network not connected to the internet. Please try again after some time or contact network operator.");
        }
        return value;
    }

    private static void renderSnackBar(Activity activity, String msg) {

        final Snackbar snackBar = Snackbar.make(activity.getWindow().findViewById(android.R.id.content), msg, Snackbar.LENGTH_INDEFINITE);

        snackBar.setAction("Dismiss", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                snackBar.dismiss();

            }
        });
        snackBar.setActionTextColor(Color.WHITE).show();
    }

}
