package br.ufpb.dcx.aps.escalonador;

public class FachadaEscalonador {
	
	private TipoEscalonador tipoEscalonador;
	private int tick;

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		if(tipoEscalonador == null) {
			throw new EscalonadorException("O tipo do escalonador é obrigatório");
		}
		this.tipoEscalonador = tipoEscalonador;
	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
		if(quantum <= 0) {
			throw new EscalonadorException("O quantum deve ser maior que zero");
		}
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
