package pt.isep.cms.turmas.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.turmas.client.TurmasService;
import pt.isep.cms.turmas.shared.Turma;
import pt.isep.cms.turmas.shared.TurmaDetails;

@SuppressWarnings("serial")
public class TurmasServiceImpl extends RemoteServiceServlet implements TurmasService {

    private static final String[] turmasFirstNameData = new String[] { "Hollie", "Emerson", "Healy", "Brigitte", "Elba",
            "Claudio", "Dena", "Christina", "Gail", "Orville", "Rae", "Mildred", "Candice", "Louise", "Emilio",
            "Geneva", "Heriberto", "Bulrush", "Abigail", "Chad", "Terry", "Bell" };

    private final String[] turmasLastNameData = new String[] { "Voss", "Milton", "Colette", "Cobb", "Lockhart", "Engle",
            "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes", "Carson", "Kelchner", "Hutchinson",
            "Underwood", "Rush", "Bouchard", "Louis", "Andrews", "English", "Snedden" };

    private final String[] turmasEmailData = new String[] { "mark@example.com", "hollie@example.com",
            "boticario@example.com", "emerson@example.com", "healy@example.com", "brigitte@example.com",
            "elba@example.com", "claudio@example.com", "dena@example.com", "brasilsp@example.com", "parker@example.com",
            "derbvktqsr@example.com", "qetlyxxogg@example.com", "antenas_sul@example.com", "cblake@example.com",
            "gailh@example.com", "orville@example.com", "post_master@example.com", "rchilders@example.com",
            "buster@example.com", "user31065@example.com", "ftsgeolbx@example.com" };

    private final HashMap<String, Turma> turmas = new HashMap<String, Turma>();
    private int serialId;

    public TurmasServiceImpl() {
        initTurmas();
        serialId = 0;
    }

    private void initTurmas() {
        // TODO: Create a real UID for each turma
        //
        for (int i = 0; i < turmasFirstNameData.length && i < turmasLastNameData.length
                && i < turmasEmailData.length; ++i) {
            Turma turma = new Turma(String.valueOf(i), turmasFirstNameData[i], turmasLastNameData[i],
                    turmasEmailData[i]);
            addTurma(turma);
        }
    }

    public Turma addTurma(Turma turma) {
        turma.setId(String.valueOf(serialId++));
        turmas.put(turma.getId(), turma);
        return turma;
    }

    public Turma updateTurma(Turma turma) {
        String lid = turma.getId();
        turmas.remove(turma.getId());
        turmas.put(turma.getId(), turma);
        return turma;
    }

    public Boolean deleteTurma(String id) {
        turmas.remove(id);
        return true;
    }

    public ArrayList<TurmaDetails> deleteTurmas(ArrayList<String> ids) {

        for (int i = 0; i < ids.size(); ++i) {
            deleteTurma(ids.get(i));
        }

        return getTurmaDetails();
    }

    public ArrayList<TurmaDetails> getTurmaDetails() {
        ArrayList<TurmaDetails> turmaDetails = new ArrayList<TurmaDetails>();

        Iterator<String> it = turmas.keySet().iterator();
        while (it.hasNext()) {
            Turma turma = turmas.get(it.next());
            turmaDetails.add(turma.getLightWeightTurma());
        }

        return turmaDetails;
    }

    public Turma getTurma(String id) {
        return turmas.get(id);
    }
}
