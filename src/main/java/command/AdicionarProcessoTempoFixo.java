package command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class AdicionarProcessoTempoFixo implements Comando {
	
	private Escalonador escalonador;
	
	private String nomeProcesso;
	private int duracao;
	
	public AdicionarProcessoTempoFixo(String nomeProcesso, int duracao) {
		this.nomeProcesso = nomeProcesso;
		this.duracao = duracao;
	}

	@Override
	public String executar() {
		escalonador.adicionarProcessoTempoFixo(nomeProcesso, duracao);
		return null;
	}

	@Override
	public void setEscalonador(Escalonador escalonador) {
		this.escalonador = escalonador;		
	}
}
