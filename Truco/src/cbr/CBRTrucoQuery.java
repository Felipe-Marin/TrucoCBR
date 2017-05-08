/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbr;

import jcolibri.cbrcore.CBRQuery;
import representation.CaseDescription;

/**
 *
 * @author Felipe
 */
public class CBRTrucoQuery {
    
    
    public static CBRQuery pedirEnvido(int nivelEnvido, Integer carta1, Integer carta2, Integer carta3){
        CBRQuery query = new CBRQuery();
        CaseDescription caseEnvido = new CaseDescription();
        caseEnvido.setRodada(1);
        caseEnvido.setNivelEnvido(nivelEnvido);
        caseEnvido.setEnvidoPediu(true);
        caseEnvido.setJ1Carta1(carta1);
        caseEnvido.setJ1Carta2(carta2);
        caseEnvido.setJ1Carta3(carta3);
        query.setDescription(caseEnvido);
        
        return query;
    }
    
    public static CBRQuery aceitarEnvido(int nivelEnvido, Integer carta1, Integer carta2, Integer carta3){
        CBRQuery query = new CBRQuery();
        CaseDescription caseEnvido = new CaseDescription();
        caseEnvido.setRodada(1);
        caseEnvido.setNivelEnvido(nivelEnvido);
        caseEnvido.setEnvidoAceito(true);
        caseEnvido.setJ1Carta1(carta1);
        caseEnvido.setJ1Carta2(carta2);
        caseEnvido.setJ1Carta3(carta3);
        //adicionar novas variaveis de aceita e nao aceita
        query.setDescription(caseEnvido);
        
        return query;
    }
    
    public static CBRQuery pedirFlor(int nivelFlor){
        CBRQuery query = new CBRQuery();
        CaseDescription caseFlor = new CaseDescription();
        
        query.setDescription(caseFlor);
        return query;
    }
    
    public static CBRQuery aceitarFlor(int nivelFlor){
        CBRQuery query = new CBRQuery();
        CaseDescription caseFlor = new CaseDescription();
        
        query.setDescription(caseFlor);
        return query;
    }
    
    public static CBRQuery pedirTruco(int nivelTruco, Integer carta1, Integer carta2, Integer carta3, Integer cartaadv1, Integer cartaadv2, Integer cartaadv3){
        CBRQuery query = new CBRQuery();
        CaseDescription caseTruco = new CaseDescription();
        caseTruco.setNivelTruco(nivelTruco);
        caseTruco.setTruco(true);
        caseTruco.setTrucoPediu(true);
        caseTruco.setJ1Carta1(carta1);
        caseTruco.setJ1Carta2(carta2);
        caseTruco.setJ1Carta3(carta3);
        caseTruco.setJ2Carta1(cartaadv1);
        caseTruco.setJ2Carta2(cartaadv2);
        caseTruco.setJ2Carta3(cartaadv3);
        query.setDescription(caseTruco);
        return query;
    }
    
    public static CBRQuery aceitarTruco(int nivelTruco, Integer carta1, Integer carta2, Integer carta3, Integer cartaadv1, Integer cartaadv2, Integer cartaadv3){
        CBRQuery query = new CBRQuery();
        CaseDescription caseTruco = new CaseDescription();
        caseTruco.setNivelTruco(nivelTruco);
        caseTruco.setTruco(true);
        caseTruco.setTrucoAceito(true);
        caseTruco.setJ1Carta1(carta1);
        caseTruco.setJ1Carta2(carta2);
        caseTruco.setJ1Carta3(carta3);
        caseTruco.setJ2Carta1(cartaadv1);
        caseTruco.setJ2Carta2(cartaadv2);
        caseTruco.setJ2Carta3(cartaadv3);
        query.setDescription(caseTruco);
        return query;
    }
    
    public static CBRQuery jogarCarta(Integer rodada, Integer carta1, Integer carta2, Integer carta3, Integer cartajogadv){
        CBRQuery query = new CBRQuery();
        CaseDescription caseCarta = new CaseDescription();
        caseCarta.setJ1Carta1(carta1);
        caseCarta.setJ1Carta2(carta2);
        caseCarta.setJ1Carta3(carta3);
        caseCarta.setRodada(rodada);
        caseCarta.setJ2CartaJogadaInt(cartajogadv);
        query.setDescription(caseCarta);
        return query;
    }
    
    
}
