package br.com.rafaelleme.senai.meusgastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rafaelleme.senai.meusgastos.model.GastosDB;
import br.com.rafaelleme.senai.meusgastos.vo.Lancamento;

public class LancamentoService {

    private SQLiteDatabase dbMeusGastos;
    private GastosDB bancoMeusGastos;

    public LancamentoService(Context context) { bancoMeusGastos = new GastosDB(context);}

    public long inserirLancamento(String descricao, double valor, String data,String tipo) {
        ContentValues valoresCampos;
        long resultadoRetorno;

        dbMeusGastos = bancoMeusGastos.getWritableDatabase();
        valoresCampos = new ContentValues();
        valoresCampos.put(GastosDB.DESCRICAO, descricao);
        valoresCampos.put(GastosDB.VALOR, valor);
        valoresCampos.put(GastosDB.DATA, data);
        valoresCampos.put(GastosDB.TIPO, tipo);


        resultadoRetorno = dbMeusGastos.insert(GastosDB.TABELA_LANCAMENTOS, null, valoresCampos);
        dbMeusGastos.close();

        return resultadoRetorno;
    }

    public List<Lancamento> carregarLancamentos() {
        Cursor cursorLancamentos;
        String[] camposTabela = {bancoMeusGastos.ID_LANCAMENTO, bancoMeusGastos.DESCRICAO, bancoMeusGastos.VALOR, bancoMeusGastos.DATA , bancoMeusGastos.TIPO};
        dbMeusGastos = bancoMeusGastos.getReadableDatabase();
        cursorLancamentos = dbMeusGastos.query(bancoMeusGastos.TABELA_LANCAMENTOS, camposTabela, null, null,
                null, null, null, null);

        if (cursorLancamentos != null && cursorLancamentos.getCount() > 0) {
            dbMeusGastos.close();
            cursorLancamentos.moveToFirst();
            return toList(cursorLancamentos);
        } else {
            dbMeusGastos.close();
            return null;
        }
    }

    private List<Lancamento> toList(Cursor c) {
        List<Lancamento> lancamentos = new ArrayList<>();

        do {
            Lancamento l = new Lancamento();
            l.setId(c.getLong(c.getColumnIndexOrThrow(GastosDB.ID_LANCAMENTO)));
            l.setDescricao(c.getString(c.getColumnIndexOrThrow(GastosDB.DESCRICAO)));
            l.setData(c.getString(c.getColumnIndexOrThrow(GastosDB.DATA)));
            l.setValor(c.getDouble(c.getColumnIndexOrThrow(GastosDB.VALOR)));
            l.setTipo(c.getString(c.getColumnIndexOrThrow(GastosDB.TIPO)));

            lancamentos.add(l);
        } while (c.moveToNext());

        return lancamentos;
    }

    public boolean excluirEntrada(long idLancamento){
        boolean excluiu = false;
        String clausulaWhere = bancoMeusGastos.ID_LANCAMENTO + "=" + idLancamento;

        dbMeusGastos = bancoMeusGastos.getWritableDatabase();
        excluiu = dbMeusGastos.delete(bancoMeusGastos.TABELA_LANCAMENTOS, clausulaWhere,null) > 0 ;
        return excluiu;
    }

    public double totalBanco(List<Lancamento> lancamentos){
       double salame = 0.0;
       if(!lancamentos.isEmpty()) {
           for (Lancamento l : lancamentos) {
               if (l.getTipo().equals("Receita")) {
                   salame += l.getValor();
               } else {
                   salame -= l.getValor();
               }
           }
       }
        return salame;
    }


}
