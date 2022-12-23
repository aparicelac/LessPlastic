package com.example.lessplastic;

import static java.lang.Integer.parseInt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Form1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button cancelar;
    Button registrar;

    private String strTipo, strTamaño;
    private EditText edtPeso;
    private Spinner spnCantidad;

    public Form1Fragment(String tipo) {
        this.strTipo = tipo;
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
    public static Form1Fragment newInstance(String param1, String param2) {
        Form1Fragment fragment = new Form1Fragment("");
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_form1, container, false);
        cancelar = vista.findViewById(R.id.btnFormCancelar);
        registrar = vista.findViewById(R.id.btnFormRegistrar);

        edtPeso = vista.findViewById(R.id.edtFormWeight);
        spnCantidad = vista.findViewById(R.id.formSpinner);
        strTamaño = null;

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new FormTipoFragment());
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tipo ya está definido al inicio
                String cantidad = spnCantidad.getSelectedItem().toString().trim();
                String peso = edtPeso.getText().toString().trim();

                if ( cantidad.isEmpty() || peso.isEmpty()) {
                    Toast.makeText(getContext(), "Ingresar todos los datos en el formulario", Toast.LENGTH_SHORT).show();
                }
                else {
                    Plastico plastico = null;
                    int intCantidad = parseInt(cantidad, 10);
                    int intPeso = parseInt(peso, 10);
                    try {
                        plastico = new Plastico(-1, strTipo, intCantidad, strTamaño, intPeso);
                    }
                    catch (Exception e) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    finally {
                        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                        boolean success = databaseHelper.addPlastic(plastico);
                        Toast.makeText(getContext(), "Registrado", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        return vista;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}