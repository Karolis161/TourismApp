package com.example.turizmoprograma.recommendations;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.turizmoprograma.R;

public class RecommendationsFragment extends Fragment {

    private final String[] tags = {"Pasirinkite dominančią sritį", "Visi", "Istorija", "Parkai", "Religija", "Muziejai", "Memorialai"};
    public static String savedName, savedLastName, savedTag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view1 = inflater.inflate(R.layout.fragment_recommendations, container, false);

        EditText name = view1.findViewById(R.id.editName);
        EditText lastName = view1.findViewById(R.id.editLastName);
        Spinner spinner = view1.findViewById(R.id.spinnerChoose);
        Button btnSave = view1.findViewById(R.id.btnSave);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, tags);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnSave.setOnClickListener(view -> {

            savedName = name.getText().toString();
            savedLastName = lastName.getText().toString();
            savedTag = spinner.getSelectedItem().toString();

            Navigation.findNavController(view).navigate(R.id.action_RecommendationsFragment_to_HomeFragment);
        });
        return view1;
    }
}