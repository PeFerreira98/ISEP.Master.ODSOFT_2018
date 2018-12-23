package pt.isep.cms.turmas.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pt.isep.cms.turmas.client.TurmasService;
import pt.isep.cms.turmas.shared.Turma;
import pt.isep.cms.turmas.shared.TurmaDetails;

@SuppressWarnings("serial")
public class TurmasServiceImpl extends RemoteServiceServlet implements TurmasService
{

    private static final String[] turmasFirstNameData = new String[] { "Hollie", "Emerson", "Healy", "Brigitte",
            "Elba", "Claudio", "Dena", "Christina", "Gail", "Orville", "Rae", "Mildred", "Candice", "Louise", "Emilio",
            "Geneva", "Heriberto", "Bulrush", "Abigail", "Chad", "Terry", "Bell" };

    private final String[] turmasLastNameData = new String[] { "Voss", "Milton", "Colette", "Cobb", "Lockhart",
            "Engle", "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes", "Carson", "Kelchner", "Hutchinson",
            "Underwood", "Rush", "Bouchard", "Louis", "Andrews", "English", "Snedden" };

    private final String[] turmasEmailData = new String[] { "mark@example.com", "hollie@example.com",
            "boticario@example.com", "emerson@example.com", "healy@example.com", "brigitte@example.com",
            "elba@example.com", "claudio@example.com", "dena@example.com", "brasilsp@example.com", "parker@example.com",
            "derbvktqsr@example.com", "qetlyxxogg@example.com", "antenas_sul@example.com", "cblake@example.com",
            "gailh@example.com", "orville@example.com", "post_master@example.com", "rchilders@example.com",
            "buster@example.com", "user31065@example.com", "ftsgeolbx@example.com" };

    private EntityManagerFactory emfactory = null;
    private EntityManager entitymanager = null;

    public TurmasServiceImpl() {
        // initTurmas();
        // serialId = 0;

        this.emfactory = Persistence.createEntityManagerFactory("CMS");

        this.entitymanager = emfactory.createEntityManager();

        initPersistentTurmas();
    }

    private void initPersistentTurmas() {
        // We only do this if the database is empty...
        Query query = entitymanager.createQuery("Select COUNT(c) from Turma c");
        Long result = (Long) query.getSingleResult();

        if (result == 0) {
            this.entitymanager.getTransaction().begin();

            for (int i = 0; i < turmasFirstNameData.length && i < turmasLastNameData.length
                    && i < turmasEmailData.length; ++i) {

                Turma turma = new Turma(i, turmasFirstNameData[i], turmasLastNameData[i],
                        turmasEmailData[i]);
                this.entitymanager.persist(turma);
            }

            this.entitymanager.getTransaction().commit();
        }
    }

    public Turma addTurma(Turma turma) {
        // Add the new turma to the database
        this.entitymanager.getTransaction().begin();
        this.entitymanager.persist(turma);
        this.entitymanager.getTransaction().commit();

        return turma;
    }

    public Turma updateTurma(Turma turma) {
        // Update the turma in the database
        this.entitymanager.getTransaction().begin();
        this.entitymanager.merge(turma);
        this.entitymanager.getTransaction().commit();

        return turma;
    }

    public Boolean deleteTurma(int id) {
        // Remove the turma in the database
        this.entitymanager.getTransaction().begin();
        Turma turma = entitymanager.find(Turma.class, id);
        entitymanager.remove(turma);
        this.entitymanager.getTransaction().commit();

        return true;
    }

    public ArrayList<TurmaDetails> deleteTurmas(ArrayList<Integer> ids) {
        for (int i = 0; i < ids.size(); ++i) {
            deleteTurma(ids.get(i));
        }

        return getTurmaDetails();
    }

    public ArrayList<TurmaDetails> getTurmaDetails() {
        ArrayList<TurmaDetails> turmaDetails = new ArrayList<TurmaDetails>();

        Query query = entitymanager.createQuery("Select c from Turma c");

        @SuppressWarnings("unchecked")
        List<Turma> list = query.getResultList();

        for (Turma turma : list) {
            turmaDetails.add(turma.getLightWeightTurma());
        }

        return turmaDetails;
    }

    public Turma getTurma(int id) {
        Turma turma = entitymanager.find(Turma.class, id);

        return turma;
    }

    public Turma getTurma(String name) {
        Query query = entitymanager.createQuery("Select c from Turma c where c.name like '" + name + "'");

        @SuppressWarnings("unchecked")
        Turma turma = (Turma)query.getSingleResult();

        return turma;
    }

    public int getTurmaCount(Turma turma) {
        Query query = entitymanager.createQuery("Select COUNT(c) from Student c where c.turmaid = " + turma.getId());
        return (int) query.getSingleResult();
    }
}
