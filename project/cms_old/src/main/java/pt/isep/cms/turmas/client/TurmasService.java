package pt.isep.cms.turmas.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.turmas.shared.Turma;
import pt.isep.cms.turmas.shared.TurmaDetails;

import java.util.ArrayList;

@RemoteServiceRelativePath("turmasService")
public interface TurmasService extends RemoteService {

    Turma addTurma(Turma turma);

    Boolean deleteTurma(int id);

    ArrayList<TurmaDetails> deleteTurmas(ArrayList<Integer> ids);

    ArrayList<TurmaDetails> getTurmaDetails();

    Turma getTurma(int id);

    Turma updateTurma(Turma turma);
}
