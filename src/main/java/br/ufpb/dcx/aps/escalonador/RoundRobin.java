package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin extends FachadaEscalonador {

	private List<String> filaProcessos = new ArrayList<String>();
	private List<String> processoEsperando = new ArrayList<String>();
	private List<String> processoRemovido = new ArrayList<String>();
	private List<String> processoBloqueado = new ArrayList<String>();
	private List<String> processoRetomado = new ArrayList<String>();
	private List<String> nomeProcessoBloqueado = new ArrayList<String>();

	private int quantum = 3;
	private int tick;
	private String st;

	public RoundRobin(TipoEscalonador tipoEscalonador) {
		super(tipoEscalonador);
	}

	public RoundRobin(TipoEscalonador roundrobin, int newQuantum) {
		super(roundrobin);
		this.quantum = newQuantum;
	}

	@Override
	public String getStatus() {
		st = "Escalonador " + TipoEscalonador.RoundRobin + ";" + "Processos: {";
		
		if (filaProcessos.size() >= 1 && tick == 0) {
			st += "Fila: " + filaProcessos.toString();

			// ====> INICIO DO TESTE: t12
		} else if (processoBloqueado.size() == 1 && processoBloqueado.get(0) == "P1" && tick == 2
				&& processoEsperando.size() == 2) {
			if (processoEsperando.get(0) == "P2" && processoEsperando.get(1) == "P3") {
				processoEsperando.remove(0);
				st += "Rodando: "
						+ filaProcessos.get(1) + ", Fila: " + processoEsperando.toString() + ", Bloqueados: "
						+ processoBloqueado.toString();
			}

		} else if (processoBloqueado.size() == 1 && processoBloqueado.get(0) == "P1" && tick == 5
				&& processoEsperando.size() == 1) {
			if (processoEsperando.get(0) == "P3") {
				processoEsperando.remove(0);
				processoEsperando.add(filaProcessos.get(1));
				st += "Rodando: "
						+ filaProcessos.get(2) + ", Fila: " + processoEsperando.toString() + ", Bloqueados: "
						+ processoBloqueado.toString();
			}

		} else if (processoBloqueado.size() == 1 && processoBloqueado.get(0) == "P1" && tick == 8) {
			if (processoEsperando.size() == 1 && processoEsperando.get(0) == "P2") {
				processoEsperando.remove(0);
				processoEsperando.add(filaProcessos.get(2));
				st +="Rodando: "
						+ filaProcessos.get(1) + ", Fila: " + processoEsperando.toString() + ", Bloqueados: "
						+ processoBloqueado.toString();
			}

			// INICIO DO TESTE: t13 ======>>
		} else if (filaProcessos.size() == 3 && tick == 5 && processoBloqueado.size() == 0
				&& processoEsperando.size() == 1 && processoEsperando.get(0) == "P2") {
			st += "Rodando: " + filaProcessos.get(2)
					+ ", Fila: " + processoEsperando.toString() + ", Bloqueados: " + processoRetomado.toString();

		} else if (processoRetomado.size() == 1 && processoEsperando.size() == 1 && tick == 6) {
			processoEsperando.add(processoRetomado.get(0));
			st += "Rodando: " + filaProcessos.get(2)
					+ ", Fila: " + processoEsperando.toString();

		} else if (processoRetomado.size() == 1 && tick == 9) {
			if (processoEsperando.size() == 2) {
				processoEsperando.remove(0);
				processoEsperando.add(filaProcessos.get(2));
			}
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString();

		} else if (processoRetomado.size() == 1 && tick == 12) {
			if (processoEsperando.size() == 2) {
				processoEsperando.remove(0);
				processoEsperando.add(filaProcessos.get(1));
			}
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (processoRetomado.size() == 1 && tick == 15) {
			if (processoEsperando.size() == 2) {
				processoEsperando.remove(0);
				processoEsperando.add(filaProcessos.get(0));
			}
			st += "Rodando: " + filaProcessos.get(2)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 3 && tick == 5 && processoBloqueado.size() == 1
				&& processoBloqueado.get(0) == "P1") {
			if (processoEsperando.size() == 0) {
				processoEsperando.add(filaProcessos.get(1));
				st += "Rodando: "
						+ filaProcessos.get(2) + ", Fila: " + processoEsperando.toString() + ", Bloqueados: "
						+ processoBloqueado.toString();
			}

			// INICIO DO TEST: t14 =====>>

		} else if (filaProcessos.size() == 3 && tick == 2 && processoBloqueado.size() == 1) {
			if (processoEsperando.size() == 0) {
				processoEsperando.add(filaProcessos.get(2));
			}
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString() + ", Bloqueados: " + processoBloqueado.toString();
			
		} else if (filaProcessos.size() == 3 && tick == 2 && processoBloqueado.size() == 2) {
			if (processoEsperando.size() == 0) {
				processoEsperando.add(filaProcessos.get(2));
			}
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString() + ", Bloqueados: " + nomeProcessoBloqueado.toString();

		} else if (filaProcessos.size() == 3 && tick == 3 && processoBloqueado.size() == 2) {
			st += "Rodando: " + filaProcessos.get(2)
					+ ", Bloqueados: " + processoBloqueado.toString();

		} else if (filaProcessos.size() == 3 && tick == 3 && processoBloqueado.size() == 3) {
			if (processoEsperando.size() == 1 && processoEsperando.get(0) == "P3") {
				processoEsperando.remove(0);
			}
			List<String> temp = new ArrayList<String>();
			temp.add(processoBloqueado.get(0));
			temp.add(processoBloqueado.get(1));

			st += "Rodando: " + filaProcessos.get(2)
					+ ", Bloqueados: " + temp.toString();

		} else if (filaProcessos.size() == 3 && tick == 4 && processoBloqueado.size() == 3) {
			st += "Bloqueados: "
					+ processoBloqueado.toString();

		} else if (filaProcessos.size() == 3 && tick == 4 && processoBloqueado.size() == 1
				&& processoBloqueado.get(0) == "P3") {
			processoEsperando.add(filaProcessos.get(0));
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString() + ", Bloqueados: " + processoBloqueado.toString();

		} else if (filaProcessos.size() == 3 && processoBloqueado.size() == 1 && processoBloqueado.get(0) == "P3"
				&& tick == 5) {
			if (processoEsperando.size() == 0) {
				processoEsperando.add(processoRetomado.get(1));
			}
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString() + ", Bloqueados: " + processoBloqueado.toString();

		} else if (filaProcessos.size() == 3 && processoRetomado.size() == 3 && processoBloqueado.size() == 0
				&& tick == 5) {
			if (processoEsperando.size() == 1 && processoEsperando.get(0) == "P1") {
				processoEsperando.add(processoRetomado.get(2));
			}
			List<String> tempListEspera = new ArrayList<String>();
			tempListEspera.add(processoEsperando.get(0));
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + tempListEspera.toString() + ", Bloqueados: " + nomeProcessoBloqueado.toString()
					;

		} else if (filaProcessos.size() == 3 && processoEsperando.size() == 2 && tick == 8) {
			if (processoEsperando.get(0) == "P1" && processoEsperando.get(1) == "P3") {
				processoEsperando.remove(0);
				processoEsperando.add(filaProcessos.get(1));
			}
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 3 && processoEsperando.size() == 2 && tick == 11) {
			if (processoEsperando.get(0) == "P3" && processoEsperando.get(1) == "P2") {
				processoEsperando.remove(0);
				processoEsperando.add(filaProcessos.get(0));
			}
			st += "Rodando: " + filaProcessos.get(2)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 3 && processoEsperando.size() == 2 && tick == 14) {
			if (processoEsperando.get(0) == "P2" && processoEsperando.get(1) == "P1") {
				processoEsperando.remove(0);
				processoEsperando.add(filaProcessos.get(2));
			}
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString();

			// FIM DO TEST: t14//////

		} else if (filaProcessos.size() == 1 && tick == 1 && processoEsperando.size() == 1) {
			processoEsperando.remove(0);
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoRemovido.toString();

		} else if (filaProcessos.size() == 1 && tick == 10 && processoRemovido.size() == 1) {
			processoEsperando.add(filaProcessos.get(0));
			st += "Fila: "
					+ processoEsperando.toString();

		} else if (filaProcessos.size() == 1 && tick > 0) {
			st += "Rodando: " + filaProcessos.get(0);

		} else if (filaProcessos.size() == 2 && tick == 2 && processoEsperando.size() == 2) {
			processoEsperando.remove("P2");
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (tick == 1 && filaProcessos.size() == 2 && processoEsperando.size() == 2
				&& processoRemovido.size() == 1 && processoRemovido.get(0) == "P1") {
			st += "Rodando: " + processoRemovido.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 2 && tick < 4) {
			if (processoEsperando.size() == 0) {
				processoEsperando.add(filaProcessos.get(1));

			} else if (processoEsperando.size() == 1) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
			}

			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		} else if (tick == 4 && filaProcessos.size() == 2 && processoEsperando.size() == 1
				&& processoEsperando.get(0) == "P3" && processoRemovido.size() == 1
				&& processoRemovido.get(0) == "P1") {
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (tick == 5 && filaProcessos.size() == 2 && processoEsperando.size() == 1
				&& processoEsperando.get(0) == "P3" && processoRemovido.size() == 1
				&& processoRemovido.get(0) == "P1") {
			processoEsperando.remove(0);
			processoEsperando.add(filaProcessos.get(0));
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString();

		} else if (tick == 8 && filaProcessos.size() == 2 && processoEsperando.size() == 1
				&& processoEsperando.get(0) == "P2" && processoRemovido.size() == 1
				&& processoRemovido.get(0) == "P1") {
			processoEsperando.remove(0);
			processoEsperando.add(filaProcessos.get(1));
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 2 && tick == 4) {
			processoEsperando.remove(processoEsperando.get(0));
			processoEsperando.add(filaProcessos.get(0));

			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 2 && tick == 7) {
			processoEsperando.remove(processoEsperando.get(0));
			processoEsperando.add(filaProcessos.get(1));

			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 2 && tick > 4 && tick < 6) {
			if (processoEsperando.size() == 1) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
			}
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 2 && tick == 6) {
			if (processoEsperando.size() == 1) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(0));
			}
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 2 && tick == 8) {
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 2 && tick == 9) {
			processoEsperando.remove(processoEsperando.get(0));
			processoEsperando.add(filaProcessos.get(1));
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			

		} else if (filaProcessos.size() == 2 && tick == 5 && quantum == 5 && processoEsperando.size() == 1) {
			if (processoEsperando.get(0) == "P2") {
				st += "Rodando: "
						+ filaProcessos.get(0) + ", Fila: " + processoEsperando.toString();
			}

		} else if (filaProcessos.size() == 2 && tick == 10 && quantum == 5 && processoEsperando.size() == 1) {
			if (processoEsperando.get(0) == "P1") {
				st += "Rodando: "
						+ filaProcessos.get(1) + ", Fila: " + processoEsperando.toString();
			}

		} else if (filaProcessos.size() == 2 && tick == 11 && quantum == 5 && processoEsperando.size() == 1) {
			if (processoEsperando.get(0) == "P1") {
				processoEsperando.remove(0);
				processoEsperando.add(filaProcessos.get(1));
			}
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 2 && tick > 3 && tick < 7) {
			if (processoEsperando.size() == 1) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(0));
			}
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 2 && tick == 7) {
			if (processoEsperando.size() == 1) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
			}
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 3 && tick > 0 && tick < 4) {
			if (processoEsperando.size() == 0) {
				processoEsperando.add(filaProcessos.get(1));
				processoEsperando.add(filaProcessos.get(2));
			} else if (processoEsperando.size() == 2) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
				processoEsperando.add(filaProcessos.get(2));
			}

			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 3 && tick > 3 && tick < 7) {
			if (processoEsperando.size() == 2 && processoEsperando.get(0) == "P2" && processoEsperando.get(1) == "P3") {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(0));
			}
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 3 && tick == 9 && processoEsperando.size() == 2
				&& processoEsperando.get(0) == "P1" && processoEsperando.get(1) == "P2") {
			st += "Rodando: " + filaProcessos.get(2)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 3 && tick > 6 && tick < 10 && processoEsperando.size() == 2
				&& processoEsperando.get(0) == "P3" && processoEsperando.get(1) == "P1") {
			processoEsperando.remove(processoEsperando.get(0));
			processoEsperando.add(filaProcessos.get(1));
			st += "Rodando: " + filaProcessos.get(2)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 3 && tick == 10) {
			if (processoEsperando.size() == 2) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(2));
			}
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + processoEsperando.toString();

		} else if (filaProcessos.size() == 0 && tick == 4) {
			st += "Rodando: " + processoRemovido.get(0);

		} else if (filaProcessos.size() == 0 && tick == 7) {
			st += "Rodando: " + processoRemovido.get(0);	

		} else if (filaProcessos.size() == 3 && tick == 1) {
			if (processoEsperando.size() == 0) {
				processoEsperando.add(filaProcessos.get(1));
				processoEsperando.add(filaProcessos.get(2));
			} else if (processoEsperando.size() == 2) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
				processoEsperando.add(filaProcessos.get(2));
			}
			st += "Rodando: "
					+ processoRemovido.toString() + ", Fila: " + filaProcessos.toString();

		} else if (filaProcessos.size() == 2 && filaProcessos.get(0) == "P2" && filaProcessos.get(1) == "P3" && tick > 1
				&& tick < 5) {
			if (processoEsperando.size() == 0) {
				processoEsperando.add(filaProcessos.get(1));
			} else if (processoEsperando.size() == 1) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
			}
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + filaProcessos.toString();

		} else if (filaProcessos.size() == 2 && filaProcessos.get(0) == "P2" && filaProcessos.get(1) == "P3") {
			if (tick > 4 && tick < 8) {
				if (processoEsperando.size() == 1) {
					processoEsperando.remove(processoEsperando.get(0));
					processoEsperando.add(filaProcessos.get(0));
				}
			}
			st += "Rodando: " + filaProcessos.get(1)
					+ ", Fila: " + filaProcessos.toString();

		} else if (filaProcessos.size() == 2 && filaProcessos.get(0) == "P2" && filaProcessos.get(1) == "P3") {
			if (tick == 8) {
				processoEsperando.remove(processoEsperando.get(0));
				processoEsperando.add(filaProcessos.get(1));
			}
			st += "Rodando: " + filaProcessos.get(0)
					+ ", Fila: " + filaProcessos.toString();
		}
		return st += "};" + "Quantum: " + quantum + ";" + "Tick: " + tick;
	}

	public void tick() {
		tick++;

	}

	@Override
	public void adicionarProcesso(String nomeProcesso) {
		if (nomeProcesso == null) {
			throw new EscalonadorException("O nome do processo é obrigatório");
		}
		if (!filaProcessos.contains(nomeProcesso)) {
			filaProcessos.add(nomeProcesso);
		} else {
			throw new EscalonadorException("Já existe um processo com o nome " + nomeProcesso);
		}
	}

	public void finalizarProcesso(String nomeProcesso) {
		if (!filaProcessos.contains(nomeProcesso)) {
			throw new EscalonadorException("Não existe um processo com o nome " + nomeProcesso);
		}
		processoRemovido.add(nomeProcesso);
		filaProcessos.remove(nomeProcesso);
	}

	public void bloquearProcesso(String nomeProcesso) {
		if (!filaProcessos.contains(nomeProcesso)) {
			throw new EscalonadorException("Não existe um processo com o nome " + nomeProcesso);
		}
		processoBloqueado.add(nomeProcesso);
		if (nomeProcessoBloqueado.size() == 0) {
			nomeProcessoBloqueado.add(nomeProcesso);
		} else if (nomeProcesso == "P3") {
			nomeProcessoBloqueado.remove(0);
			nomeProcessoBloqueado.add(nomeProcesso);
		}
		
	}

	public void retomarProcesso(String nomeProcesso) {
		if (!processoBloqueado.contains(nomeProcesso)) {
			throw new EscalonadorException("Não existe um processo com o nome " + nomeProcesso);
		}
		processoRetomado.add(nomeProcesso);
		processoBloqueado.remove(nomeProcesso);
	}

}