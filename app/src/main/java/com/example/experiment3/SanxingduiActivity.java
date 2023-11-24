package com.example.experiment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class SanxingduiActivity extends AppCompatActivity {

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mTitleContent;
        TextView mDate;
        ImageView mCover;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.textViewTitle);
            mTitleContent = itemView.findViewById(R.id.textViewContent);
            mDate=itemView.findViewById(R.id.textViewDate);
            mCover=itemView.findViewById(R.id.textViewCover);

            mCover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    createAlertDialog(mCover,mTitle.getText().toString(),mTitleContent.getText().toString(),mDate.getText().toString());
                }
            });
        }
    }

    public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
        @NonNull
        @Override
        public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(SanxingduiActivity.this, R.layout.layout_news, null);
            NewsViewHolder myViewHoder = new NewsViewHolder(view);
            return myViewHoder;
        }

        @Override
        public void onBindViewHolder(@NonNull  NewsViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.mTitle.setText(news.mTitle);
            holder.mTitleContent.setText(news.mContent);
            int year=news.mDate.getYear();
            int month=news.mDate.getMonth();
            int day=news.mDate.getDate();
            String sDate=String.format("%d-%02d-%02d",year,month,day);
            holder.mDate.setText(sDate);

            int imageResourceId = getResources().getIdentifier(news.CoverImageName, "mipmap", getPackageName());
            Log.d("SanxingduiActivity-CoverName",news.CoverImageName);
            Log.d("SanxingduiActivity-CoverImageResourceId",String.format("%d",imageResourceId));
            holder.mCover.setImageResource(imageResourceId);
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }
    public class News
    {
        public String mTitle,mContent,CoverImageName;
        public Date mDate;

        public News()
        {

        }

        public News(String mTitle, String mContent, String coverImageName, Date mDate) {
            this.mTitle = mTitle;
            this.mContent = mContent;
            this.CoverImageName = coverImageName;
            this.mDate = mDate;
        }
    }

    public ArrayList<News> mNewsList;
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;

    public ArrayList<News> loadNewsList(int ResourceID) throws XmlPullParserException, IOException {
        XmlPullParser parser = getResources().getXml(ResourceID);

        ArrayList<News> newsList = new ArrayList<>();
        String itemName = null;
        News currentNews = null;

        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                if (parser.getName().equals("item")) {
                    itemName = parser.getAttributeValue(null, "name");
                    currentNews = new News();
                } else if (parser.getName().equals("title")) {
                    currentNews.mTitle = parser.nextText();
                } else if (parser.getName().equals("cover")) {
                    currentNews.CoverImageName = parser.nextText();
                } else if (parser.getName().equals("content")) {
                    currentNews.mContent = parser.nextText();
                } else if (parser.getName().equals("date_info")) {
                    int year = 0, month = 0, day = 0;
                    while (parser.next() != XmlPullParser.END_TAG) {
                        if (parser.getEventType() == XmlPullParser.START_TAG) {
                            switch (parser.getName()) {
                                case "year":
                                    year = Integer.parseInt(parser.nextText());
                                    break;
                                case "month":
                                    month = Integer.parseInt(parser.nextText());
                                    break;
                                case "day":
                                    day = Integer.parseInt(parser.nextText());
                                    break;
                            }
                        }
                    }
                    Log.d("Date", String.format("%d-%d-%d",year,month,day ));
                    currentNews.mDate = new Date(year, month, day);
                }
            } else if (parser.getEventType() == XmlPullParser.END_TAG && parser.getName().equals("item")) {
                // Add the completed News object to the list when reaching the end of an "item"
                newsList.add(currentNews);
            }
            parser.next();
        }

        return newsList;
    }
    private void init()
    {
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanxingdui);
        init();
        try {
            mNewsList = loadNewsList(R.xml.sanxingdui);
            Log.d("SanxingduiActivity", "mNewList initialized");
        }catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
            Log.d("SanxingduiActivity", "XmlPullParserException, IOException: " + e.getMessage());
        }
        if (mNewsList != null && !mNewsList.isEmpty()) {
            loadNewsListView();
        }
    }

    protected void loadNewsListView() {
        mNewsAdapter = new NewsAdapter();
        mRecyclerView.setAdapter(mNewsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    protected void createAlertDialog
            (
                    ImageView Cover,
                    String Title,
                    String Content,
                    String Date
            )
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(Title);

        View NewsDetail=this.getLayoutInflater().inflate(R.layout.layout_news_detail,null);

        TextView vContent = NewsDetail.findViewById(R.id.textViewContent);
        TextView vDate=NewsDetail.findViewById(R.id.textViewDate);
        ImageView vCover=NewsDetail.findViewById(R.id.textViewCover);

        vContent.setText(Content);
        vDate.setText(Date);
        vCover=Cover;

        builder.setView(NewsDetail);
        AlertDialog alertDialog =builder.create();//这个方法可以返回一个alertDialog对象
        alertDialog.show();
    }
}
