package pt.isep.cms.turmas.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.turmas.shared.Turma;
import pt.isep.cms.turmas.shared.TurmaDetails;

import java.util.ArrayList;

public interface TurmasServiceAsync {

    public void addTurma(Turma turma, AsyncCallback<Turma> callback);

    public void deleteTurma(String id, AsyncCallback<Boolean> callback);

    public void deleteTurmas(ArrayList<String> ids, AsyncCallback<ArrayList<TurmaDetails>> callback);

    public void getTurmaDetails(AsyncCallback<ArrayList<TurmaDetails>> callback);

    public void getTurma(String id, AsyncCallback<Turma> callback);

    public void updateTurma(Turma turma, AsyncCallback<Turma> callback);
}
