package br.ufpb.dcx.aps.escalonador;

import java.util.LinkedList;

import command.Comando;

public class Escalonador {

	private Comando command;

	protected LinkedList<String> filaProcessoEspera;
	protected String processoExecutando;

	protected TipoEscalonador tipoEscalonador;

	protected int quantum = 3;
	protected int tick;

	protected String status;

	public Escalonador() {
	}

	public Escalonador(TipoEscalonador tipoEscalonador) {
		if (tipoEscalonador == TipoEscalonador.Fifo) {
			this.quantum = 0;
		}

		this.tipoEscalonador = tipoEscalonador;
	}

	public Escalonador(TipoEscalonador tipoEscalonador, int quantum) {
		if (tipoEscalonador == TipoEscalonador.Fifo) {
			this.quantum = 0;
		}

		this.tipoEscalonador = tipoEscalonador;
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
	
	public void executar(Comando c) {
		c.setEscalonador(this);
		command.executar();
	}

}
