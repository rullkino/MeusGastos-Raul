package br.com.rafaelleme.senai.meusgastos;

import android.app.Dialog;
import android.content.Context;
import android.service.voice.VoiceInteractionSession;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rafaelleme.senai.meusgastos.vo.Lancamento;
import br.com.rafaelleme.senai.meusgastos.vo.LancamentoViewHolder;


public class LancamentoAdapter extends RecyclerView.Adapter<LancamentoViewHolder> {

    private Context context;
    private List<Lancamento> lancamentos;

    private LancamentoService lancamentoService;

    public LancamentoAdapter(Context context, List<Lancamento> lancamentos) {
        this.context = context;
        this.lancamentos = lancamentos;
    }

    @Override
    public LancamentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.adapter_lancamentos, parent,false);

        LancamentoViewHolder lancamentoViewHolder = new LancamentoViewHolder(view);

        return lancamentoViewHolder;
    }

    @Override
    public void onBindViewHolder(LancamentoViewHolder holder, final int position) {
        final Lancamento lancamento = lancamentos.get(position);

        if(lancamento.getTipo().equals("Receita")){
            holder.adpLayout.setBackgroundColor(context.getResources().getColor(R.color.receita));
        }else{
            holder.adpLayout.setBackgroundColor(context.getResources().getColor(R.color.despesa));
        }

        holder.txtTipoLancamento.setText(lancamento.getTipo());
        holder.txtData.setText(lancamento.getData());
        holder.txtDescricao.setText(lancamento.getDescricao());
        holder.txtValor.setText(lancamento.getValor().toString());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                lancamentoService = new LancamentoService(context);
                if(lancamentoService.excluirEntrada(lancamento.getId())){
                    lancamentos.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,lancamentos.size());

                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return lancamentos != null ? lancamentos.size() : 0;
    }
}
