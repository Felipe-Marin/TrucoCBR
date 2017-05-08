/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.CaseBaseFilter;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import representation.CaseDescription;
import representation.CaseResult;

/**
 *
 * @author Felipe
 */
public class CBRTrucoTest {
    public static String testPedirTruco(NNConfig simconfig, CBRCaseBase casebase){
        Integer numAcertos = 0;
        Integer numTotal = 0;
        List<CBRCase> caselist = new ArrayList(casebase.getCases());
        List<CBRCase> caseTrucoList = new ArrayList();
        for(CBRCase caseX : caselist){
            CaseDescription desc = (CaseDescription) caseX.getDescription();
            if(desc.getTruco() == true){
                caseTrucoList.add(caseX);
            }
        }
        for(CBRCase caseX : caseTrucoList){
            numTotal++;
            List<CBRCase> listaCaso = new ArrayList();
            listaCaso.add(caseX);
            casebase.forgetCases(listaCaso);
            CaseDescription desc = (CaseDescription) caseX.getDescription();
            CaseResult result = (CaseResult) caseX.getResult();
            String retorno = CBRTruco.pedirTruco(simconfig, casebase, desc.getNivelTruco(), desc.getJ1Carta1(), desc.getJ1Carta2(), desc.getJ1Carta3(), desc.getJ2Carta1(), desc.getJ2Carta2(), desc.getJ2Carta3());
            if((retorno.equals("Pedir truco") && result.getGanhouMao()) || (retorno.equals("Não pedir truco") && !result.getGanhouMao())){
                numAcertos++;
            }
            casebase.learnCases(listaCaso);
        }
        Double porcentagem = numAcertos.doubleValue()/numTotal.doubleValue() * 100;
        return "Pedir Truco: " + numAcertos.toString() + " acertos em um total de " + numTotal.toString() + " testados. (" + porcentagem.toString() + "%).";
    }
    
        public static String testAceitarTruco(NNConfig simconfig, CBRCaseBase casebase){
        Integer numAcertos = 0;
        Integer numTotal = 0;
        List<CBRCase> caselist = new ArrayList(casebase.getCases());
        List<CBRCase> caseTrucoList = new ArrayList();
        for(CBRCase caseX : caselist){
            CaseDescription desc = (CaseDescription) caseX.getDescription();
            if(desc.getTruco() == true){
                caseTrucoList.add(caseX);
            }
        }
        for(CBRCase caseX : caseTrucoList){
            numTotal++;
            List<CBRCase> listaCaso = new ArrayList();
            listaCaso.add(caseX);
            casebase.forgetCases(listaCaso);
            CaseDescription desc = (CaseDescription) caseX.getDescription();
            CaseResult result = (CaseResult) caseX.getResult();
            String retorno = CBRTruco.aceitarTruco(simconfig, casebase, desc.getNivelTruco(), desc.getJ1Carta1(), desc.getJ1Carta2(), desc.getJ1Carta3(), desc.getJ2Carta1(), desc.getJ2Carta2(), desc.getJ2Carta3());
            if((retorno.equals("Aceitar truco") && result.getGanhouMao()) || (retorno.equals("Não aceitar truco") && !result.getGanhouMao())){
                numAcertos++;
            }
            casebase.learnCases(listaCaso);
        }
        Double porcentagem = numAcertos.doubleValue()/numTotal.doubleValue() * 100;
        return "Aceitar Truco: " + numAcertos.toString() + " acertos em um total de " + numTotal.toString() + " testados. (" + porcentagem.toString() + "%).";
    }

    public static String testPedirEnvido(NNConfig simconfig, CBRCaseBase casebase){
        Integer numAcertos = 0;
        Integer numTotal = 0;
        List<CBRCase> caselist = new ArrayList(casebase.getCases());
        List<CBRCase> caseEnvidoList = new ArrayList();
        for(CBRCase caseX : caselist){
            CaseDescription desc = (CaseDescription) caseX.getDescription();
            if(desc.getEnvido() == true){
                caseEnvidoList.add(caseX);
            }
        }
        for(CBRCase caseX : caseEnvidoList){
            numTotal++;
            List<CBRCase> listaCaso = new ArrayList();
            listaCaso.add(caseX);
            casebase.forgetCases(listaCaso);
            CaseDescription desc = (CaseDescription) caseX.getDescription();
            String retorno = CBRTruco.pedirEnvido(simconfig, casebase, desc.getNivelEnvido(), desc.getJ1Carta1(), desc.getJ1Carta2(), desc.getJ1Carta3());
            if((retorno.equals("Pedir envido") && desc.getEnvidoGanho()) || (retorno.equals("Não pedir envido") && !desc.getEnvidoGanho())){
                numAcertos++;
            }
            casebase.learnCases(listaCaso);
        }
        Double porcentagem = numAcertos.doubleValue()/numTotal.doubleValue() * 100;
        return "Pedir Envido: " + numAcertos.toString() + " acertos em um total de " + numTotal.toString() + " testados. (" + porcentagem.toString() + "%).";
    }
    
    public static String testAceitarEnvido(NNConfig simconfig, CBRCaseBase casebase){
        Integer numAcertos = 0;
        Integer numTotal = 0;
        List<CBRCase> caselist = new ArrayList(casebase.getCases());
        List<CBRCase> caseEnvidoList = new ArrayList();
        for(CBRCase caseX : caselist){
            CaseDescription desc = (CaseDescription) caseX.getDescription();
            if(desc.getEnvido() == true){
                caseEnvidoList.add(caseX);
            }
        }
        for(CBRCase caseX : caseEnvidoList){
            numTotal++;
            List<CBRCase> listaCaso = new ArrayList();
            listaCaso.add(caseX);
            casebase.forgetCases(listaCaso);
            CaseDescription desc = (CaseDescription) caseX.getDescription();
            String retorno = CBRTruco.aceitarEnvido(simconfig, casebase, desc.getNivelEnvido(), desc.getJ1Carta1(), desc.getJ1Carta2(), desc.getJ1Carta3());
            if((retorno.equals("Aceitar envido") && desc.getEnvidoGanho()) || (retorno.equals("Não aceitar envido") && !desc.getEnvidoGanho())){
                numAcertos++;
            }
            casebase.learnCases(listaCaso);
        }
        Double porcentagem = numAcertos.doubleValue()/numTotal.doubleValue() * 100;
        return "Aceitar Envido: " + numAcertos.toString() + " acertos em um total de " + numTotal.toString() + " testados. (" + porcentagem.toString() + "%).";
    }
    
}
