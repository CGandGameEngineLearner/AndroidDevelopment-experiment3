package com.example.experiment3;

import androidx.annotation.NonNull;
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

        public News(String mTitle, String mContent, String coverImageName, Date mDate) {
            this.mTitle = mTitle;
            this.mContent = mContent;
            CoverImageName = coverImageName;
            this.mDate = mDate;
        }
    }

    public ArrayList<News> mNewsList;
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;

    public ArrayList<News> loadNewsList(int ResourceID) throws XmlPullParserException, IOException {

        XmlPullParser parser = getResources().getXml(ResourceID);


        // 解析 xml 文档
        ArrayList<News> newsList = new ArrayList<>();
        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                if (parser.getName().equals("item")) {
                    // 获取新闻信息
                    String title = parser.getAttributeValue(0);
                    String cover = parser.getAttributeValue(1);
                    String content = parser.nextText();

                    // 解析日期信息
                    int year = Integer.parseInt(parser.getAttributeValue(2));
                    int month = Integer.parseInt(parser.getAttributeValue(3));
                    int day = Integer.parseInt(parser.getAttributeValue(4));
                    Date date = new Date(year - 1900, month - 1, day);

                    // 创建新闻对象
                    News news = new News(title, content,cover, date);

                    // 添加新闻对象到列表
                    newsList.add(news);
                }
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
            Log.d("SanxingduiActivity","mNewList initialized");
        }
        catch (Exception e)
        {
            Log.d("SanxingduiActivity","XmlPullParserException, IOException");
        }
        if(mNewsList!=null&&!mNewsList.isEmpty()) {
            loadNewsListView();
        }
    }

    protected void loadNewsListView()
    {
        mNewsAdapter = new NewsAdapter();
        mRecyclerView.setAdapter(mNewsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}
