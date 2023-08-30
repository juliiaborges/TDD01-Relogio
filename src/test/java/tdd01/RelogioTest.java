package tdd01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RelogioTest {

    @Test
    public void testarFormatoCerto() {
        Relogio relogio = new Relogio(16, 40, 20);
        assertTrue(relogio.formatoHorario());
    }

    @Test
    public void testarFormartoExtremidade() {
        Relogio relogio = new Relogio(23, 59, 59);
        assertTrue(relogio.formatoHorario());
    }

    @Test
    public void testarFormarioHoraErrado() {
        Relogio relogio = new Relogio(25, 20, 30);
        assertFalse(relogio.formatoHorario());
    }

    @Test
    public void testarFormarioMinutoErrado() {
        Relogio relogio = new Relogio(22, 62, 30);
        assertFalse(relogio.formatoHorario());
    }

    @Test
    public void testarFormarioSegundoErrado() {
        Relogio relogio = new Relogio(20, 10, 60);
        assertFalse(relogio.formatoHorario());
    }

    @Test
    public void testarReiniciamento() {
        Relogio relogio = new Relogio(15, 40, 20);
        relogio.reiniciar();
        assertEquals(0, relogio.getHoras());
        assertEquals(0, relogio.getMinutos());
        assertEquals(0, relogio.getSegundos());
    }

    @Test
    public void testMarcarTempo() {
        Relogio relogio = new Relogio(12, 0, 0);

        assertEquals("01:30:45", relogio.marcarTempo(12, 0, 0, 13, 30, 45));
        assertEquals("00:30:10", relogio.marcarTempo(23, 45, 10, 0, 15, 20));
        assertEquals("00:00:00", relogio.marcarTempo(9, 0, 0, 9, 0, 0));
    }

    @Test
    public void testarImprimirFormato24() {
        Relogio relogio = new Relogio(20, 15, 30);
        assertEquals("20:15:30", relogio.imprimir24h(true));
    }

    @Test
    public void testImprimirHoraFormatoAMPM() {
        Relogio relogio = new Relogio(10, 45, 30);
        assertEquals("10:45 AM", relogio.imprimirAMPM(false));
    }

    @Test
    public void testAtualizacaoHorario() {
        Relogio relogio = new Relogio(12, 0, 0);

        String horarioAtual = relogio.obterHorarioFormato24h();
        assertEquals("12:00:00", horarioAtual);

        relogio.atualizarHorario(15, 30, 45);
        assertEquals("15:30:45", relogio.obterHorarioFormato24h());
    }
}
