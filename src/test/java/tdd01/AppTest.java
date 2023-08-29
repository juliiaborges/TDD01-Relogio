package tdd01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AppTest {
    //---------------------------------------------------------------------------//

    // Testar formato da hora //
    @Test// - Verificar se a hora irá dar certo com horários válidos
    public void testarFormatoCerto(){
        Relogio relogio = new Relogio(16,40,20);
        assertTrue(relogio.formatoHorario());
     }// - Verificar se a hora irá dar certo com horários nas extremidades
     public void testarFormartoExtremidade(){
        Relogio relogio = new Relogio(23,59,59);
        assertTrue(relogio.formatoHorario());
     }
     @Test// - Verificar se a hora irá dar errado com a hora com formato errado
     public void testarFormarioHoraErrado(){
        Relogio relogio = new Relogio(25,20,30);
        assertFalse(relogio.formatoHoraio());
     }
     @Test// - Verificar se a hora irá dar errado com o minuto com formato errado
      public void testarFormarioMinutoErrado(){
        Relogio relogio = new Relogio(22,62,30);
        assertFalse(relogio.formatoHoraio());
     }
        @Test// - Verificar se a hora irá dar errado com o segundo com formato errado
      public void testarFormarioSegundoErrado(){
        Relogio relogio = new Relogio(20,10,60);
        assertFalse(relogio.formatoHoraio());
     }

     //------------------------------------------------------------------------------//

     //Testar pra ver se quando reiniciar meia noite irá para 00h
     @Test
     public void testarReiniciamento(){
        Relogio relogio = new Relogio(15,40,20);
        relogio.reiniciar();
        assertEquals(0,relogio.getHoras());
        assertEquals(0,relogio.getMinutos());
        assertEquals(0,relogio.getSegundos());
     }
     //Fim do teste do método reiniciar 

     //Testar se o tempo indicado pelo usário para conometrar irá constar corretamente o mesmo valor na saída
     @Test
     public void testarCronometro(){
        Relogio relogio = new Relogio(13,12,10);
        relogio.marcarTempo(0,0,30);
        assertEquals("00:00:30", relogio.tempoFinalAtingido());
     }
     //Fim do teste do método cronometrar

     //Testar Formato 24h
     @Test
     public void testarImprimirFormato24(){
        Relogio relogio = new Relogio(20,15,30);
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

        // Simula o usuário pedindo o horário
        String horarioAtual = relogio.obterHorarioFormato24h();
        assertEquals("12:00:00", horarioAtual);

        // Simula a atualização manual do horário
        relogio.atualizarHorario(15, 30, 45);
        assertEquals("15:30:45", relogio.obterHorarioFormato24h());
    }
    }

