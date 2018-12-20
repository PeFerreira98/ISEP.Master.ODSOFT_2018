package pt.isep.cms.turmas.client.presenter;

import pt.isep.cms.turmas.client.TurmasServiceAsync;
import pt.isep.cms.turmas.client.event.AddTurmaEvent;
import pt.isep.cms.turmas.client.event.EditTurmaEvent;
import pt.isep.cms.turmas.shared.TurmaDetails;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

public class TurmasPresenter implements Presenter {

    private List<TurmaDetails> turmaDetails;

    public interface Display {
        HasClickHandlers getAddButton();

        HasClickHandlers getDeleteButton();

        HasClickHandlers getList();

        void setData(List<String> data);

        int getClickedRow(ClickEvent event);

        List<Integer> getSelectedRows();

        Widget asWidget();
    }

    private final TurmasServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public TurmasPresenter(TurmasServiceAsync rpcService, HandlerManager eventBus, Display view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;
    }

    public void bind() {
        display.getAddButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new AddTurmaEvent());
            }
        });

        display.getDeleteButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                deleteSelectedTurmas();
            }
        });

        display.getList().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                int selectedRow = display.getClickedRow(event);

                if (selectedRow >= 0) {
                    String id = turmaDetails.get(selectedRow).getId();
                    eventBus.fireEvent(new EditTurmaEvent(id));
                }
            }
        });
    }

    public void go(final HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());

        fetchTurmaDetails();
    }

    public void sortTurmaDetails() {

        // Yes, we could use a more optimized method of sorting, but the
        // point is to create a test case that helps illustrate the higher
        // level concepts used when creating MVP-based applications.
        //
        for (int i = 0; i < turmaDetails.size(); ++i) {
            for (int j = 0; j < turmaDetails.size() - 1; ++j) {
                if (turmaDetails.get(j).getDisplayName()
                        .compareToIgnoreCase(turmaDetails.get(j + 1).getDisplayName()) >= 0) {
                    TurmaDetails tmp = turmaDetails.get(j);
                    turmaDetails.set(j, turmaDetails.get(j + 1));
                    turmaDetails.set(j + 1, tmp);
                }
            }
        }
    }

    public void setTurmaDetails(List<TurmaDetails> turmaDetails) {
        this.turmaDetails = turmaDetails;
    }

    public TurmaDetails getTurmaDetail(int index) {
        return turmaDetails.get(index);
    }

    private void fetchTurmaDetails() {

        rpcService.getTurmaDetails(new AsyncCallback<ArrayList<TurmaDetails>>() {
            public void onSuccess(ArrayList<TurmaDetails> result) {
                turmaDetails = result;
                sortTurmaDetails();
                List<String> data = new ArrayList<String>();

                for (int i = 0; i < result.size(); ++i) {
                    data.add(turmaDetails.get(i).getDisplayName());
                }

                display.setData(data);
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error fetching turma details");
            }
        });
    }

    private void deleteSelectedTurmas() {
        List<Integer> selectedRows = display.getSelectedRows();
        ArrayList<String> ids = new ArrayList<String>();

        for (int i = 0; i < selectedRows.size(); ++i) {
            ids.add(turmaDetails.get(selectedRows.get(i)).getId());
        }

        rpcService.deleteTurmas(ids, new AsyncCallback<ArrayList<TurmaDetails>>() {
            public void onSuccess(ArrayList<TurmaDetails> result) {
                turmaDetails = result;
                sortTurmaDetails();
                List<String> data = new ArrayList<String>();

                for (int i = 0; i < result.size(); ++i) {
                    data.add(turmaDetails.get(i).getDisplayName());
                }

                display.setData(data);

            }

            public void onFailure(Throwable caught) {
                Window.alert("Error deleting selected turmas");
            }
        });
    }
}
