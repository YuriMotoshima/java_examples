package com.example.models;
import java.util.ArrayList;
import java.util.List;

import com.example.exceptions.InvalidTipesRecived;

public abstract class Pessoa {
    protected Integer codIdentificacao;
    protected String nome;
    protected String tipo;
    protected String escolaridade;
    protected String instituicaoEnsino;
    private List<String> erros;
    
    /**
     * Classe Pessoa.
     *
     * @param codIdentificacao Identificador unico da pessoa.
     * @param nome Nome completo
     * @param tipo Classificação, se ["Estudando", "Funcionário","Prestador de Serviço","Visitante"]
     * @param escolaridade Nivel de ensino, se ["Não concluído","Ensino Médio","Ensino Básico","Graudação","Pós Graduação","Mestrado","Doutorado"]
     * @param instituicaoEnsino Nome da instituição de ensino.
     * @throws InvalidTipesRecived Se o tipo ou escolaridade forem inválidos
     */
    protected Pessoa(
        Integer codIdentificacao, 
        String nome, 
        String tipo, 
        String escolaridade,
        String instituicaoEnsino
    ) throws InvalidTipesRecived {
        this.codIdentificacao = codIdentificacao;
        this.nome = nome;
        this.tipo = tipo;
        this.escolaridade = escolaridade;
        this.instituicaoEnsino = instituicaoEnsino;
        this.erros = new ArrayList<>();

        // Executa as validações automaticamente
        validarDados();
    }
    
    /**
     * Valida os dados da pessoa.
     * @throws InvalidTipesRecived Se o tipo ou escolaridade forem inválidos
     */
    protected final void validarDados() throws InvalidTipesRecived {
        verificarTipo(this.tipo, this.erros);
        verificarEscolaridade(this.escolaridade, this.erros);
        if (!this.erros.isEmpty()){
            throw new InvalidTipesRecived(String.join("; ", this.erros));
        }
    }

    protected void verificarTipo(String tipo, List<String> erros){
        List<String> tipos = List.of("Estudante", "Funcionário", "Prestador de Serviço", "Visitante");

        if (!tipos.contains(tipo)) {
            erros.add("Verifique o valor passado em 'Tipo'.");
        }
    }

    protected void verificarEscolaridade(String escolaridade, List<String> erros){
        List<String> escolas = List.of("Não concluído", "Ensino Médio", "Ensino Básico", "Graudação", "Pós Graduação", "Mestrado", "Doutorado");

        if (!escolas.contains(escolaridade)) {
            erros.add("Verifique o valor passado em 'Escolaridade'.");
        }
    }

    /**
     * Retorna a lista de erros de validação.
     * @return Lista de strings com os erros de validação.
     */
    public List<String> getErros() {
        return new ArrayList<>(this.erros);
    }
}