package br.com.rafaelleme.senai.meusgastos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import br.com.rafaelleme.senai.meusgastos.vo.Lancamento;

public class  MainActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton fbLancamentos;
    RecyclerView rvLancamentos;
    List<Lancamento> lancamentos;
    LancamentoService lcService;
    LancamentoAdapter lcAdapter;
    TextView txtTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvLancamentos = findViewById(R.id.rvLancamentos);
        fbLancamentos = findViewById(R.id.fbNovosLancamentos);
        txtTotal = findViewById(R.id.txtSaldoTotal);
        fbLancamentos.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        lcService = new LancamentoService(this);
        lancamentos = lcService.carregarLancamentos();
        lcAdapter = new LancamentoAdapter(this, lancamentos);

        if(lancamentos == null){
            txtTotal.setText("0");
        }else {
            txtTotal.setText(String.valueOf(lcService.totalBanco(lancamentos)));
        }

        rvLancamentos.setLayoutManager(new LinearLayoutManager(this));
        rvLancamentos.setAdapter(lcAdapter);
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fbNovosLancamentos:
                Intent intent = new Intent(this, LancamentoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
