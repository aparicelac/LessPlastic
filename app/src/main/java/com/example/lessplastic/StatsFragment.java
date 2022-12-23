package com.example.lessplastic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RecyclerView recyclerCategory;
    List<Category> categories;

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void fillList(){
        categories.add(new Category("#003f5c", "Bolsas", "Mas de lo usual", "2g", "1"));
        categories.add(new Category("#444e86", "Botellas", "Mas de lo usual", "3g", "1"));
        categories.add(new Category("#955196", "Tecnopor", "Mas de lo usual", "8g", "1"));
        categories.add(new Category("#dd5182", "Empaques", "Mas de lo usual", "11g", "1"));
        categories.add(new Category("#ff6e54", "PVC", "Mas de lo usual", "21g", "1"));
        categories.add(new Category("#ffa600", "Envases", "Mas de lo usual", "23g", "1"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        categories = new ArrayList<>();
        recyclerCategory = view.findViewById(R.id.catRecyclerView);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(getContext()));

        fillList();

        CategoryAdapter adapter = new CategoryAdapter(categories);
        recyclerCategory.setAdapter(adapter);
        return view;
    }
}