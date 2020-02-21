package br.ufpb.dcx.aps.escalonador;

public class MaisCurtoPrimeiro extends FachadaEscalonador{

	public MaisCurtoPrimeiro(TipoEscalonador tipoEscalonador) {
		super(tipoEscalonador);
	}
	public String getStatus() {
		quantum = 0;
		return "Escalonador MaisCurtoPrimeiro;Processos: {};Quantum: "+ quantum +";Tick: " + tick;
		
	}

}
