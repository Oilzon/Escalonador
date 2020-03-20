package br.ufpb.dcx.aps.escalonador;

public class Escalonador {

	protected TipoEscalonador tipoEscalonador;

	protected int quantum;
	protected int tick;

	protected String status;

	public Escalonador() {
	}

	public Escalonador(TipoEscalonador tipoEscalonador) {
		this.tipoEscalonador = tipoEscalonador;
	}

	public Escalonador(TipoEscalonador tipoEscalonador, int quantum) {
		this.tipoEscalonador = tipoEscalonador;
		this.quantum = 0;
	}

	public void tick() {
		tick++;
	}

	public String getStatus() {
		return "Escalonador RoundRobin;Processos: {};Quantum: 3;Tick: " + tick;
	}

	public void adicionarProcessoTempoFixo(String nomeProcesso, int duracao) {

	}

	public void adicionarProcesso(String nomeProcesso) {
		throw new EscalonadorException();
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
		throw new EscalonadorException();
	}

}
