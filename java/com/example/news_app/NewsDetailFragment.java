package com.example.news_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class NewsDetailFragment extends Fragment {
    private static final String ARG_NEWS_ITEM = "news_item";
    private NewsItem newsItem;
    private RecyclerView recyclerViewRelatedNews;
    private TopStoriesAdapter relatedNewsAdapter;

    public static NewsDetailFragment newInstance(NewsItem item) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        if (getArguments() != null) {
            newsItem = (NewsItem) getArguments().getSerializable(ARG_NEWS_ITEM);
        }

        ImageView newsImage = view.findViewById(R.id.news_image);
        TextView newsDescription = view.findViewById(R.id.news_description);
        recyclerViewRelatedNews = view.findViewById(R.id.recycler_view_related_news);

        Glide.with(this).load(newsItem.getImageUrl()).into(newsImage);
        newsDescription.setText(newsItem.getDescription());

        recyclerViewRelatedNews.setLayoutManager(new LinearLayoutManager(getContext()));
        List<NewsItem> relatedNews = getRelatedNews();

        relatedNewsAdapter = new TopStoriesAdapter(relatedNews, this::openRelatedNewsDetail);
        recyclerViewRelatedNews.setAdapter(relatedNewsAdapter);

        return view;
    }

    private List<NewsItem> getRelatedNews() {
        List<NewsItem> relatedNews = new ArrayList<>();
        relatedNews.add(new NewsItem("New Insights into Quantum Computing", "Researchers at Stanford University have provided new insights into the practical applications of quantum computing. Their recent study explores the potential of quantum algorithms in solving complex problems faster than classical computers.", "https://via.placeholder.com/150"));
        relatedNews.add(new NewsItem("Paleontology Advances", "Advancements in paleontological techniques are helping scientists uncover and classify more dinosaur species. Recent discoveries in China and Africa are shedding light on the evolutionary history of these ancient creatures.", "https://via.placeholder.com/150"));
        relatedNews.add(new NewsItem("Renewable Energy Milestones", "Recent milestones in renewable energy are set to significantly reduce global carbon emissions. Innovations in energy storage and grid management are making it easier to integrate renewable sources into existing power systems.", "https://via.placeholder.com/150"));
        return relatedNews;
    }

    private void openRelatedNewsDetail(NewsItem item) {
        NewsDetailFragment fragment = NewsDetailFragment.newInstance(item);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
