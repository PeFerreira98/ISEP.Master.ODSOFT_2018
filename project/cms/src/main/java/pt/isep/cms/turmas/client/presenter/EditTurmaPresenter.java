package pt.isep.cms.turmas.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.Window;
import pt.isep.cms.turmas.client.TurmasServiceAsync;
import pt.isep.cms.turmas.client.event.TurmaUpdatedEvent;
import pt.isep.cms.turmas.client.event.EditTurmaCancelledEvent;
import pt.isep.cms.turmas.shared.Turma;

public class EditTurmaPresenter implements Presenter {
    public interface Display {
        HasClickHandlers getSaveButton();

        HasClickHandlers getCancelButton();

        HasValue<String> getFirstName();

        HasValue<String> getLastName();

        HasValue<String> getEmailAddress();

        void show();

        void hide();
    }

    private Turma turma;
    private final TurmasServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public EditTurmaPresenter(TurmasServiceAsync rpcService, HandlerManager eventBus, Display display) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.turma = new Turma();
        this.display = display;
        bind();
    }

    public EditTurmaPresenter(TurmasServiceAsync rpcService, HandlerManager eventBus, Display display, int id) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = display;
        bind();

        rpcService.getTurma(id, new AsyncCallback<Turma>() {
            public void onSuccess(Turma result) {
                turma = result;
                EditTurmaPresenter.this.display.getFirstName().setValue(turma.getFirstName());
                EditTurmaPresenter.this.display.getLastName().setValue(turma.getLastName());
                EditTurmaPresenter.this.display.getEmailAddress().setValue(turma.getEmailAddress());
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error retrieving turma");
            }
        });

    }

    public void bind() {
        this.display.getSaveButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                doSave();
                display.hide();
            }
        });

        this.display.getCancelButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                display.hide();
                eventBus.fireEvent(new EditTurmaCancelledEvent());
            }
        });
    }

    public void go(final HasWidgets container) {
        display.show();
    }

    private void doSave() {
        turma.setFirstName(display.getFirstName().getValue());
        turma.setLastName(display.getLastName().getValue());
        turma.setEmailAddress(display.getEmailAddress().getValue());

        if (turma.getId() == 0) {
            // Adding new turma
            rpcService.addTurma(turma, new AsyncCallback<Turma>() {
                public void onSuccess(Turma result) {
                    eventBus.fireEvent(new TurmaUpdatedEvent(result));
                }

                public void onFailure(Throwable caught) {
                    Window.alert("Error adding turma");
                }
            });
        } else {
            // updating existing turma
            rpcService.updateTurma(turma, new AsyncCallback<Turma>() {
                public void onSuccess(Turma result) {
                    eventBus.fireEvent(new TurmaUpdatedEvent(result));
                }

                public void onFailure(Throwable caught) {
                    Window.alert("Error updating turma");
                }
            });
        }
    }

}
