/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbr;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.selection.SelectCases;
import representation.CaseDescription;
import representation.CaseResult;

/**
 *
 * @author Felipe
 */
public class CBRTruco {
    
    private final static String qualquerCarta = "Caso mais semelhante resultou em uma derrota, jogue a carta de menor valor.";
    
    public static String pedirTruco(NNConfig simconfig, CBRCaseBase casebase, Integer niveltruco, Integer carta1, Integer carta2, Integer carta3, Integer cartaAdv1, Integer cartaAdv2, Integer cartaAdv3){
        List<CBRQuery> querylist= new ArrayList<CBRQuery>();
        List<Integer[]> cartasjogador = new ArrayList<Integer[]>();
        cartasjogador.add(new Integer[]{carta1, carta2, carta3});
        cartasjogador.add(new Integer[]{carta1, carta3, carta2});
        cartasjogador.add(new Integer[]{carta2, carta1, carta3});
        cartasjogador.add(new Integer[]{carta2, carta2, carta3});
        cartasjogador.add(new Integer[]{carta3, carta1, carta2});
        cartasjogador.add(new Integer[]{carta3, carta2, carta1});
        List<Integer[]> cartasAdv = new ArrayList<Integer[]>();
        cartasAdv.add(new Integer[]{cartaAdv1, cartaAdv2, cartaAdv3});
        cartasAdv.add(new Integer[]{cartaAdv1, cartaAdv3, cartaAdv2});
        cartasAdv.add(new Integer[]{cartaAdv2, cartaAdv1, cartaAdv3});
        cartasAdv.add(new Integer[]{cartaAdv2, cartaAdv2, cartaAdv3});
        cartasAdv.add(new Integer[]{cartaAdv3, cartaAdv1, cartaAdv2});
        cartasAdv.add(new Integer[]{cartaAdv3, cartaAdv2, cartaAdv1});
        for(Integer[] combjog: cartasjogador){
            for(Integer[] combadv: cartasAdv){
                querylist.add(CBRTrucoQuery.pedirTruco(niveltruco, combjog[0], combjog[1], combjog[2], combadv[0], combadv[1], combadv[2]));
            }
        }
        double evalvalue = 0;
        CaseResult result = null;
        for(CBRQuery query : querylist){
            //System.out.println(evalvalue);
            //System.out.println(query);
            Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(
        	casebase.getCases(), query, simconfig);
            //System.out.println(eval);
            eval = SelectCases.selectTopKRR(eval, 1);
            for (RetrievalResult nse : eval){
                //System.out.println(nse.getEval());
                if(nse.getEval() > evalvalue){
                    evalvalue = nse.getEval();
                    //System.out.println(evalvalue);
                    result = (CaseResult) nse.get_case().getResult();
                }
            }
        }
        if(result != null){
            System.out.println(result.getGanhouMao());
            if(result.getGanhouMao())
                return "Pedir truco";
            else
                return "Não pedir truco";
        }
        return "Erro ao buscar caso";
    }
    
    public static String aceitarTruco(NNConfig simconfig, CBRCaseBase casebase, Integer niveltruco, Integer carta1, Integer carta2, Integer carta3, Integer cartaAdv1, Integer cartaAdv2, Integer cartaAdv3){
        List<CBRQuery> querylist= new ArrayList<CBRQuery>();
        List<Integer[]> cartasjogador = new ArrayList<Integer[]>();
        cartasjogador.add(new Integer[]{carta1, carta2, carta3});
        cartasjogador.add(new Integer[]{carta1, carta3, carta2});
        cartasjogador.add(new Integer[]{carta2, carta1, carta3});
        cartasjogador.add(new Integer[]{carta2, carta2, carta3});
        cartasjogador.add(new Integer[]{carta3, carta1, carta2});
        cartasjogador.add(new Integer[]{carta3, carta2, carta1});
        List<Integer[]> cartasAdv = new ArrayList<Integer[]>();
        cartasAdv.add(new Integer[]{cartaAdv1, cartaAdv2, cartaAdv3});
        cartasAdv.add(new Integer[]{cartaAdv1, cartaAdv3, cartaAdv2});
        cartasAdv.add(new Integer[]{cartaAdv2, cartaAdv1, cartaAdv3});
        cartasAdv.add(new Integer[]{cartaAdv2, cartaAdv2, cartaAdv3});
        cartasAdv.add(new Integer[]{cartaAdv3, cartaAdv1, cartaAdv2});
        cartasAdv.add(new Integer[]{cartaAdv3, cartaAdv2, cartaAdv1});
        for(Integer[] combjog: cartasjogador){
            for(Integer[] combadv: cartasAdv){
                querylist.add(CBRTrucoQuery.pedirTruco(niveltruco, combjog[0], combjog[1], combjog[2], combadv[0], combadv[1], combadv[2]));
            }
        }
        double evalvalue = 0;
        CaseResult result = null;
        for(CBRQuery query : querylist){
            Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(
        	casebase.getCases(), query, simconfig);
            eval = SelectCases.selectTopKRR(eval, 1);
            for (RetrievalResult nse : eval){
                if(nse.getEval() > evalvalue){
                    evalvalue = nse.getEval();
                    result = (CaseResult) nse.get_case().getResult();
                }
            }
        }
        if(result != null){
            System.out.println(result.getGanhouMao());
            if(result.getGanhouMao())
                return "Aceitar truco";
            else
                return "Não aceitar truco";
        }
        return "Erro ao buscar caso";
    }
    
