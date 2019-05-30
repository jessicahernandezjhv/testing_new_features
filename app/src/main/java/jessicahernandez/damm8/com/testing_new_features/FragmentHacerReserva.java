package jessicahernandez.damm8.com.testing_new_features;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentHacerReserva.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentHacerReserva#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHacerReserva extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // ELEMENTOS EN PANTALLA
    EditText edtFecha;
    EditText edtComensales;
    EditText edtNombre;
    EditText edtTelefono;
    EditText edtComentarios;
    Button btnHacerReserva;

    // FIREBASE DATABASE
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    // Elemento raíz de nuestra DataBase
    String databasePath = "reservas";

    private OnFragmentInteractionListener mListener;

    public FragmentHacerReserva() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHacerReserva.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHacerReserva newInstance(String param1, String param2) {
        FragmentHacerReserva fragment = new FragmentHacerReserva();
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
        View view = inflater.inflate(R.layout.fragment_hacer_reserva, container, false);
        edtFecha = view.findViewById(R.id.editFechaID);
        edtComensales = view.findViewById(R.id.editComensalesID);
        edtNombre = view.findViewById(R.id.editNombreID);
        edtTelefono = view.findViewById(R.id.editTelefonoID);
        edtComentarios = view.findViewById(R.id.editComentariosID);
        btnHacerReserva = view.findViewById(R.id.btnHacerReserva);

        //Firebase Database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(databasePath);

        //Generamos un listener para cuando clickamos el botón Enviar
        btnHacerReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacerReserva();
            }
        });
        return view;
    }

    // Función que lee los datos introducidos por el usuario y los escribe en Firebase DB
    private void hacerReserva(){
        final String fecha = edtFecha.getText().toString();
        final String comensales = edtComensales.getText().toString();
        final String nombre = edtNombre.getText().toString();
        final String telefono = edtTelefono.getText().toString();
        final String comentarios = edtComentarios.getText().toString();

        ModelReservas nuevaReserva = new ModelReservas(fecha, comensales, nombre, telefono, comentarios);

        String nuevaReservaID = databaseReference.push().getKey();
        databaseReference.child(nuevaReservaID).setValue(nuevaReserva);
        Toast.makeText(getActivity(),"Reserva realizada",Toast.LENGTH_SHORT).show();
        clearEditFields();
    }

    // Función que limpia los campos para realizar una nueva reserva
    public void clearEditFields() {
        SystemClock.sleep(500);
        edtFecha.getText().clear();
        edtComensales.getText().clear();
        edtNombre.getText().clear();
        edtTelefono.getText().clear();
        edtComentarios.getText().clear();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
