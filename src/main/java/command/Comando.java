package command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public interface Comando {
		
		public String executar();

		public void setEscalonador(Escalonador escalonador);
}
