package pt.isep.cms.turmas.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddTurmaEvent extends GwtEvent<AddTurmaEventHandler> {
    public static Type<AddTurmaEventHandler> TYPE = new Type<AddTurmaEventHandler>();

    @Override
    public Type<AddTurmaEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AddTurmaEventHandler handler) {
        handler.onAddTurma(this);
    }
}
