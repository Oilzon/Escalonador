package br.ufpb.dcx.aps.escalonador;

public class FachadaEscalonador {

	protected TipoEscalonador tipoEscalonador;
	protected String status;
	protected int tick;
	private FIFO escalonador;

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		if (tipoEscalonador.equals(tipoEscalonador.Fifo)) {
			escalonador = new FIFO(tipoEscalonador);
		}
	}

	public FachadaEscalonador(TipoEscalonador tipoEscalonador, int quantum) {
		if (this.tipoEscalonador.equals(tipoEscalonador.Fifo)) {
			escalonador = new FIFO(tipoEscalonador);
		}
	}

	public String getStatus() {
		return escalonador.getStatus();
	}

	public void tick() {
		escalonador.tick();
	}

	public void adicionarProcesso(String nomeProcesso) {
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
	}

	public void finalizarProcesso(String nomeProcesso) {
	}

	public void bloquearProcesso(String nomeProcesso) {
	}

	public void retomarProcesso(String nomeProcesso) {

	}

	public void adicionarProcessoTempoFixo(String nomeProcesso, int duracao) {
		
		escalonador.adicionarProcessoTempoFixo(nomeProcesso, duracao);

	}
}
