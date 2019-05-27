package jessicahernandez.damm8.com.testing_new_features;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPlatos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPlatos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPlatos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<PlatosModel> listaPlatos;
    RecyclerView recyclerPlatos;
    PlatosAdapter adapter;

    public FragmentPlatos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPlatos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPlatos newInstance(String param1, String param2) {
        FragmentPlatos fragment = new FragmentPlatos();
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
        View view = inflater.inflate(R.layout.fragment_platos, container, false);
        recyclerPlatos = view.findViewById(R.id.recyclerPlatosID);
        recyclerPlatos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PlatosAdapter(listaPlatos);
        recyclerPlatos.setAdapter(adapter);

        HiloAPI hilo = new HiloAPI();
        hilo.execute("https://jdarestaurantapi.firebaseio.com/menu.json");

        return view;
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

    class HiloAPI extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection connection = null;
            URL url;
            String result = "";

            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();

                int data = inputStream.read();

                while(data != 1) {
                    result += (char) data;
                    data = inputStream.read();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("entrantes");

                for(int i=0; i<jsonArray.length(); i++) {
                    PlatosModel platos = new PlatosModel();

                    JSONObject jsonitem = jsonArray.getJSONObject(i);
                    platos.setNombre(jsonitem.getString("nombre"));
                    platos.setIngredientes(jsonitem.getString("ingredientes"));
                    platos.setPrecio(jsonitem.getString("precio"));
                    listaPlatos.add(platos);
                }

                JSONArray jsonArray2 = jsonObject.getJSONArray("postres");
                for(int i=0; i<jsonArray.length(); i++) {
                    PlatosModel platos = new PlatosModel();

                    JSONObject jsonitem = jsonArray2.getJSONObject(i);
                    platos.setNombre(jsonitem.getString("nombre"));
                    platos.setIngredientes(jsonitem.getString("ingredientes"));
                    platos.setPrecio(jsonitem.getString("precio"));
                    listaPlatos.add(platos);
                }

                JSONArray jsonArray3 = jsonObject.getJSONArray("principales");
                for(int i=0; i<jsonArray.length(); i++) {
                    PlatosModel platos = new PlatosModel();

                    JSONObject jsonitem = jsonArray2.getJSONObject(i);
                    platos.setNombre(jsonitem.getString("nombre"));
                    platos.setIngredientes(jsonitem.getString("ingredientes"));
                    platos.setPrecio(jsonitem.getString("precio"));
                    listaPlatos.add(platos);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            adapter.notifyDataSetChanged();

        }

    }

}
