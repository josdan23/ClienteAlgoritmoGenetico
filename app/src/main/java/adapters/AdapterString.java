package adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.josdan.clientealgoritmogenetico.R;

import java.util.ArrayList;

/**
 * Created by josdan on 21/06/17.
 */

public class AdapterString extends ArrayAdapter {
    private Context context;
    private ArrayList<Integer> soluciones;
    private ArrayList<String> encabezado;

    public AdapterString(Context context, ArrayList<Integer> soluciones, ArrayList<String> encabezado) {
        super(context,-1, soluciones);
        this.context= context;
        this.soluciones = soluciones;
        this.encabezado = encabezado;

    }

    @Override
    public int getCount() {
        return soluciones.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return soluciones.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View item = inflater.inflate(R.layout.list_item, parent, false);

        TextView tvNumeroDeZona = (TextView) item.findViewById(R.id.tvNumeroDeZona);
        TextView tvCantidadAguaRequerida = (TextView) item.findViewById(R.id.tvCantidadAguaRequerida);

        tvNumeroDeZona.setText(encabezado.get(position));
        tvCantidadAguaRequerida.setText(soluciones.get(position).toString());


        return item;
    }
}
