package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Escalonador extends FachadaEscalonador {

	private LinkedList<String> filaProcessosEspera = new LinkedList<String>();
	private List<String> filaProcessoBloqueado = new ArrayList<String>();
	private List<Integer> prioridades = new ArrayList<Integer>();
	private LinkedList<String> processoASerRemovido = new LinkedList<String>();
	private LinkedList<String> processoExecutando = new LinkedList<String>();
	private LinkedList<String> removidoDaEspera = new LinkedList<String>();
	
	private TipoEscalonador tipoEscalonador;
	private int tick;
	private int quantum = 3;
	private int tempoChegada = 0;
	private String st;
	

	public Escalonador(TipoEscalonador tipoEscalonador) {
		super(tipoEscalonador);
		this.tipoEscalonador = tipoEscalonador;
	}

	public Escalonador(TipoEscalonador tipoEscalonador, int quantum) {
		super(tipoEscalonador, quantum);
		this.tipoEscalonador = tipoEscalonador;
	}

	public String getStatus() {
		st = "Escalonador " + this.tipoEscalonador + ";Processos: {";

		if (!processoASerRemovido.isEmpty()) {
			if (!filaProcessosEspera.isEmpty()) {
				st += "Rodando: " + processoASerRemovido.getFirst() + ", ";

			} else {
				System.out.println("VAI ELSE");
				st += "Rodando: " + processoASerRemovido.getFirst();
			}
			processoASerRemovido.pollFirst();
		}
		if (!processoExecutando.isEmpty()) {
			if (filaProcessosEspera.isEmpty()) {
				st += "Rodando: " + processoExecutando.getFirst();
			} else {
				st += "Rodando: " + processoExecutando.getFirst() + ", Fila: " + filaProcessosEspera;
			}
			alternarProcesso();
		} else {
			if (!filaProcessosEspera.isEmpty()) {
				st += "Fila: " + filaProcessosEspera;
			}

		}if(!removidoDaEspera.isEmpty()) {
			st += ", Fila: "+ removidoDaEspera;
			removidoDaEspera.clear();
		}

		return st += "};Quantum: " + quantum + ";Tick: " + tick;
	}

	public void tick() {
		
		tick++;

		executarProcesso();

		

	}

	@Override
	public void adicionarProcesso(String nomeProcesso) {
		filaProcessosEspera.add(nomeProcesso);
		tempoChegada = tick;


	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
		filaProcessosEspera.add(nomeProcesso);
		prioridades.add(prioridade);

	}

	public void finalizarProcesso(String nomeProcesso) {
		if (processoExecutando.getFirst().equals(nomeProcesso)) {
			processoASerRemovido.add(processoExecutando.pollFirst());
		}else if(filaProcessosEspera.contains(nomeProcesso)) {
			filaProcessosEspera.remove(nomeProcesso);
			removidoDaEspera.add(nomeProcesso);			
			
		}
	}

	public void bloquearProcesso(String nomeProcesso) {
//		if (processoExecutando.get(0).equals(nomeProcesso)) {
//			filaProcessoBloqueado.add(processoExecutando);
//		}
	}

	public void retomarProcesso(String nomeProcesso) {

	}

	public void adicionarProcessoTempoFixo(String string, int duracao) {

	}

	public void alternarProcesso() {
		String temp;
		if (tick % quantum == 0) {
			temp = processoExecutando.pollFirst();
			filaProcessosEspera.add(temp);
			processoExecutando.add(filaProcessosEspera.pollFirst());
		
		}
		/*
		 * else if (tempoChegada > 0 && tick % (2 * quantum) == 0) { filaProcessosEspera
		 * processoExecutando.add(filaProcessosEspera.pollFirst()); tempoChegada = 0;
		 * 
		 * }
		 */	}

	public void executarProcesso() {
		if (!filaProcessosEspera.isEmpty() && processoExecutando.isEmpty()) {
			processoExecutando.add(filaProcessosEspera.poll());
		}
	}
	

}
