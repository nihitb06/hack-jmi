package team7.hack.com.hackjmiapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_home, container, false);

        itemView.findViewById(R.id.drive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WebActivity.class).putExtra("url", 0));
            }
        });
        itemView.findViewById(R.id.learn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WebActivity.class).putExtra("url", 1));
            }
        });
        itemView.findViewById(R.id.awareness).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WebActivity.class).putExtra("url", 2));
            }
        });
        itemView.findViewById(R.id.donate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WebActivity.class).putExtra("url", 3));
            }
        });
        itemView.findViewById(R.id.compare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PieActivity.class));
            }
        });

        return itemView;
    }

}
