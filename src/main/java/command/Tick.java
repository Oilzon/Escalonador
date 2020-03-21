package command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public class Tick implements Comando {
	private Escalonador escalonador;
	
	@Override
	public String executar() {
		escalonador.tick();
		return null;

	}

	@Override
	public void setEscalonador(Escalonador escalonador) {
		this.escalonador = escalonador;
		
	}

}
