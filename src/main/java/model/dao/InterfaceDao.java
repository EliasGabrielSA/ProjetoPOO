package model.dao;

import java.util.List;
import model.Disco;

public interface InterfaceDao<T> {
    
    public abstract void incluir(T entidade) throws Exception;

    public abstract void editar(T entidade) throws Exception;

    public abstract void excluir(T entidade) throws Exception;

    public abstract T pesquisarPorNome(String nome) throws Exception;

    public abstract List<T> listar(String param) throws Exception;
    
    public abstract void discoSelecionado(Disco disco) throws Exception;
 
}
