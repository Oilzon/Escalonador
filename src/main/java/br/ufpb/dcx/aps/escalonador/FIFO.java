package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FIFO extends Escalonador {

	private LinkedList<String> filaProcessoEspera;
	private List<Integer> duracoes;
	private LinkedList<String> processoFinalizar;

	private boolean executando;
	private String processoExecutando;
	private int duracaoProcessoExecutando;
	private int duracaoFixa;

	public FIFO(TipoEscalonador tipoEscalonador) {
		super(tipoEscalonador);
		filaProcessoEspera = new LinkedList<String>();
		duracoes = new ArrayList<Integer>();
		processoFinalizar = new LinkedList<String>();
	}

	public FIFO(TipoEscalonador tipoEscalonador, int quantum) {
		super(tipoEscalonador, quantum);
	}

	public String getStatus() {
		status = "Escalonador Fifo;Processos: {";

		if(processoExecutando == null) {
			if(!filaProcessoEspera.isEmpty()) {
				status += "Fila: " + filaProcessoEspera;	
			}
		}else {
			if(filaProcessoEspera.isEmpty()) {
				status += "Rodando: " + processoExecutando;
			
			}else {
				status += "Rodando: " + processoExecutando + ", Fila: " + filaProcessoEspera.toString();
			
			}
		}
		return status += "};Quantum: " + quantum + ";Tick: " + tick;
	}

	public void tick() {
		tick++;

		executarPrimeiroProcesso();

		executarNovoProcesso();

	}

	public void adicionarProcesso(String nomeProcesso) {
		filaProcessoEspera.add(nomeProcesso);
	}

	public void adicionarProcessoTempoFixo(String nomeProcesso, int duracao) {
		adicionarProcesso(nomeProcesso);
		duracoes.add(duracao);
	}

	public void executarPrimeiroProcesso() {
		if (!filaProcessoEspera.isEmpty() && processoExecutando == null) {
			processoExecutando = filaProcessoEspera.pollFirst();
			duracaoProcessoExecutando = duracoes.remove(0);
			duracaoFixa = tick + duracaoProcessoExecutando;
		}
	}

	private void executarNovoProcesso() {
		if (duracaoFixa == tick && processoExecutando != null) {
			if (!filaProcessoEspera.isEmpty()) {
				processoExecutando = filaProcessoEspera.pollFirst();
				duracaoProcessoExecutando = duracoes.remove(0);
			}else {
				processoExecutando = null;
				duracaoProcessoExecutando = 0;
			}
			if(duracaoProcessoExecutando > 0) {
				duracaoFixa = tick + duracaoProcessoExecutando;
			}
		}
	}

}
