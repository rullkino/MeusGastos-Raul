package br.com.rafaelleme.senai.meusgastos.vo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.rafaelleme.senai.meusgastos.R;

public class LancamentoViewHolder extends RecyclerView.ViewHolder {

    public TextView txtDescricao, txtTipoLancamento, txtData, txtValor;
    public LinearLayout adpLayout;

    public LancamentoViewHolder(View itemView) {
        super(itemView);

        txtTipoLancamento = itemView.findViewById(R.id.txtTipoLancamento);
        adpLayout = itemView.findViewById(R.id.adplayout);
        txtData = itemView.findViewById(R.id.txtData);
        txtDescricao = itemView.findViewById(R.id.txtDescricao);
        txtValor = itemView.findViewById(R.id.txtValor);
    }
}
