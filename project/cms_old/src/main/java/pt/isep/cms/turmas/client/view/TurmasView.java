package pt.isep.cms.turmas.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;
import pt.isep.cms.turmas.client.presenter.TurmasPresenter;

import java.util.ArrayList;
import java.util.List;

public class TurmasView extends Composite implements TurmasPresenter.Display {
    private final Button addButton;
    private final Button deleteButton;
    private FlexTable turmasTable;
    private final FlexTable contentTable;
    // private final VerticalPanel vPanel ;

    public TurmasView() {
        DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);
        contentTableDecorator.setWidth("100%");
        contentTableDecorator.setWidth("18em");

        contentTable = new FlexTable();
        contentTable.setWidth("100%");
        contentTable.getCellFormatter().addStyleName(0, 0, "turmas-ListContainer");
        contentTable.getCellFormatter().setWidth(0, 0, "100%");
        contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);

        // vPanel = new VerticalPanel();
        // initWidget(vPanel);

        // Create the menu
        //
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setBorderWidth(0);
        hPanel.setSpacing(0);
        hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
        addButton = new Button("Add");
        hPanel.add(addButton);

        deleteButton = new Button("Delete");
        hPanel.add(deleteButton);

        // vPanel.add(hPanel);

        contentTable.getCellFormatter().addStyleName(0, 0, "turmas-ListMenu");
        contentTable.setWidget(0, 0, hPanel);

        // Create the turmas list
        //
        turmasTable = new FlexTable();
        turmasTable.setCellSpacing(0);
        turmasTable.setCellPadding(0);
        turmasTable.setWidth("100%");
        turmasTable.addStyleName("turmas-ListContents");
        turmasTable.getColumnFormatter().setWidth(0, "15px");

        // vPanel.add(turmasTable);

        contentTable.setWidget(1, 0, turmasTable);

        contentTableDecorator.add(contentTable);
    }

    public HasClickHandlers getAddButton() {
        return addButton;
    }

    public HasClickHandlers getDeleteButton() {
        return deleteButton;
    }

    public HasClickHandlers getList() {
        return turmasTable;
    }

    public void setData(List<String> data) {
        turmasTable.removeAllRows();

        for (int i = 0; i < data.size(); ++i) {
            turmasTable.setWidget(i, 0, new CheckBox());
            turmasTable.setText(i, 1, data.get(i));
        }
    }

    public int getClickedRow(ClickEvent event) {
        int selectedRow = -1;
        HTMLTable.Cell cell = turmasTable.getCellForEvent(event);

        if (cell != null) {
            // Suppress clicks if the user is actually selecting the
            // check box
            //
            if (cell.getCellIndex() > 0) {
                selectedRow = cell.getRowIndex();
            }
        }

        return selectedRow;
    }

    public List<Integer> getSelectedRows() {
        List<Integer> selectedRows = new ArrayList<Integer>();

        for (int i = 0; i < turmasTable.getRowCount(); ++i) {
            CheckBox checkBox = (CheckBox) turmasTable.getWidget(i, 0);
            if (checkBox.getValue()) {
                selectedRows.add(i);
            }
        }

        return selectedRows;
    }

    public Widget asWidget() {
        return this;
    }
}
