package com.example.news_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private RecyclerView recyclerViewTopStories;
    private RecyclerView recyclerViewNews;
    private TopStoriesAdapter topStoriesAdapter;
    private TopStoriesAdapter newsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerViewTopStories = view.findViewById(R.id.recycler_view_top_stories);
        recyclerViewNews = view.findViewById(R.id.recycler_view_news);

        recyclerViewTopStories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<NewsItem> topStories = getTopStories();
        List<NewsItem> news = getNews();

        topStoriesAdapter = new TopStoriesAdapter(topStories, this::openNewsDetail);
        newsAdapter = new TopStoriesAdapter(news, this::openNewsDetail);

        recyclerViewTopStories.setAdapter(topStoriesAdapter);
        recyclerViewNews.setAdapter(newsAdapter);

        return view;
    }

    private void openNewsDetail(NewsItem item) {
        NewsDetailFragment fragment = NewsDetailFragment.newInstance(item);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private List<NewsItem> getTopStories() {
        List<NewsItem> topStories = new ArrayList<>();
        topStories.add(new NewsItem("Major Breakthrough in Quantum Computing", "Scientists at MIT have achieved a significant breakthrough in quantum computing by successfully implementing a 256-qubit system. This advancement is expected to drastically improve the speed and security of data processing in fields such as cryptography and complex simulations.", "https://via.placeholder.com/150"));
        topStories.add(new NewsItem("New Species of Dinosaur Discovered", "A team of paleontologists in Argentina has discovered a new species of dinosaur, which they have named 'Titanosaurus Magnificus'. The fossils, which are estimated to be 70 million years old, provide new insights into the diversity of life during the Late Cretaceous period.", "https://via.placeholder.com/150"));
        topStories.add(new NewsItem("Advancements in Renewable Energy", "Innovations in solar and wind energy technologies are driving down costs and increasing efficiency. A new generation of solar panels and wind turbines is making it possible to generate more power with fewer resources, accelerating the shift towards sustainable energy solutions.", "https://via.placeholder.com/150"));
        return topStories;
    }

    private List<NewsItem> getNews() {
        List<NewsItem> news = new ArrayList<>();
        news.add(new NewsItem("Tech Giants Release New Gadgets", "Leading technology companies have unveiled their latest products at the annual TechExpo. Highlights include next-generation smartphones with foldable screens, advanced AI-powered smart home devices, and innovative wearable tech designed to improve health and fitness.", "https://via.placeholder.com/150"));
        news.add(new NewsItem("Global Markets See Record Growth", "Global stock markets have experienced record growth this quarter, driven by robust economic data and increased investor confidence. Key sectors contributing to this surge include technology, renewable energy, and healthcare.", "https://via.placeholder.com/150"));
        news.add(new NewsItem("Breakthrough in Medical Research", "Researchers at Johns Hopkins University have developed a new gene-editing technique that shows promise in treating a variety of genetic disorders. The technique, known as CRISPR-2, allows for more precise and less invasive modifications to the human genome.", "https://via.placeholder.com/150"));
        news.add(new NewsItem("Climate Change Initiatives Gain Momentum", "Governments and organizations worldwide are ramping up efforts to combat climate change with ambitious new policies and initiatives. Recent commitments include transitioning to 100% renewable energy by 2040 and implementing strict regulations on carbon emissions.", "https://via.placeholder.com/150"));
        return news;
    }
}
