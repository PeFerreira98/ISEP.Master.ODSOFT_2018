package pt.isep.cms.turmas.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class TurmaDeletedEvent extends GwtEvent<TurmaDeletedEventHandler> {
    public static Type<TurmaDeletedEventHandler> TYPE = new Type<TurmaDeletedEventHandler>();

    @Override
    public Type<TurmaDeletedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(TurmaDeletedEventHandler handler) {
        handler.onTurmaDeleted(this);
    }
}
