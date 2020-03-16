package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FIFO extends FachadaEscalonador {

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

		if (!executando) {
			if (!filaProcessoEspera.isEmpty()) {
				status += "Fila: " + filaProcessoEspera.toString();

			}
		} else if (executando) {
			if (!processoFinalizar.isEmpty()) {
				if (!filaProcessoEspera.isEmpty()) {
					status += "Rodando: " + processoFinalizar.pollFirst() + ", Fila: " + filaProcessoEspera.toString();
					executando = false;
				}else {
					status += "Rodando: " + processoFinalizar.pollFirst();
					executando = false;
				}

			} else if (!filaProcessoEspera.isEmpty()) {
				status += "Rodando: " + processoExecutando + ", Fila: " + filaProcessoEspera.toString();
			} else {
				status += "Rodando: " + processoExecutando;
			}
		}

		return status += "};Quantum: 0;Tick: " + tick;
	}

	public void tick() {
		tick++;

		executarProcesso();

		finalizaPorSiSo();
		
		duracaoProcessoExecutando--;
		
	}

	public void adicionarProcesso(String nomeProcesso) {
		filaProcessoEspera.add(nomeProcesso);
	}

	public void adicionarProcessoTempoFixo(String nomeProcesso, int duracao) {
		adicionarProcesso(nomeProcesso);
		duracoes.add(duracao);
	}

	public void executarProcesso() {
		if (!filaProcessoEspera.isEmpty() && processoExecutando == null) {
			if (!executando) {
				executando = true;
				processoExecutando = filaProcessoEspera.pollFirst();
				duracaoProcessoExecutando = duracoes.remove(0);
			}
		}
	}

	public void finalizaPorSiSo() {
		if (processoExecutando != null && duracaoProcessoExecutando == 1) {
			processoFinalizar.add(processoExecutando);
			processoExecutando = null;
			duracaoProcessoExecutando = 0;
		}
	}

}
