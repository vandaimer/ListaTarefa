package vandaimer.com.listatarefa;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class ContatoAdapter extends BaseAdapter {

    private final List<Contato> contatos;
    private final Activity activity;

    public ContatoAdapter(List<Contato> contatos, Activity activity) {
        this.contatos = contatos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row  = convertView;
        Contato contato = contatos.get(position);

        if(row == null){
            row = this.activity.getLayoutInflater().inflate(R.layout.celula, parent, false);
        }

        TextView desc = (TextView) row.findViewById(R.id.tv_desc);
        TextView date = (TextView) row.findViewById(R.id.tv_date);

        desc.setText(contato.getDesc());
        date.setText(contato.getDate());

        return row;
    }
}
