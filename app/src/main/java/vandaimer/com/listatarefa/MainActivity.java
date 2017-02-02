package vandaimer.com.listatarefa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {

    private EditText etTask;
    private ListView lvTask;
    private Button btAddTask;
    ArrayList<Contato> contatos = new ArrayList<>();
    ContatoAdapter contatoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contatoAdapter = new ContatoAdapter(contatos, this);

        etTask = (EditText) findViewById(R.id.etTask);
        lvTask = (ListView) findViewById(R.id.lvTask);
        btAddTask = (Button) findViewById(R.id.btAddTask);

        lvTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Exclusão de tarefa")
                        .setMessage("Deseja apagar a tarefa selecionada?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                contatos.remove(position);
                                contatoAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
                return true;
            }
        });

        btAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etTask.getText().toString().isEmpty()){
                    Contato contato = new Contato();
                    contato.setDesc(etTask.getText().toString());
                    contato.setDate(new Date().toString());
                    contatos.add(contato);

                    updateListTask();
                    etTask.setText("");
                }
            }
        });
    }

    public void updateListTask(){

        if(lvTask.getAdapter() == null){
            lvTask.setAdapter(contatoAdapter);
        }
    }
}
