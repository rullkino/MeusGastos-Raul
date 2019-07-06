package br.com.rafaelleme.senai.meusgastos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LancamentoActivity extends AppCompatActivity {

    EditText edtDescrcao, edtData, edtValor;
    LancamentoService lcSercvice;
    RadioGroup rgTipoTransacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento);

        lcSercvice = new LancamentoService(this);

        edtData = findViewById(R.id.edtData);
        edtDescrcao = findViewById(R.id.edtDescricao);
        rgTipoTransacao = findViewById(R.id.rgTipoTransacao);
        edtValor = findViewById(R.id.edtValor);
        rgTipoTransacao.check(R.id.rbDispesa);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnSalvar:
                if(edtData.getText().toString().equals("")||edtDescrcao.getText().toString().equals("")||edtValor.getText().toString().equals("")){
                    Toast.makeText(this, "Todos os campos são obrogatórios", Toast.LENGTH_SHORT).show();
                }else{
                    if(salvar()){
                        Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    }else{
                        Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean salvar(){
        String tipoTransacao, descricao, data;
        Double valor;

        if(rgTipoTransacao.getCheckedRadioButtonId() == R.id.rbDispesa){
            tipoTransacao = "Despesa";
        }else{
            tipoTransacao = "Receita";
        }
        data = edtData.getText().toString();
        descricao = edtDescrcao.getText().toString();
        valor = Double.valueOf(edtValor.getText().toString());

        if(lcSercvice.inserirLancamento(descricao,valor,data,tipoTransacao) > 0){
            return true;
        }
        return false;
    }
}
