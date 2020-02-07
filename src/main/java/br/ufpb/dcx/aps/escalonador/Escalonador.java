package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

public class Escalonador extends FachadaEscalonador{

	protected List<String> filaProcessos = new ArrayList<String>();
	private List<String> filaProcessoEspera = new ArrayList<String>();
	private List<String> filaProcessoBloqueado = new ArrayList<String>();
	protected String processoASerRemovido = "";
	protected String processoExecutando = "";

	protected TipoEscalonador tipoEscalonador;
	protected int tick;
	protected String st;


	public Escalonador(TipoEscalonador tipoEscalonador) {
		super(tipoEscalonador);
	}
	
	public Escalonador(TipoEscalonador tipoEscalonador, int quantum) {
		super(tipoEscalonador, quantum);
		}
	
	public String getStatus() {
		st = "Escalonador " + super.tipoEscalonador + ";Processos: {";
		
		if(!processoASerRemovido.isEmpty()) {
			if(!filaProcessos.isEmpty()) {
				st += "Rodando: " + processoASerRemovido + ", ";
			}else {
				st += "Rodando: " + processoASerRemovido;
			}
			processoASerRemovido = "";
			}
		if(!filaProcessos.isEmpty()) {
			if(!processoExecutando.isEmpty()) {
				st += "Rodando: " + processoExecutando;
				if(!filaProcessos.isEmpty()) {
					st += ", Fila: " + filaProcessos;
				}
				alternarProcesso();
				
			}else {
				st += "Fila: " + filaProcessos;
			}
					
		}else if (!processoExecutando.isEmpty()) {
			st += "Rodando: " + processoExecutando;
			
		}
		
		return st += "};Quantum: " + quantum + ";Tick: " + tick;
	}

	public void tick() {
		tick++;
		
		executarProcesso();
		
	}
	@Override
	public void adicionarProcesso(String nomeProcesso) {
		filaProcessos.add(nomeProcesso);

	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
		filaProcessos.add(nomeProcesso);

	}

	public void finalizarProcesso(String nomeProcesso) {
		processoASerRemovido = processoExecutando;
		processoExecutando = "";
	}

	public void bloquearProcesso(String nomeProcesso) {
		if(processoExecutando.equals(nomeProcesso)) {
			filaProcessoBloqueado.add(processoExecutando);
		}
	}

	public void retomarProcesso(String nomeProcesso) {

	}

	public void adicionarProcessoTempoFixo(String string, int duracao) {
		
	}

	public void alternarProcesso() {
		if (tick % quantum == 0) {
			filaProcessos.add(processoExecutando);
			processoExecutando = filaProcessos.get(0);
			filaProcessos.remove(0);
		}

	}
	
	public void executarProcesso() {
		if(!filaProcessos.isEmpty() && processoExecutando.isEmpty()) {
			processoExecutando = filaProcessos.get(0);
			filaProcessos.remove(0);
		}
	}
}
