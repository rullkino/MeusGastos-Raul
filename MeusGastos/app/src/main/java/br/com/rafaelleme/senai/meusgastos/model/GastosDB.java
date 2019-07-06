package br.com.rafaelleme.senai.meusgastos.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GastosDB extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "BdGastos";

    public static final String TABELA_LANCAMENTOS = "Lancamentos";
    public static final String ID_LANCAMENTO = "Id_Lancamento";
    public static final String DESCRICAO = "Descricao";
    public static final String VALOR = "Valor";
    public static final String DATA = "Data";
    public static final String TIPO = "Tipo";

    public static final int VERSAO = 3;

    public GastosDB(Context context){
        super(context,NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlComando = " CREATE TABLE IF NOT EXISTS " + TABELA_LANCAMENTOS + " ( Id_Lancamento INTEGER PRIMARY KEY AUTOINCREMENT, Descricao TEXT, Valor DECIMAL, Data TEXT, Tipo TEXT); ";
        db.execSQL(sqlComando);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlComando = " DROP TABLE IF EXISTS " + TABELA_LANCAMENTOS;
        db.execSQL(sqlComando);
        onCreate(db);
    }
}