    public static String pedirEnvido(NNConfig simconfig, CBRCaseBase casebase, Integer nivelenvido, Integer carta1, Integer carta2, Integer carta3){
        List<CBRQuery> querylist= new ArrayList<CBRQuery>();
        querylist.add(CBRTrucoQuery.pedirEnvido(nivelenvido, carta1, carta2, carta3));
        querylist.add(CBRTrucoQuery.pedirEnvido(nivelenvido, carta1, carta3, carta2));
        querylist.add(CBRTrucoQuery.pedirEnvido(nivelenvido, carta2, carta1, carta3));
        querylist.add(CBRTrucoQuery.pedirEnvido(nivelenvido, carta2, carta3, carta1));
        querylist.add(CBRTrucoQuery.pedirEnvido(nivelenvido, carta3, carta1, carta2));
        querylist.add(CBRTrucoQuery.pedirEnvido(nivelenvido, carta3, carta2, carta1));
        double evalvalue = 0;
        CaseDescription result = null;
        for(CBRQuery query : querylist){
            Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(
        	casebase.getCases(), query, simconfig);
            eval = SelectCases.selectTopKRR(eval, 1);
            for (RetrievalResult nse : eval){
                if(nse.getEval() > evalvalue){
                    evalvalue = nse.getEval();
                    result = (CaseDescription) nse.get_case().getDescription();

                }
            }
        }
        if(result != null){
            System.out.println(result.getEnvidoGanho());
            if(result.getEnvidoGanho())
                return "Pedir envido";
            else
                return "Não pedir envido";
        }
        return "Erro ao buscar caso";
    }
        
    public static String aceitarEnvido(NNConfig simconfig, CBRCaseBase casebase, Integer nivelenvido, Integer carta1, Integer carta2, Integer carta3){
        List<CBRQuery> querylist= new ArrayList<CBRQuery>();
        querylist.add(CBRTrucoQuery.aceitarEnvido(nivelenvido, carta1, carta2, carta3));
        querylist.add(CBRTrucoQuery.aceitarEnvido(nivelenvido, carta1, carta3, carta2));
        querylist.add(CBRTrucoQuery.aceitarEnvido(nivelenvido, carta2, carta1, carta3));
        querylist.add(CBRTrucoQuery.aceitarEnvido(nivelenvido, carta2, carta3, carta1));
        querylist.add(CBRTrucoQuery.aceitarEnvido(nivelenvido, carta3, carta1, carta2));
        querylist.add(CBRTrucoQuery.aceitarEnvido(nivelenvido, carta3, carta2, carta1));
        double evalvalue = 0;
        CaseDescription result = null;
        for(CBRQuery query : querylist){
            Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(
        	casebase.getCases(), query, simconfig);
            eval = SelectCases.selectTopKRR(eval, 1);
            for (RetrievalResult nse : eval){
                if(nse.getEval() > evalvalue){
                    evalvalue = nse.getEval();
                    result = (CaseDescription) nse.get_case().getDescription();

                }
            }
        }
        if(result != null){
            System.out.println(result.getEnvidoGanho());
            if(result.getEnvidoGanho())
                return "Aceitar envido";
            else
                return "Não aceitar envido";
        }
        return "Erro ao buscar caso";
    }
    
