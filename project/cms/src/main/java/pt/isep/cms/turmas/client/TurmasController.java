package pt.isep.cms.turmas.client;

import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.turmas.client.event.AddTurmaEvent;
import pt.isep.cms.turmas.client.event.AddTurmaEventHandler;
import pt.isep.cms.turmas.client.event.EditTurmaCancelledEvent;
import pt.isep.cms.turmas.client.event.EditTurmaCancelledEventHandler;
import pt.isep.cms.turmas.client.event.EditTurmaEvent;
import pt.isep.cms.turmas.client.event.EditTurmaEventHandler;
import pt.isep.cms.turmas.client.event.TurmaUpdatedEvent;
import pt.isep.cms.turmas.client.event.TurmaUpdatedEventHandler;
import pt.isep.cms.turmas.client.presenter.TurmasPresenter;
import pt.isep.cms.turmas.client.presenter.Presenter;
import pt.isep.cms.turmas.client.presenter.EditTurmaPresenter;
import pt.isep.cms.turmas.client.view.TurmasView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.turmas.client.view.TurmasDialog;

public class TurmasController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
    private final HandlerManager eventBus;
    private final TurmasServiceAsync rpcService;
    private HasWidgets container;

    public static interface CwConstants extends Constants {
    }

    /**
     * An instance of the constants.
     */
    private final CwConstants constants;
    private final ShowcaseConstants globalConstants;

    public TurmasController(TurmasServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        this.constants = constants;
        this.globalConstants = constants;

        bind();
    }

    private void bind() {
        // (ATB) No History at this level
        // History.addValueChangeHandler(this);

        eventBus.addHandler(AddTurmaEvent.TYPE, new AddTurmaEventHandler() {
            public void onAddTurma(AddTurmaEvent event) {
                doAddNewTurma();
            }
        });

        eventBus.addHandler(EditTurmaEvent.TYPE, new EditTurmaEventHandler() {
            public void onEditTurma(EditTurmaEvent event) {
                doEditTurma(event.getId());
            }
        });

        eventBus.addHandler(EditTurmaCancelledEvent.TYPE, new EditTurmaCancelledEventHandler() {
            public void onEditTurmaCancelled(EditTurmaCancelledEvent event) {
                doEditTurmaCancelled();
            }
        });

        eventBus.addHandler(TurmaUpdatedEvent.TYPE, new TurmaUpdatedEventHandler() {
            public void onTurmaUpdated(TurmaUpdatedEvent event) {
                doTurmaUpdated();
            }
        });
    }

    private void doAddNewTurma() {
        // Lets use the presenter to display a dialog...
        Presenter presenter = new EditTurmaPresenter(rpcService, eventBus,
                new TurmasDialog(globalConstants, TurmasDialog.Type.ADD));
        presenter.go(container);

    }

    private void doEditTurma(String id) {
        Presenter presenter = new EditTurmaPresenter(rpcService, eventBus,
                new TurmasDialog(globalConstants, TurmasDialog.Type.UPDATE), id);
        presenter.go(container);
    }

    private void doEditTurmaCancelled() {
        // Nothing to update...
    }

    private void doTurmaUpdated() {
        // (ATB) Update the list of Turmas...
        Presenter presenter = new TurmasPresenter(rpcService, eventBus, new TurmasView());
        presenter.go(container);
    }

    public void go(final HasWidgets container) {
        this.container = container;

        Presenter presenter = new TurmasPresenter(rpcService, eventBus, new TurmasView());
        presenter.go(container);
    }

}
