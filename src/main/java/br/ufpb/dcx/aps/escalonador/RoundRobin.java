package br.ufpb.dcx.aps.escalonador;

public class RoundRobin extends Escalonador{

	public RoundRobin(TipoEscalonador tipoEscalonador) {
		super(tipoEscalonador);
	}
	
	public RoundRobin(TipoEscalonador tipoEscalonador, int quantum) {
		super(tipoEscalonador, quantum);
	}

}