    public static String jogarCarta(NNConfig simconfig, CBRCaseBase casebase, Integer rodada, Integer carta1, Integer carta2, Integer carta3, Integer cartaadv){
        List<CBRQuery> querylist= new ArrayList<CBRQuery>();
        querylist.add(CBRTrucoQuery.jogarCarta(rodada, carta1, carta2, carta3, cartaadv));
        querylist.add(CBRTrucoQuery.jogarCarta(rodada, carta1, carta3, carta2, cartaadv));
        querylist.add(CBRTrucoQuery.jogarCarta(rodada, carta2, carta1, carta3, cartaadv));
        querylist.add(CBRTrucoQuery.jogarCarta(rodada, carta2, carta3, carta1, cartaadv));
        querylist.add(CBRTrucoQuery.jogarCarta(rodada, carta3, carta1, carta2, cartaadv));
        querylist.add(CBRTrucoQuery.jogarCarta(rodada, carta3, carta2, carta1, cartaadv));
        double evalvalue = 0;
        CaseDescription result = null;
        for(CBRQuery query : querylist){
            Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(
        	casebase.getCases(), query, simconfig);
            eval = SelectCases.selectTopKRR(eval, 1);
            for (RetrievalResult nse : eval){
                if(nse.getEval() > evalvalue){
                    evalvalue = nse.getEval();
                    CaseResult resultado = (CaseResult)nse.get_case().getResult();
                    if(resultado.getGanhouRodada())
                        result = (CaseDescription) nse.get_case().getDescription();
                }
            }
        }
        if(result != null){
            System.out.println(result.getJ1CartaJogadaInt());
            return String.valueOf(result.getJ1CartaJogadaInt());
            //return result.getJ1CartaJogada();
        }
        return qualquerCarta;
    }
    
    public static String closestCard(String carta1Valor, String carta1Naipe, String carta2Valor, String carta2Naipe, String carta3Valor, String carta3Naipe, String cartaJogada){
        if(cartaJogada.equals(qualquerCarta))
            return qualquerCarta;
        Integer cartaJogadaInt = Integer.parseInt(cartaJogada);
        Integer carta1 = CBRTrucoUtil.getCardValue(carta1Valor, carta1Naipe);
        Integer carta2 = CBRTrucoUtil.getCardValue(carta2Valor, carta2Naipe);
        Integer carta3 = CBRTrucoUtil.getCardValue(carta3Valor, carta3Naipe);
        List<AbstractMap.SimpleEntry> listaCartas = new ArrayList<AbstractMap.SimpleEntry>();
        if(carta1 != null){
            Integer prox1 = cartaJogadaInt - carta1;
            AbstractMap.SimpleEntry card1 = new AbstractMap.SimpleEntry<String, Integer>(carta1Valor + " de " + carta1Naipe, prox1);
            listaCartas.add(card1);
        }
        if(carta2 != null){
            Integer prox2 = cartaJogadaInt - carta2;
            AbstractMap.SimpleEntry card2 = new AbstractMap.SimpleEntry<String, Integer>(carta2Valor + " de " + carta2Naipe, prox2);
            listaCartas.add(card2);
        }
        if(carta3 != null){
            Integer prox3 = cartaJogadaInt - carta3;
            AbstractMap.SimpleEntry card3 = new AbstractMap.SimpleEntry<String, Integer>(carta3Valor + " de " + carta3Naipe, prox3);
            listaCartas.add(card3);
        }
        Integer cartaProxima = -50;
        String cartaProximaStr = "";
        for(AbstractMap.SimpleEntry<String, Integer> card : listaCartas){
            Integer value = card.getValue();
            System.out.println("Valor: " + value.toString());
            if(value <= 0){
                if(value > cartaProxima){
                    cartaProxima = value;
                    cartaProximaStr = card.getKey();
                }
            }
        }
        if(cartaProxima == -50){
            for(AbstractMap.SimpleEntry<String, Integer> card : listaCartas){
                if(card.getValue() > cartaProxima){
                    cartaProxima = card.getValue();
                    cartaProximaStr = card.getKey();
                }
            }
        }
        return cartaProximaStr;
    }
    
}
