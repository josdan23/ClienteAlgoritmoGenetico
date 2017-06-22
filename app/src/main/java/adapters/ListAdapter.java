package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.josdan.clientealgoritmogenetico.R;

import java.util.ArrayList;

import model.Zona;

/**
 * Created by josdan on 21/06/17.
 */

public class ListAdapter extends ArrayAdapter {

    private ArrayList<Zona> zonas;
    Context context;

    public ListAdapter(Context context, ArrayList<Zona> zonas){
        super(context, -1, zonas);
        this.zonas  = zonas;
        this.context = context;
    }


    @Override
    public int getCount() {
        return zonas.size();
    }

    @Override
    public Object getItem(int position) {
        return zonas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.list_item, parent, false);

        TextView tvNumeroDeZona = (TextView) item.findViewById(R.id.tvNumeroDeZona);
        TextView tvCantidadAguaRequerida = (TextView) item.findViewById(R.id.tvCantidadAguaRequerida);

        tvNumeroDeZona.setText("Numero de Zona: " + String.valueOf(zonas.get(position).getNumeroDeZona()));
        tvCantidadAguaRequerida.setText("Agua Requerida: " + String.valueOf(zonas.get(position).getCantidadAguaRequerida()));
        return item;
    }
}
