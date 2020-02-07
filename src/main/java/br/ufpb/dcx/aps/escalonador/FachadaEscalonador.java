package br.ufpb.dcx.aps.escalonador;

public class FachadaEscalonador {
	
	protected TipoEscalonador tipoEscalonador;
	protected int tick;
	protected int quantum = 3;

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		this.tipoEscalonador = tipoEscalonador;
	}

	public FachadaEscalonador(TipoEscalonador tipoescalonador, int quantum) {
		this.tipoEscalonador = tipoescalonador;
		this.quantum = quantum;
	}

	public String getStatus() {
		return "Escalonador RoundRobin;Processos: {};Quantum: 3;Tick: " + tick;
	}

	public void tick() {
		tick++;
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

	public void adicionarProcessoTempoFixo(String string, int duracao) {
		
	}
}
