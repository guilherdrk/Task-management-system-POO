package java.com.seuprojeto.tasks.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarefaService {

    public List<Tarefa> tarefaList;
    public long proximoId = 1;

    public TarefaService(){
        tarefaList = new ArrayList<>();

    }

    public void adicionarTarefa(Tarefa tarefa){
        tarefa.setId(proximoId++);
        this.tarefaList.add(tarefa);
        System.out.println("Tarefa adicionada: " + tarefa.getTitulo());
    }
    public List<Tarefa> listarTarefas(){
        return new ArrayList<>(this.tarefaList);
    }
    public boolean marcarComoConcluida(long id){
        Optional<Tarefa> tarefaOptional = tarefaList.stream()
                .filter(tarefa -> tarefa.getId() == id).findFirst();
        if(tarefaOptional.isPresent()){
            Tarefa tarefa = tarefaOptional.get();
            tarefa.setConcluida(true);
            System.out.println("Tarefa" + tarefa.getTitulo() + " marcada como concluida.");
            return true;
        }
        System.out.println("Tarefa com ID " + id + " não encontrada para marcar como concluida");
        return false;
    }
    public boolean removerTarefa(int id){
        boolean removido = tarefaList.removeIf(t -> t.getId() == id);
        if (removido){
            System.out.println("Tarefa com ID " + id + " removida.");
            return true;
        }else {
            System.out.println("Tareda com ID " + id + " não encontrada para remover");
            return false;
        }
    }
    public boolean editarTarefa(Tarefa tarefa){
        int index = -1;
        for (int i = 0; i < tarefaList.size(); i++){
            if (tarefaList.get(i).getId() == tarefa.getId()){
                index = i;
                break;
            }
        }

        if(index != -1){
            tarefaList.set(index, tarefa);
            System.out.println("Tarefa " + tarefa.getTitulo() + " (ID: " + tarefa.getId() + ") editada com sucesso");
            return true;
        }else {
            System.out.println("Tarefa com ID " + tarefa.getId() + " não encontrada para edição");
            return false;
        }
    }
    public  Optional<Tarefa> buscarTarefaPorId(long id){
        return tarefaDAO.buscarPorId(id);
    }
}
