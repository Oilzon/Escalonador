package command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class GetStatus implements Comando {
	
	private Escalonador escalonador;
	
	public String executar() {
		return this.getEscalonador().getStatus();
	}

	public Escalonador getEscalonador() {
		return escalonador;
	}

	@Override
	public void setEscalonador(Escalonador escalonador) {
		this.escalonador = escalonador;
		
	}
}
