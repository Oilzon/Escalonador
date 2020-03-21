package command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class AdicionarProcesso implements Comando {
	Escalonador escalonador;
	String nomeProcesso;
	
	public AdicionarProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}

	public AdicionarProcesso(String string, int i) {
	}

	@Override
	public String executar() {
		escalonador.adicionarProcesso(nomeProcesso);
		return null;
	}

	@Override
	public void setEscalonador(Escalonador escalonador) {
		this.escalonador = escalonador;

	}

}
