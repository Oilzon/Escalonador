package br.ufpb.dcx.aps.escalonador;

import static br.ufpb.dcx.aps.escalonador.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import command.AdicionarProcesso;
import command.AdicionarProcessoTempoFixo;
import command.Tick;

public class FachadaEscalonadorFifoTest {

	private FachadaEscalonador fachada;

	@BeforeEach
	public void inicializar() {
		fachada = new FachadaEscalonador(TipoEscalonador.Fifo);
	}

	@Test
	public void t01_statusAposCriacao() {
		checaStatus(fachada, TipoEscalonador.Fifo, 0, 0);
	}

	@Test
	public void t02_avancarTempo() {
		fachada.executar(new Tick());
		checaStatus(fachada, TipoEscalonador.Fifo, 0, 1);
	}

	@Test
	public void t03_processoTerminaPorSiSo() {
		fachada.executar(new AdicionarProcessoTempoFixo("P1", 2));
		checaStatusFila(fachada, TipoEscalonador.Fifo, 0, 0, "P1");

		fachada.executar(new Tick());
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 1, "P1");

		fachada.executar(new Tick());
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 2, "P1");

		// Acaba a duração do processo e libera a CPU
		fachada.executar(new Tick());
		checaStatus(fachada, TipoEscalonador.Fifo, 0, 3);
	}

	@Test
	public void t04_doisProcessosIniciaPeloPrimeiro() {
		fachada.executar(new AdicionarProcessoTempoFixo("P1", 3));
		fachada.executar(new AdicionarProcessoTempoFixo("P2", 2));

		fachada.executar(new Tick());
		checaStatusRodandoFila(fachada, TipoEscalonador.Fifo, 0, 1, "P1", "P2");

		fachada.executar(new Tick());
		checaStatusRodandoFila(fachada, TipoEscalonador.Fifo, 0, 2, "P1", "P2");

		fachada.executar(new Tick());
		checaStatusRodandoFila(fachada, TipoEscalonador.Fifo, 0, 3, "P1", "P2");

		fachada.executar(new Tick());
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 4, "P2");

		fachada.executar(new Tick());
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 5, "P2");

		fachada.executar(new Tick());
		checaStatus(fachada, TipoEscalonador.Fifo, 0, 6);
	}

	@Test
	public void t05_tresProcessosDesempatePelaOrdemDeAdicao() {
		fachada.executar(new AdicionarProcessoTempoFixo("P1", 3));
		fachada.executar(new AdicionarProcessoTempoFixo("P2", 2));
		fachada.executar(new AdicionarProcessoTempoFixo("P3", 3));

		fachada.executar(new Tick());
		checaStatusRodandoFila(fachada, TipoEscalonador.Fifo, 0, 1, "P1", "P2", "P3");

		ticks(fachada, 3);
		checaStatusRodandoFila(fachada, TipoEscalonador.Fifo, 0, 4, "P2", "P3");

		ticks(fachada, 2);
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 6, "P3");

		ticks(fachada, 3);
		checaStatus(fachada, TipoEscalonador.Fifo, 0, 9);
	}

	@Test
	public void t06_tresProcessosInicioDiferente() {
		fachada.executar(new AdicionarProcessoTempoFixo("P1", 2));

		fachada.executar(new Tick());
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 1, "P1");

		fachada.executar(new AdicionarProcessoTempoFixo("P2", 3));
		fachada.executar(new AdicionarProcessoTempoFixo("P3", 2));

		fachada.executar(new Tick());
		checaStatusRodandoFila(fachada, TipoEscalonador.Fifo, 0, 2, "P1", "P2", "P3");

		fachada.executar(new Tick());
		checaStatusRodandoFila(fachada, TipoEscalonador.Fifo, 0, 3, "P2", "P3");

		ticks(fachada, 3);
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 6, "P3");

		ticks(fachada, 2);
		checaStatus(fachada, TipoEscalonador.Fifo, 0, 8);
	}

	@Test
	public void t07_tresProcessosInicioDiferente() {
		fachada.executar(new AdicionarProcessoTempoFixo("P1", 2));

		fachada.executar(new Tick());
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 1, "P1");

		fachada.executar(new AdicionarProcessoTempoFixo("P2", 3));

		fachada.executar(new Tick());
		checaStatusRodandoFila(fachada, TipoEscalonador.Fifo, 0, 2, "P1", "P2");

		fachada.executar(new AdicionarProcessoTempoFixo("P3", 2));

		fachada.executar(new Tick());
		checaStatusRodandoFila(fachada, TipoEscalonador.Fifo, 0, 3, "P2", "P3");

		ticks(fachada, 3);
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 6, "P3");

		ticks(fachada, 2);
		checaStatus(fachada, TipoEscalonador.Fifo, 0, 8);
	}

	@Test
	public void t08_tresProcessosAdicionarMenorNoMeio() {
		fachada.executar(new AdicionarProcessoTempoFixo("P1", 2));

		fachada.executar(new Tick());
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 1, "P1");

		fachada.executar(new AdicionarProcessoTempoFixo("P2", 1));

		fachada.executar(new Tick());
		checaStatusRodandoFila(fachada, TipoEscalonador.Fifo, 0, 2, "P1", "P2");

		fachada.executar(new AdicionarProcessoTempoFixo("P3", 2));

		fachada.executar(new Tick());
		checaStatusRodandoFila(fachada, TipoEscalonador.Fifo, 0, 3, "P2", "P3");

		fachada.executar(new Tick());
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 4, "P3");

		ticks(fachada, 2);
		checaStatus(fachada, TipoEscalonador.Fifo, 0, 6);
	}

	@Test
	public void t09_intervaloEntreProcessos() {
		fachada.executar(new AdicionarProcessoTempoFixo("P1", 3));

		fachada.executar(new Tick());
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 1, "P1");

		ticks(fachada, 6);
		checaStatus(fachada, TipoEscalonador.Fifo, 0, 7);

		fachada.executar(new AdicionarProcessoTempoFixo("P2", 2));
		checaStatusFila(fachada, TipoEscalonador.Fifo, 0, 7, "P2");

		fachada.executar(new Tick());
		checaStatusRodando(fachada, TipoEscalonador.Fifo, 0, 8, "P2");

		ticks(fachada, 3);
		checaStatus(fachada, TipoEscalonador.Fifo, 0, 11);
	}

	@Test
	public void t10_validacoes() {
		fachada.executar(new AdicionarProcessoTempoFixo("P", 1));
		assertThrows(EscalonadorException.class, () -> fachada.executar(new AdicionarProcessoTempoFixo("P", 2)),
				"Já existe um processo com o nome P");

		assertThrows(EscalonadorException.class, () -> fachada.executar(new AdicionarProcessoTempoFixo(null, 1)),
				"O nome do processo é obrigatório");

		assertThrows(EscalonadorException.class, () -> fachada.executar(new AdicionarProcessoTempoFixo("Q", 0)),
				"A duração do processo deve ser maior que zero");

		assertThrows(EscalonadorException.class, () -> fachada.executar(new AdicionarProcessoTempoFixo("Q", -1)),
				"A duração do processo deve ser maior que zero");

		assertThrows(EscalonadorException.class, () -> fachada.executar(new AdicionarProcesso("P")),
				"O Escalonador Fifo exige que todos os processos tenham uma duração definida na adição");

		assertThrows(EscalonadorException.class, () -> fachada.executar(new AdicionarProcesso("P", 2)),
				"O Escalonador Fifo exige que todos os processos tenham uma duração definida na adição");

	}
}