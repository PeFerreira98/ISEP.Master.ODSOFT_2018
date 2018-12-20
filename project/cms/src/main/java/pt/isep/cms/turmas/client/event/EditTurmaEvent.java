package pt.isep.cms.turmas.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditTurmaEvent extends GwtEvent<EditTurmaEventHandler> {
    public static Type<EditTurmaEventHandler> TYPE = new Type<EditTurmaEventHandler>();
    private final String id;

    public EditTurmaEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public Type<EditTurmaEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EditTurmaEventHandler handler) {
        handler.onEditTurma(this);
    }
}
